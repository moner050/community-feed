package org.lmh.post.repository.post_queue;

import lombok.RequiredArgsConstructor;
import org.lmh.common.service.RedisService;
import org.lmh.post.repository.entity.post.PostEntity;
import org.lmh.post.repository.jpa.JpaPostRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class UserQueueRedisRepositoryImpl implements UserQueueRedisRepository{

    private final RedisService redisService;
    private final JpaPostRepository jpaPostRepository;

    @Override
    public void publishPostToFollowingUserList(PostEntity postEntity, List<Long> userIdList) {
        for (Long userId : userIdList) {
            redisService.setData(String.valueOf(userId), postEntity.getId());
        }
    }

    @Override
    public void publishPostListToFollowerUser(List<PostEntity> postEntityList, Long userId) {
        List<Long> postIdList = new ArrayList<>();

        for (PostEntity postEntity : postEntityList) {
            postIdList.add(postEntity.getId());
        }

        redisService.setAllData(String.valueOf(userId), postIdList);
    }

    @Override
    public void deleteDeleteFeed(Long userId, Long authorId) {
        String user = String.valueOf(userId);
        String author = String.valueOf(authorId);

        if(redisService.containsKey(user)) {
            redisService.deleteValueByTargetId(user, author);
        }
    }

    @Override
    public List<PostEntity> getPostListByUserId(Long userId) {
        List<Long> postPKList = redisService.getData(String.valueOf(userId)).stream().toList();

        return jpaPostRepository.findAllByIdList(postPKList);
    }
}
