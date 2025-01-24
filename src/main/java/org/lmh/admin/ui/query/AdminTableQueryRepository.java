package org.lmh.admin.ui.query;

import org.lmh.admin.ui.dto.GetTableListResponse;
import org.lmh.admin.ui.dto.users.GetUserTableRequestDto;
import org.lmh.admin.ui.dto.users.GetUserTableResponseDto;

public interface AdminTableQueryRepository {

    GetTableListResponse<GetUserTableResponseDto> getUserTableData(GetUserTableRequestDto dto);
}
