package com.KafanovAndRomanovich.user.repository;

import com.KafanovAndRomanovich.user.model.Achievement;
import com.KafanovAndRomanovich.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    List<Achievement> findByUser(User user);
}
