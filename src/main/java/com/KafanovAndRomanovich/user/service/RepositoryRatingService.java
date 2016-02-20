package com.KafanovAndRomanovich.user.service;

import com.KafanovAndRomanovich.user.model.Post;
import com.KafanovAndRomanovich.user.model.Rating;
import com.KafanovAndRomanovich.user.model.User;
import com.KafanovAndRomanovich.user.repository.PostRepository;
import com.KafanovAndRomanovich.user.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Alex on 17.02.2016.
 */
@Service
@Transactional
public class RepositoryRatingService implements RatingService {

    RatingRepository ratingRepository;

    @Autowired
    public RepositoryRatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }
    public Integer getScore(Post post) {
        Integer result = 0;
        for (Rating rait : post.getRatings()) {
            if (rait.getPositive()) {
                result++;
            } else result--;
        }
        return result;
    }

    @Override
    public void saveOrDeleteRating(Rating rating, Post post,User user) {
        for (Rating rait : post.getRatings()) {
            if (rating.getUser().getId().equals(rait.getUser().getId())) {
                if (rating.getPositive() == rait.getPositive())
                    return;
                else {
                    post.removeRating(rait);
                    return;
                }
            }
        }
        ratingRepository.save(rating);
        post.addRating(rating);
    }

    @Override
    public Rating findByUserAndPost(User user, Post post) {
        return ratingRepository.findByUserAndPost(user,post);
    }


}
