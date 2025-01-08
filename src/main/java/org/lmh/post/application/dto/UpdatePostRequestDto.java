package org.lmh.post.application.dto;

import org.lmh.post.domain.content.PostPublicationState;

public record UpdatePostRequestDto(Long userId, String content, PostPublicationState state) {
}
