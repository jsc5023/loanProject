package com.example.loan.dto;

import com.example.loan.domain.UserAccount;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserAccountDto(
        String userId,
        String userPassword,
        String email,
        String nickname,
        String phoneNumber,
        LocalDate birth_date,
        char gender,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
){
    public static UserAccountDto of(String userId, String userPassword, String email, String nickname, String phoneNumber, LocalDate birth_date, char gender) {
        return new UserAccountDto(userId, userPassword, email, nickname, phoneNumber, birth_date, gender, null, null, null, null);
    }

    public static UserAccountDto of(String userId, String userPassword, String email, String nickname, String phoneNumber, LocalDate birth_date, char gender, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new UserAccountDto(userId, userPassword, email, nickname, phoneNumber, birth_date, gender, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getUserId(),
                entity.getUserPassword(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getPhoneNumber(),
                entity.getBirth_date(),
                entity.getGender(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public UserAccount toEntity() {
        return UserAccount.of(
                userId,
                userPassword,
                email,
                nickname,
                phoneNumber,
                birth_date,
                gender
        );
    }
}
