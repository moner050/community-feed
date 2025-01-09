package org.lmh.user.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.lmh.post.repository.post_queue.UserPostQueueCommandRepository;
import org.lmh.user.application.interfaces.UserRelationRepository;
import org.lmh.user.domain.User;
import org.lmh.user.repository.entity.UserEntity;
import org.lmh.user.repository.entity.UserRelationEntity;
import org.lmh.user.repository.entity.UserRelationIdEntity;
import org.lmh.user.repository.jpa.JpaUserRelationRepository;
import org.lmh.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {

    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserRepository jpaUserRepository;
    private final UserPostQueueCommandRepository commandRepository;

    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        return jpaUserRelationRepository.existsById(id);
    }

    @Override
    @Transactional
    public void save(User user, User targetUser) {
        UserRelationEntity entity = new UserRelationEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.save(entity);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
        commandRepository.saveFollowPost(user.getId(), targetUser.getId());
    }

    @Override
    @Transactional
    public void delete(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.deleteById(id);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
        commandRepository.deleteUnFollowPost(user.getId(), targetUser.getId());
    }
}
