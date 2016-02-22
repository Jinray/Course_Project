package com.KafanovAndRomanovich.user.service;

import com.KafanovAndRomanovich.user.model.Post;
import com.KafanovAndRomanovich.user.model.Rating;
import com.KafanovAndRomanovich.user.model.User;


public interface RatingService {
    Integer getScore(Post post);
    void saveOrDeleteRating(Rating rating, Post post,User user);
    Rating findByUserAndPost(User user,Post post);
}
