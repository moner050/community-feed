package org.lmh.user.application.dto;

public record FollowUserRequestDto(Long userId, Long targetUserId) {
}
