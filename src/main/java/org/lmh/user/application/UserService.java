package org.lmh.user.application;

import org.lmh.user.application.dto.CreateUserRequestDto;
import org.lmh.user.application.interfaces.UserRepository;
import org.lmh.user.domain.User;
import org.lmh.user.domain.UserInfo;

import java.util.IllformedLocaleException;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDto dto) {
        UserInfo info = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(null, info);

        return userRepository.save(user);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId);
    }
}
