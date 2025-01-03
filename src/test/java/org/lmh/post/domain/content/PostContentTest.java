package org.lmh.post.domain.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostContentTest {

    @Test
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent() {
        // given
        String text = "test content";

        // when
        PostContent content = new PostContent(text);

        // then
        assertEquals(text, content.contentText);
    }

    @Test
    void givenContentLengthIsOver_whenCreated_thenThrowError() {
        // given
        String content = "a".repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁", "헉", "닭", "쑥", "슳"})
    void givenContentLengthIsOverAndKorean_whenCreated_thenThrowError(String koreanWord) {
        // given
        String content = koreanWord.repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    void givenContentLengthIsUnder_whenCreated_thenThrowError() {
        // given
        String content = "a".repeat(4);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsEmpty_whenCreated_thenThrowError(String value) {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(value));
    }

    @Test
    void givenUpdatedContentLengthIsOver_whenUpdated_thenThrowError() {
        // given
        String text = "test content";
        PostContent content = new PostContent(text);

        // when, then
        String value = "b".repeat(501);
        assertThrows(IllegalArgumentException.class, () -> content.updateContent(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁", "헉", "닭", "쑥", "슳"})
    void givenUpdatedContentLengthIsOverAndKorean_whenUpdated_thenThrowError(String koreanWord) {
        // given
        String text = "test content";
        PostContent content = new PostContent(text);
    
        // when, then
        String updatedContent = koreanWord.repeat(501);
        assertThrows(IllegalArgumentException.class, () -> content.updateContent(updatedContent));
    }

    @Test
    void givenUpdatedContentLengthIsUnder_whenUpdated_thenThrowError() {
        // given
        String text = "valid content";
        PostContent content = new PostContent(text);
    
        // when, then
        String value = "b".repeat(4);
        assertThrows(IllegalArgumentException.class, () -> content.updateContent(value));
    }
}
