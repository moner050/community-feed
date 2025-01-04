package org.lmh.user.application;

import org.junit.jupiter.api.Test;
import org.lmh.fake.FakeObjectFactory;
import org.lmh.user.application.dto.CreateUserRequestDto;
import org.lmh.user.domain.User;
import org.lmh.user.domain.UserInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();

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
