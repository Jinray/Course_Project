package com.KafanovAndRomanovich.user.service;

import com.KafanovAndRomanovich.user.model.User;


public interface AchievementService {
    void addPostAchievement(User user);
    void addCommentAchievement(User user);
    void addRegisteredAchievement(User user);
    void addRatingAchievement(User user);
}
