package com.KafanovAndRomanovich.user.repository;

import com.KafanovAndRomanovich.user.model.Achievement;
import com.KafanovAndRomanovich.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Alex on 19.02.2016.
 */
public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    List<Achievement> findByUser(User user);
}
