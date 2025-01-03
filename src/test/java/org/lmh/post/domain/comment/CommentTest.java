package org.lmh.post.domain.comment;

import org.junit.jupiter.api.Test;
import org.lmh.post.domain.Post;
import org.lmh.post.domain.content.CommentContent;
import org.lmh.post.domain.content.PostContent;
import org.lmh.user.domain.User;
import org.lmh.user.domain.UserInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommentTest {

    private final UserInfo info = new UserInfo("test", "url");
    private final User user = new User(1L, info);
    private final User otherUser = new User(2L, info);

    private final Post post = new Post(1L, user, new PostContent("content"));
    private final Comment comment = new Comment(1L, post, user, new CommentContent("comment"));

    @Test
    void givenCommentCreated_whenLike_thenLikeCountShouldBe1() {
        // given
        comment.like(otherUser);

        // when, then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_whenLikeMySelf_thenThrowError() {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> comment.like(user));
    }

    @Test
    void givenComment_whenCommentUpdate_thenCommentContentShouldBeUpdated() {
        // given
        String newCommentContent = "updated comment";
    
        // when
        comment.updateComment(user, newCommentContent);
    
        // then
        assertEquals(newCommentContent, comment.getContent());
    }

    @Test
    void givenNewStringContent_whenOtherUserUpdatesUserComment_thenShouldThrowException() {
        // given
        String newCommentContent = "updated comment";

        // when, then
        assertThrows(IllegalArgumentException.class, () -> comment.updateComment(otherUser, newCommentContent));
    }

    @Test
    void givenNewContentLengthOver100_whenUpdateComment_thenThrowException() {
        // given
        String longCommentContent = "a".repeat(101);
    
        // when, then
        assertThrows(IllegalArgumentException.class, () -> comment.updateComment(user, longCommentContent));
    }
    
}
