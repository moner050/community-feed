package org.lmh.post.repository.post_queue;

import lombok.RequiredArgsConstructor;
import org.lmh.common.service.RedisService;
import org.lmh.post.repository.entity.post.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class UserQueueRedisRepositoryImpl implements UserQueueRedisRepository{

    private final RedisService<PostEntity> redisService;

    @Override
    public void publishPostToFollowingUserList(PostEntity postEntity, List<Long> userIdList) {
        for (Long userId : userIdList) {
            redisService.setData(String.valueOf(userId), postEntity);
        }
    }

    @Override
    public void publishPostListToFollowerUser(List<PostEntity> postEntityList, Long userId) {
        redisService.setAllData(String.valueOf(userId), postEntityList);
    }

    @Override
    public void deleteDeleteFeed(Long userId, Long authorId) {
        String user = String.valueOf(userId);
        String author = String.valueOf(authorId);

        if(redisService.containsKey(user)) {
            redisService.deleteValueByTargetId(user, author);
        }
    }

    public Set<PostEntity> getPostSetByUserId(Long userId) {
        return redisService.getData(String.valueOf(userId), PostEntity.class);
    }
}
