package com.KafanovAndRomanovich.user.service;

import com.KafanovAndRomanovich.user.model.Achievement;
import com.KafanovAndRomanovich.user.model.User;

/**
 * Created by Alex on 19.02.2016.
 */
public interface AchievementService {
    void addPostAchievement(User user);
    void addCommentAchievement(User user);
    void addRegisteredAchievement(User user);
    void addRatingAchievement(User user);
}
