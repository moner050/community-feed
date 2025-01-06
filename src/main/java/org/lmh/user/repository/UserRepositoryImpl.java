package org.lmh.user.repository;

import lombok.RequiredArgsConstructor;
import org.lmh.user.application.interfaces.UserRepository;
import org.lmh.user.domain.User;
import org.lmh.user.repository.entity.UserEntity;
import org.lmh.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity(user);
        entity = jpaUserRepository.save(entity);
        return entity.toUser();
    }

    @Override
    public User findById(Long id) {
        UserEntity entity = jpaUserRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        return entity.toUser();
    }
}
