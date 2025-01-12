package org.lmh.acceptance.utils;

import org.lmh.user.application.dto.CreateUserRequestDto;
import org.lmh.user.application.dto.FollowUserRequestDto;
import org.springframework.stereotype.Component;

import static org.lmh.acceptance.steps.UserAcceptanceSteps.createUser;
import static org.lmh.acceptance.steps.UserAcceptanceSteps.followUser;

@Component
public class DataLoader {

    public void loadData() {
        CreateUserRequestDto dto = new CreateUserRequestDto("testUser", "");
        createUser(dto);        // 1번 유저 생성
        createUser(dto);        // 2번 유저 생성
        createUser(dto);        // 3번 유저 생성

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(1L, 3L));
    }
}
