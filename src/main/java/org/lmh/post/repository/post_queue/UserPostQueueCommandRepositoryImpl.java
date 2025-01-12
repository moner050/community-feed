package org.lmh.post.repository.post_queue;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.lmh.post.repository.entity.post.PostEntity;
import org.lmh.post.repository.jpa.JpaPostRepository;
import org.lmh.user.repository.entity.UserEntity;
import org.lmh.user.repository.jpa.JpaUserRelationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository{

    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final UserQueueRedisRepository userQueueRedisRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        // 작성자 엔티티 불러오기
        UserEntity userEntity = postEntity.getAuthor();
        // 작성자 팔로워들 id 리스트
        List<Long> followerIds = jpaUserRelationRepository.findFollowers(userEntity.getId());
        // 팔로워들에게 게시글 넣어주기
        userQueueRedisRepository.publishPostToFollowingUserList(postEntity, followerIds);
    }

    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        // 작성자 게시글들 불러오기
        List<PostEntity> postEntities = jpaPostRepository.findAllPostIdsByAuthorId(targetId);
        // 팔로우한 작성자 게시글을 유저에게 넣어주기
        userQueueRedisRepository.publishPostListToFollowerUser(postEntities, userId);
    }

    @Override
    @Transactional
    public void deleteUnFollowPost(Long userId, Long targetId) {
        userQueueRedisRepository.deleteDeleteFeed(userId, targetId);
    }
}
