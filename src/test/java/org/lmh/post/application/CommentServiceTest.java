package org.lmh.post.application;

import org.junit.jupiter.api.Test;
import org.lmh.post.application.dto.LikeRequestDto;
import org.lmh.post.application.dto.UpdateCommentRequestDto;
import org.lmh.post.domain.comment.Comment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommentServiceTest extends PostApplicationTestTemplate {

    @Test
    void givenCreateCommentRequestDto_whenCreateComment_thenReturnComment() {
        // when
        Comment comment = commentService.createComment(commentRequestDto);

        // then
        String content = comment.getContent();
        assertEquals(commentContentText, content);
    }

    @Test
    void givenCreateComment_whenUpdateComment_thenReturnUpdatedComment() {
        // given
        Comment comment = commentService.createComment(commentRequestDto);
        
        // when
        UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto(comment.getId(), user.getId(), "updated content");
        Comment updatedComment = commentService.updateComment(updateCommentRequestDto);
    
        // then
        assertEquals(comment.getId(), updatedComment.getId());
        assertEquals(comment.getAuthor(), updatedComment.getAuthor());
        assertEquals(comment.getContent(), updatedComment.getContent());
    }

    @Test
    void givenComment_whenLikeComment_thenReturnCommentWithLike() {
        // given
        Comment comment = commentService.createComment(commentRequestDto);
    
        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);

        // then
        assertEquals(comment.getId(), comment.getLikeCount());
    }

    @Test
    void givenComment_whenLikeCommentMyself_thenThrowException() {
        // given
        Comment comment = commentService.createComment(commentRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), user.getId());

        // then
        assertThrows(IllegalArgumentException.class, () -> commentService.likeComment(likeRequestDto));
    }

    @Test
    void givenComment_whenUnlikeComment_thenReturnCommentWithoutLike() {
        // given
        Comment comment = commentService.createComment(commentRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);
        commentService.unlikeComment(likeRequestDto);

        // then
        assertEquals(0, comment.getLikeCount());
    }

}
