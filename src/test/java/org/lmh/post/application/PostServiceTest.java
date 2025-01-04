package org.lmh.post.application;

import org.junit.jupiter.api.Test;
import org.lmh.post.application.dto.LikeRequestDto;
import org.lmh.post.domain.Post;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostServiceTest extends PostApplicationTestTemplate{

    @Test
    void givenPostRequestDto_whenCreate_thenReturnPost() {
        // when
        Post savedPost = postService.createPost(postRequestDto);

        // then
        Post foundPost = postService.getPost(savedPost.getId());
        assertEquals(savedPost, foundPost);
    }

    @Test
    void givenCreatePost_whenUpdatePost_thenCreateEqualUpdate() {
        // given
        Post savedPost = postService.createPost(postRequestDto);
    
        // when
        Post updatedPost = postService.updatePost(savedPost.getId(), postRequestDto);
    
        // then
        assertEquals(savedPost.getId(), updatedPost.getId());
        assertEquals(savedPost.getAuthor(), updatedPost.getAuthor());
        assertEquals(savedPost.getContent(), updatedPost.getContent());
    }

    @Test
    void givenCreatePost_whenLiked_thenReturnPostWithLike() {
        // given
        Post savedPost = postService.createPost(postRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        // then
        assertEquals(1, savedPost.getLikeCount());
    }

    @Test
    void givenCreatePost_whenLikedTwice_thenReturnPostWithLike() {
        // given
        Post savedPost = postService.createPost(postRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);
        postService.likePost(likeRequestDto);
    
        // then
        assertEquals(1, savedPost.getLikeCount());
    }

    @Test
    void givenCreatePostLiked_whenUnliked_thenReturnPostWithLike() {
        // given
        Post savedPost = postService.createPost(postRequestDto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        // when
        postService.unlikePost(likeRequestDto);

        // then
        assertEquals(0, savedPost.getLikeCount());
    }

    @Test
    void givenCreatePost_whenUnLiked_thenReturnPostWithLike() {
        // given
        Post savedPost = postService.createPost(postRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.unlikePost(likeRequestDto);

        // then
        assertEquals(0, savedPost.getLikeCount());
    }

}
