package com.example.loan.config.auth;

public enum AuthErrorCode {
    // 1 - errorMessage = "아이디(로그인 전화번호, 로그인 전용 아이디) 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.";
    // 99 - errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의하세요";

    INVALID_CREDENTIALS(1, "아이디(로그인 전화번호, 로그인 전용 아이디) 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.")
    , UNKNOWN_ERROR(99, "알 수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의하세요.");

    final private int code;
    final private String explanation;

    public int getCode() {
        return code;
    }

    public String getExplanation() {
        return explanation;
    }

    AuthErrorCode(int code, String explanation) {
        this.code = code;
        this.explanation = explanation;
    }

    public static String getExplanationByCode(int code) {
        for (AuthErrorCode errorCode : AuthErrorCode.values()) {
            if (errorCode.getCode() == code) {
                return errorCode.getExplanation();
            }
        }

        return "Unknown error code: " + code;
    }
}
