package org.lmh.post.ui;

import lombok.RequiredArgsConstructor;
import org.lmh.common.principal.AuthPrincipal;
import org.lmh.common.principal.UserPrincipal;
import org.lmh.common.ui.Response;
import org.lmh.post.repository.post_queue.UserPostQueueQueryRepository;
import org.lmh.post.ui.dto.GetPostContentResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final UserPostQueueQueryRepository queueQueryRepository;

    @GetMapping("")
    public Response<List<GetPostContentResponseDto>> getPostFeed(@AuthPrincipal UserPrincipal userPrincipal, Long lastPostId) {

        List<GetPostContentResponseDto> result = queueQueryRepository.getContentResponse(userPrincipal.getUserId(), lastPostId);
        return Response.ok(result);
    }
}
