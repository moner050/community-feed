package org.lmh.admin.ui;

import lombok.RequiredArgsConstructor;
import org.lmh.admin.ui.dto.GetTableListResponse;
import org.lmh.admin.ui.dto.users.GetUserTableRequestDto;
import org.lmh.admin.ui.dto.users.GetUserTableResponseDto;
import org.lmh.admin.ui.query.AdminTableQueryRepository;
import org.lmh.admin.ui.query.UserStatsQueryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserStatsQueryRepository userStatsQueryRepository;
    private final AdminTableQueryRepository adminTableQueryRepository;

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");

        mav.addObject("result", userStatsQueryRepository.getDailyRegisterUserStats(7));
        return mav;
    }

    @GetMapping("/users")
    public ModelAndView users(GetUserTableRequestDto dto) {
        ModelAndView mav = new ModelAndView("users");

        GetTableListResponse<GetUserTableResponseDto> result = adminTableQueryRepository.getUserTableData(dto);
        mav.addObject("requestDto", dto);
        mav.addObject("userList", result.getTableData());
        mav.addObject("totalCount", result.getTotalCount());
        return mav;
    }
}
