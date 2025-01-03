package org.lmh.post.domain;

import org.junit.jupiter.api.Test;
import org.lmh.post.domain.content.PostContent;
import org.lmh.post.domain.content.PostPublicationState;
import org.lmh.user.domain.User;
import org.lmh.user.domain.UserInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostTest {

    private final UserInfo info = new UserInfo("test", "url");
    private final User user = new User(1L, info);
    private final User otherUser = new User(2L, info);

    private final Post post = new Post(1L, user, new PostContent("content"));

    @Test
    void givenPostCreated_whenLike_thenLikeCountShouldBe1() {
        // when
        post.like(otherUser);

        // then
        assertEquals(1, post.getLikeCount());
    }
    
    @Test
    void givenPostCreated_whenLikeMySelf_thenThrowException() {
        // when & then
        assertThrows(IllegalStateException.class, () -> post.like(user));
    }


    @Test
    void givenPostLikeOtherUser_whenUnlike_thenLikeCountShouldBe0() {
        // given
        post.like(otherUser);
    
        // when
        post.unlike();
    
        // then
        assertEquals(0, post.getLikeCount());
    }
    
    @Test
    void givenPostCreated_whenUnlike_thenLikeCountShouldBe0() {
        // when
        post.unlike();
    
        // then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostUpdate_whenPublicNewContent_thenContentShouldBeUpdated() {
        // given
        String newContent = "new Content";
        
        // when
        post.updatePost(user, newContent, PostPublicationState.PUBLIC);
    
        // then
        assertEquals(newContent, post.getContent());
    }

    @Test
    void givenNewStringContent_whenOtherUserUpdatesUserPost_thenShouldThrowException() {
        // given
        String newContent = "New Content";
    
        // when & then
        assertThrows(IllegalArgumentException.class, () -> post.updatePost(otherUser, newContent, PostPublicationState.PUBLIC));
    }

}
