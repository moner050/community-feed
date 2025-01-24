package org.lmh.admin.ui.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lmh.common.utils.TimeCalculator;

import java.time.LocalDateTime;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserTableResponseDto {

    @Getter
    private Long id;
    @Getter
    private String email;
    @Getter
    private String name;
    @Getter
    private String role;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;

    public String getCreatedAt() {
        return TimeCalculator.getFormattedDate(createdAt);
    }

    public String getUpdatedAt() {
        return TimeCalculator.getFormattedDate(updatedAt);
    }

    public String getLastLoginAt() {
        return TimeCalculator.getFormattedDate(lastLoginAt);
    }
}
