package org.lmh.user.application;

import org.junit.jupiter.api.Test;
import org.lmh.user.application.dto.CreateUserRequestDto;
import org.lmh.user.application.interfaces.UserRepository;
import org.lmh.user.domain.User;
import org.lmh.user.domain.UserInfo;
import org.lmh.user.repository.FakeUserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {

    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);

    @Test
    void givenUserInfoDto_whenCreateUser_thenCanFindUser() {
        // given
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");

        // when
        User savedUser = userService.createUser(dto);

        // then
        User foundUser = userService.getUser(savedUser.getId());
        UserInfo userInfo = foundUser.getInfo();
        assertEquals(foundUser.getId(), savedUser.getId());
        assertEquals("test", userInfo.getName());
    }
}