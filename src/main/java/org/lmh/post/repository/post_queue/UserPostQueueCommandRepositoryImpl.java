package org.lmh.post.repository.post_queue;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.lmh.post.repository.entity.post.PostEntity;
import org.lmh.post.repository.entity.post.UserPostQueueEntity;
import org.lmh.post.repository.jpa.JpaPostRepository;
import org.lmh.post.repository.jpa.JpaUserPostQueueRepository;
import org.lmh.user.repository.entity.UserEntity;
import org.lmh.user.repository.jpa.JpaUserRelationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository{

    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        // 작성자 엔티티 불러오기
        UserEntity userEntity = postEntity.getAuthor();
        // 작성자 팔로워들 id 리스트
        List<Long> followerIds = jpaUserRelationRepository.findFollowers(userEntity.getId());

        // 엔티티로 변환해 저장
        List<UserPostQueueEntity> userPostQueueEntityList = followerIds.stream()
                .map(userId -> new UserPostQueueEntity(userId, postEntity.getId(), userEntity.getId()))
                .toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
    }

    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        // 작성자 게시글들 불러오기
        List<Long> postIdList = jpaPostRepository.findAllPostIdsByAuthorId(targetId);

        // 엔티티로 변환해 저장
        List<UserPostQueueEntity> userPostQueueEntityList = postIdList.stream()
                .map(postId -> new UserPostQueueEntity(userId, postId, targetId))
                .toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
    }

    @Override
    @Transactional
    public void deleteUnFollowPost(Long userId, Long targetId) {
        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
    }
}
