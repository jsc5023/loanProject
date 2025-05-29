package com.example.loan.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "userId", unique = true),
        @Index(columnList = "email", unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class UserAccount extends AuditingFields {
    @Id
    @Column(name = "user_id", length = 50)
    String userId;

    private @Column(nullable = false) String userPassword;

    @Setter
    @Column(length = 100) private String email;
    @Setter @Column(length = 100) private String nickname;

    @Setter @Column(length = 100) private String phoneNumber;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Setter
    @Column(nullable = false)
    private LocalDate birth_date; // 생성일시

    @Setter
    @Column(nullable = false, length = 1)
    private char gender;

    protected UserAccount() {}

    private UserAccount(String userId, String userPassword, String email, String nickname, String phoneNumber, LocalDate birth_date, char gender, String createdBy) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.birth_date = birth_date;
        this.gender = gender;
        this.createdBy = createdBy;
        this.modifiedBy = createdBy;
    }

    public static UserAccount of(String userId, String userPassword, String email, String nickname, String phoneNumber, LocalDate birth_date, char gender) {
        return UserAccount.of(userId, userPassword, email, nickname, phoneNumber, birth_date, gender, null);
    }

    public static UserAccount of(String userId, String userPassword, String email, String nickname, String phoneNumber, LocalDate birth_date, char gender, String createdBy) {
        return new UserAccount(userId, userPassword, email, nickname, phoneNumber, birth_date, gender, createdBy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount that)) return false;
        return this.getUserId() != null && this.getUserId().equals(that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getUserId());
    }
}
