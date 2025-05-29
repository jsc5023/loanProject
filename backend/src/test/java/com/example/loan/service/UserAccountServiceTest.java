package com.example.loan.service;

import com.example.loan.domain.UserAccount;
import com.example.loan.dto.UserAccountDto;
import com.example.loan.repository.UserAccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("회원 관련 로직")
@ExtendWith(MockitoExtension.class)
public class UserAccountServiceTest {

    @InjectMocks private UserAccountService sut;

    @Mock private UserAccountRepository userAccountRepository;

    @DisplayName("회원 정보를 입력하면, 새로운 회원 정보를 저장하여 가입시키고 해당 회원을 리턴한다.")
    @Test
    void givenUserParams_whenSaving_thenSavesUserAccount() {
        // Given
        UserAccount userAccount = createUserAccount("jsc");
        UserAccount saveUserAccount = createSigningUpUserAccount("jsc");
        given(userAccountRepository.save(userAccount)).willReturn(saveUserAccount);

        // When
        UserAccountDto result = sut.saveUser(
                userAccount.getUserId(),
                userAccount.getUserPassword(),
                userAccount.getEmail(),
                userAccount.getNickname(),
                userAccount.getPhoneNumber(),
                userAccount.getBirth_date(),
                userAccount.getGender()
        );

        // Then
        assertThat(result)
                .hasFieldOrPropertyWithValue("userId", userAccount.getUserId())
                .hasFieldOrPropertyWithValue("userPassword", userAccount.getUserPassword())
                .hasFieldOrPropertyWithValue("email", userAccount.getEmail())
                .hasFieldOrPropertyWithValue("nickname", userAccount.getNickname())
                .hasFieldOrPropertyWithValue("phoneNumber", userAccount.getPhoneNumber())
                .hasFieldOrPropertyWithValue("birth_date", userAccount.getBirth_date())
                .hasFieldOrPropertyWithValue("gender", userAccount.getGender())
                .hasFieldOrPropertyWithValue("createdBy", userAccount.getUserId())
                .hasFieldOrPropertyWithValue("modifiedBy", userAccount.getUserId());

        then(userAccountRepository).should().save(userAccount);
    }

    private UserAccount createUserAccount(String username) {
        return createUserAccount(username, null);
    }

    private UserAccount createUserAccount(String userId, String createdBy) {
        return UserAccount.of(
                userId,
                "password",
                "e@mail.com",
                "nickname",
                "010-1234-5678",
                LocalDate.now(),
                'M',
                createdBy
        );
    }

    private UserAccount createSigningUpUserAccount(String username) {
        return createUserAccount(username, username);
    }
}
