package com.KafanovAndRomanovich.user.repository;

import com.KafanovAndRomanovich.user.model.Post;
import com.KafanovAndRomanovich.user.model.Rating;
import com.KafanovAndRomanovich.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Alex on 17.02.2016.
 */
public interface RatingRepository extends JpaRepository<Rating,Long>{
    Rating findByUserAndPost(User user, Post post);
}
