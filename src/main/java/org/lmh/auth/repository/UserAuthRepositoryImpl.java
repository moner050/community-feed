package org.lmh.auth.repository;

import lombok.RequiredArgsConstructor;
import org.lmh.auth.application.interfaces.UserAuthRepository;
import org.lmh.auth.domain.UserAuth;
import org.lmh.auth.repository.entity.UserAuthEntity;
import org.lmh.auth.repository.jpa.JpaUserAuthRepository;
import org.lmh.user.application.interfaces.UserRepository;
import org.lmh.user.domain.User;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserAuthRepositoryImpl implements UserAuthRepository {

    private final JpaUserAuthRepository jpaUserAuthRepository;
    private final UserRepository userRepository;

    @Override
    public UserAuth registerUser(UserAuth auth, User user) {
        User savedUser = userRepository.save(user);
        UserAuthEntity userAuthEntity = new UserAuthEntity(auth, savedUser.getId());
        userAuthEntity = jpaUserAuthRepository.save(userAuthEntity);

        return userAuthEntity.toUserAuth();
    }
}
