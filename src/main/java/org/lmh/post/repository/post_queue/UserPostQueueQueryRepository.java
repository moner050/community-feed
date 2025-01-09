package org.lmh.post.repository.post_queue;

import org.lmh.post.ui.dto.GetPostContentResponseDto;

import java.util.List;

public interface UserPostQueueQueryRepository {
    List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId);
}
