package org.lmh.user.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lmh.user.application.dto.CreateUserRequestDto;
import org.lmh.user.application.dto.FollowUserRequestDto;
import org.lmh.user.application.interfaces.UserRelationRepository;
import org.lmh.user.application.interfaces.UserRepository;
import org.lmh.user.domain.User;
import org.lmh.user.repository.FakeUserRelationRepository;
import org.lmh.user.repository.FakeUserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserRelationServiceTest {

    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);
    private final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();
    private final UserRelationService userRelationService = new UserRelationService(userService, userRelationRepository);

    private User user1;
    private User user2;

    private FollowUserRequestDto requestDto;

    @BeforeEach
    void init() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
        this.user1 = userService.createUser(dto);
        this.user2 = userService.createUser(dto);

        this.requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @Test
    void givenCreateTwoUser_whenFollow_thenUserFollowSaved() {
        // when
        userRelationService.follow(requestDto);

        // then
        assertEquals(1, user1.getFollowingCounter());
        assertEquals(1, user2.getFollowerCounter());
    }

    @Test
    void givenCreateTwoUserFollowed_whenFollow_thenUserThrowError() {
        // given
        userRelationService.follow(requestDto);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenFollow_thenUserThrowError() {
        // given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(sameUser));
    }

    @Test
    void givenCreateTwoUser_whenUnfollow_thenUserUnfollowSaved() {
        // given
        userRelationService.follow(requestDto);

        // when
        userRelationService.unfollow(requestDto);

        // then
        assertEquals(0, user1.getFollowingCounter());
        assertEquals(0, user2.getFollowerCounter());
    }

    @Test
    void givenCreateTwoUserFollowed_whenUnfollow_thenUserThrowError() {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenUnfollowSelf_thenUserThrowError() {
        // given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(sameUser));
    }

}
