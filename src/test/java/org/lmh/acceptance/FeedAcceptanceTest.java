package org.lmh.acceptance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lmh.acceptance.utils.AcceptanceTestTemplate;
import org.lmh.post.application.dto.CreatePostRequestDto;
import org.lmh.post.domain.content.PostPublicationState;
import org.lmh.post.ui.dto.GetPostContentResponseDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.lmh.acceptance.steps.FeedAcceptanceSteps.requestCreatePost;
import static org.lmh.acceptance.steps.FeedAcceptanceSteps.requestFeed;

public class FeedAcceptanceTest extends AcceptanceTestTemplate {

    /**
     * user1 -> follow -> user2
     * user1 -> follow -> user3
     */
    @BeforeEach
    void setUp() {
        super.init();
    }

    /**
     * User2 Create Post1
     * User1 Get Post1 From Feed
     */
    @Test
    void givenUserHasFollowerAndCreatePost_whenFollowerUserRequestFeed_thenFollowerCanGetPostFromFeed() {
        // given
        CreatePostRequestDto dto = new CreatePostRequestDto(2L, "user 1 can get this post", PostPublicationState.PUBLIC);
        Long createPostId = requestCreatePost(dto);

        // when
        List<GetPostContentResponseDto> result = requestFeed(1L);

        // then
        assertEquals(1, result.size());
        assertEquals(createPostId, result.get(0).getId());
    }
}
