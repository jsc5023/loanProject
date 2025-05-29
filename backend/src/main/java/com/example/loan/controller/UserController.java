package com.example.loan.controller;

import com.example.loan.config.auth.AuthErrorCode;
import com.example.loan.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/security/login")
    public String login() {

        return "/security/login";
    }

    @PostMapping("/security/login")
    public String loginError(HttpServletRequest request, Model model) {
        int errorCode = (Integer) request.getAttribute("errorCode");
        String exception = AuthErrorCode.getExplanationByCode(errorCode);

        model.addAttribute("error", errorCode);
        model.addAttribute("errorMessage", exception);

        return "/security/login";
    }


    @GetMapping("/security/sign-up")
    public String signUp() {
        return "/security/sign-up";
    }

    @PostMapping("/security/sign-up")
    public String signUp(HttpServletRequest request, Model model) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        String phoneNumber = request.getParameter("phone_number");

        LocalDate birth_date = LocalDate.parse(request.getParameter("birthdate"), DateTimeFormatter.ISO_DATE);
        char gender = request.getParameter("birthdate").charAt(0);
        String confirmPassword = request.getParameter("confirm-password");
        String email = request.getParameter("email");

        if(!password.equals(confirmPassword)) {
            model.addAttribute("error", "비밀번호와 비밀번호 확인의 내용이 같지 않습니다");
            return "/security/sign-up"; // 실패시 다시 사용자 ID로 이동
        }

        if (userService.isUserIdDuplicate(userId)) {
            model.addAttribute("error", "이미 존재하는 사용자 ID입니다.");
            return "/security/sign-up";
        }

        userService.signUp(userId, password, email, nickname, phoneNumber, birth_date, gender);


        return "redirect:/security/login";
    }
}
