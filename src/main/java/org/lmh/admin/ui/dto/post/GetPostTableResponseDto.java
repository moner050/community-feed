package org.lmh.admin.ui.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lmh.common.utils.TimeCalculator;

import java.time.LocalDateTime;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetPostTableResponseDto {

    @Getter
    private Long postId;
    @Getter
    private Long userId;
    @Getter
    private String userName;

    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String getContent() {
        if(content.length() > 10) {
            return content.substring(0, 10) + "...";
        }
        return content;
    }

    public String getCreatedAt() {
        return TimeCalculator.getFormattedDate(createdAt);
    }

    public String getUpdatedAt() {
        return TimeCalculator.getFormattedDate(updatedAt);
    }
}
