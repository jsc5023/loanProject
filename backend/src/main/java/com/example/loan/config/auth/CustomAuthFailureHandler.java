package com.example.loan.config.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        int errorCode = findErrorCode(exception);

        request.setAttribute("errorCode", errorCode);
        request.getRequestDispatcher("/security/login").forward(request, response);
    }

    private int findErrorCode(AuthenticationException exception) {
        int errorCode = 0;

        if(exception instanceof BadCredentialsException) {
            errorCode = AuthErrorCode.INVALID_CREDENTIALS.getCode();
        } else {
            errorCode = AuthErrorCode.UNKNOWN_ERROR.getCode();
        }

        return errorCode;
    }

}
