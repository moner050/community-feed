package org.lmh.post.application.dto;

import org.lmh.post.domain.content.PostPublicationState;

public record CreatePostRequestDto(Long userId, String content, PostPublicationState state) {
}
