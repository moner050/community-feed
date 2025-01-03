package org.lmh.post.application.dto;

public record CreateCommentRequestDto(Long postId, Long userId, String content) {
}
