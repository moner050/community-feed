package org.lmh.admin.ui.query;

import org.lmh.admin.ui.dto.users.GetDailyRegisterUserResponseDto;

import java.util.List;

public interface UserStatsQueryRepository {

    List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStats(int beforeDays);
}
