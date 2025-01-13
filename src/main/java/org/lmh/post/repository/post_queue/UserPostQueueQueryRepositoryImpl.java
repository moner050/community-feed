package org.lmh.post.repository.post_queue;

import lombok.RequiredArgsConstructor;
import org.lmh.post.repository.entity.like.LikeIdEntity;
import org.lmh.post.repository.entity.post.PostEntity;
import org.lmh.post.repository.jpa.JpaLikeRepository;
import org.lmh.post.ui.dto.GetPostContentResponseDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository{

    private final UserQueueRedisRepository userQueueRedisRepository;
    private final JpaLikeRepository jpaLikeRepository;

    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {
        List<PostEntity> postEntities = userQueueRedisRepository.getPostListByUserId(userId);
        List<GetPostContentResponseDto> result = new ArrayList<>();

        for(PostEntity postEntity : postEntities) {
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
                    .isLikedByMe(jpaLikeRepository.existsById(new LikeIdEntity(postEntity.getId(), userId, "POST")))
                    .build();
            result.add(res);
        }

        return result;
    }
}
