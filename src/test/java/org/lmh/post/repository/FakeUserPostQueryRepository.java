package org.lmh.post.repository;

import lombok.RequiredArgsConstructor;
import org.lmh.post.repository.entity.post.PostEntity;
import org.lmh.post.repository.post_queue.UserPostQueueQueryRepository;
import org.lmh.post.ui.dto.GetPostContentResponseDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("test")
@RequiredArgsConstructor
public class FakeUserPostQueryRepository implements UserPostQueueQueryRepository {

    private final FakeUserQueueRedisRepository fakeUserQueueRedisRepository;

    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {
        List<PostEntity> postEntities = fakeUserQueueRedisRepository.getPostListByUserId(userId);
        List<GetPostContentResponseDto> result = new ArrayList<>();

        for(PostEntity postEntity : postEntities) {
            // 일단 아이디만 확인하니 아이디만 넣어 만들어주기
            GetPostContentResponseDto res = GetPostContentResponseDto.builder()
                    .id(postEntity.getId())
                    .content(postEntity.getContent())
                    .userId(postEntity.getAuthor().getId())
                    .userName(postEntity.getAuthor().getName())
                    .userProfileImage(postEntity.getAuthor().getProfileImageUrl())
                    .createAt(postEntity.getRegDt())
                    .updateAt(postEntity.getUpdDt())
                    .likeCount(postEntity.getLikeCount())
                    .commentCount(postEntity.getCommentCount())
                    .build();
            result.add(res);
        }

        return result;
    }
}
