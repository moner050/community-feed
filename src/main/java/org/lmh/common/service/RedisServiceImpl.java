package org.lmh.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.lmh.post.repository.entity.post.PostEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl<T> implements RedisService<T> {

    private final RedisTemplate<String, T> redisTemplate;
    private final ObjectMapper mapper;

    @Override
    public Set<T> getData(String key, Class<T> clazz) {
        String jsonData = (String) redisTemplate.opsForValue().get(key);

        try {
            if (StringUtils.hasText(jsonData)) {
                return Set.of(mapper.readValue(jsonData, clazz));
            }
            return Set.of();
        }
        catch (JsonProcessingException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void setData(String key, T value) {
        redisTemplate.opsForSet().add(key, value);
    }

    @Override
    public void setAllData(String key, List<T> values) {
        for (T value : values) {
            redisTemplate.opsForSet().add(key, value);
        }
    }

    @Override
    public void deleteValue(String key, T value) {
        redisTemplate.opsForSet().remove(key, value);
    }

    @Override
    public void deleteValueByTargetId(String key, String targetId) {
        Set<T> values = redisTemplate.opsForSet().members(key);

        if(values == null || values.isEmpty()) {
            return;
        }

        // 삭제할 대상 선택
        Set<T> targets = values.stream()
                .filter(value -> {
                    if(value instanceof PostEntity) {
                        return targetId.equals(((PostEntity) value).getId());
                    }
                    return false;
                })
                .collect(Collectors.toSet());

        if(!targets.isEmpty()) {
            redisTemplate.opsForSet().remove(targetId, targets.toArray());
        }
    }

    @Override
    public void deleteData(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public boolean containsKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    @Override
    public boolean containsValue(String key, T value) {
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(key, value));
    }
}
