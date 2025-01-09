package org.lmh.post.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder       // 해당 클래스 상속 시, 자식 클래스에 해당 클래스 속성들을 그대로 적용 시켜줌.
@NoArgsConstructor
@AllArgsConstructor
public class GetContentResponseDto {
    private Long id;
    private String content;
    private Long userId;
    private String userName;
    private String userProfileImage;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Integer likeCount;
    private boolean isLikedByMe;
}

