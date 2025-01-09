package org.lmh.post.repository.post_queue;

import org.lmh.post.repository.entity.post.PostEntity;

public interface UserPostQueueCommandRepository {
    // 게시글 생성 시, 나를 팔로우 하는 유저들에게 글을 넣어주는 메소드
    void publishPost(PostEntity postEntity);
    // 유저가 나를 팔로우 시, 나의 게시글들을 넣어주는 메소드
    void saveFollowPost(Long userId, Long targetId);
    // 유저가 나를 언팔로우 시, 나의 게시글들을 삭제시켜 주는 메소드
    void deleteUnFollowPost(Long userId, Long targetId);
}
