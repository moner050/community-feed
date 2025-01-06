package org.lmh.user.ui;

import lombok.RequiredArgsConstructor;
import org.lmh.common.ui.Response;
import org.lmh.user.application.UserService;
import org.lmh.user.application.dto.CreateUserRequestDto;
import org.lmh.user.application.dto.GetUserListResponseDto;
import org.lmh.user.application.dto.GetUserResponseDto;
import org.lmh.user.domain.User;
import org.lmh.user.repository.jpa.JpaUserListQueryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final JpaUserListQueryRepository jpaUserListQueryRepository;

    @PostMapping
    public Response<Long> createUser(@RequestBody CreateUserRequestDto dto) {
        User user = userService.createUser(dto);
        return Response.ok(user.getId());
    }

    @GetMapping("/{userId}")
    public Response<GetUserResponseDto> getUserProfile(@PathVariable(name = "userId") Long userId) {
        return Response.ok(userService.getUserProfile(userId));
    }

    @GetMapping("/{userId}/follower")
    public Response<List<GetUserListResponseDto>> getFollowerList(@PathVariable(name = "userId") Long userId) {
        List<GetUserListResponseDto> result = jpaUserListQueryRepository.getFollowerUserList(userId);
        return Response.ok(result);
    }

    @GetMapping("/{userId}/following")
    public Response<List<GetUserListResponseDto>> getFollowingList(@PathVariable(name = "userId") Long userId) {
        List<GetUserListResponseDto> result = jpaUserListQueryRepository.getFollowingUserList(userId);
        return Response.ok(result);
    }
}
