package com.KafanovAndRomanovich.user.repository;

import com.KafanovAndRomanovich.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
