package com.example.loan.repository;

import com.example.loan.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
    UserAccount findByUserId(String userId);
}
