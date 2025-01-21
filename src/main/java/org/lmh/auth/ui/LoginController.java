package org.lmh.auth.ui;

import lombok.RequiredArgsConstructor;
import org.lmh.auth.application.AuthService;
import org.lmh.auth.application.dto.LoginRequestDto;
import org.lmh.auth.application.dto.UserAccessTokenResponseDto;
import org.lmh.common.ui.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    @PostMapping
    public Response<UserAccessTokenResponseDto> login(@RequestBody LoginRequestDto dto) {
        return Response.ok(authService.login(dto));
    }
}
