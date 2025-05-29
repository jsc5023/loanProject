package com.example.loan.service;

import com.example.loan.domain.UserAccount;
import com.example.loan.dto.UserAccountDto;
import com.example.loan.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserAccountService implements UserDetailsService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUserId(userId);

        if (userAccount == null) {
            throw new UsernameNotFoundException("아이디에 해당하는 유저를 찾을 수 없습니다: " + userId);
        }

        // 권한이 필요 없다면 빈 리스트로 반환
        return new org.springframework.security.core.userdetails.User(
                userAccount.getUserId(),
                userAccount.getUserPassword(),
                new ArrayList<>()  // 권한 없이 빈 리스트
        );
    }

    @Transactional(readOnly = true)
    public Optional<UserAccountDto> searchUser(String username) {
        return userAccountRepository.findById(username)
                .map(UserAccountDto::from);
    }

    public UserAccountDto saveUser(String userId, String password, String email, String nickname, String phoneNumber, LocalDate birth_date, char gender) {
        return UserAccountDto.from(
                userAccountRepository.save(UserAccount.of(userId, password, email, nickname, phoneNumber, birth_date, gender))
        );
    }
}
