package org.lmh.post.domain.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommentContentTest {

    @Test
    void givenContentLengthIsOk_whenCreateCommentContent_thenReturnTextContent() {
        // Arrange
        String contentText = "This is a test comment.";
        
        // Act
        CommentContent commentContent = new CommentContent(contentText);
    
        // Assert
        assertEquals(contentText, commentContent.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenCreateCommentContent_thenThrowException() {
        // Arrange
        String overlyLongContent = "a".repeat(101);
    
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(overlyLongContent));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁", "닭", "굵"})
    void givenContentLengthIsOverAndKorean_whenCreatCommentContent_thenThrowError(String koreanWord) {
        // given
        String content = koreanWord.repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }
    
    @ParameterizedTest
    @NullAndEmptySource
    void givenNullAndEmpty_whenCreateCommentContent_thenThrowException(String content) {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }
    
    
}
