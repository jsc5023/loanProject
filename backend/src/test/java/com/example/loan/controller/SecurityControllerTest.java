package com.example.loan.controller;

import com.example.loan.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("View 컨트롤러 - 시크릿(권한, 유저 관련) 컨트롤러")
@Import(SecurityConfig.class)
@WebMvcTest(SecurityControllerTest.EmptyController.class)
public class SecurityControllerTest {

    private final MockMvc mvc;

    SecurityControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view][GET] 로그인 페이지 - 자동 로그인 화면 이동")
    @Test
    void givenNothing_whenTryingToLogIn_thenReturnsLogInView() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/login"))
                .andExpect(status().is3xxRedirection());
    }

    /**
     * 어떤 컨트롤러도 필요하지 않은 테스트임을 나타내기 위해 테스트용 빈 컴포넌트를 사용함.
     * 이게 없으면, 로그인 페이지 정상호출의 테스트 빌드가 정상적으로 작동되지 않음.
     */
    @TestComponent
    static class EmptyController {}
}
