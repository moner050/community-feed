package org.lmh.user.application.dto;

import org.lmh.user.domain.User;

public record GetUserResponseDto(Long id, String name, String profileImageUrl, Integer followingCount, Integer followerCount) {

    public GetUserResponseDto(User user) {
        this(user.getId(), user.getName(), user.getProfileImageUrl(), user.followingCount(), user.followerCount());
    }
}
