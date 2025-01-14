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
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate<String, Long> redisTemplate;

    @Override
    public Set<Long> getData(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public void setData(String key, Long value) {
        redisTemplate.opsForSet().add(key, value);
    }

    @Override
    public void setAllData(String key, List<Long> values) {
        for (Long value : values) {
            redisTemplate.opsForSet().add(key, value);
        }
    }

    @Override
    public void deleteValue(String key, Long value) {
        redisTemplate.opsForSet().remove(key, value);
    }

    @Override
    public void deleteValueByTargetId(String key, String targetId) {
        Set<Long> values = redisTemplate.opsForSet().members(key);

        if(values == null || values.isEmpty()) {
            return;
        }

        // 삭제할 대상 선택
        Set<Long> targets = values.stream()
                .filter(value -> {
                    return String.valueOf(value).equals(targetId);
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
    public boolean containsValue(String key, Long value) {
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(key, value));
    }
}
