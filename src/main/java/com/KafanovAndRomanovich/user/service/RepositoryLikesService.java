package com.KafanovAndRomanovich.user.service;

import com.KafanovAndRomanovich.user.model.Comment;
import com.KafanovAndRomanovich.user.model.Likes;
import com.KafanovAndRomanovich.user.repository.LikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Alex on 18.02.2016.
 */
@Service
@Transactional
public class RepositoryLikesService implements LikesService {

    LikesRepository likesRepository;

    @Autowired
    public RepositoryLikesService(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }

    @Override
    public void saveOrDeleteLikes(Likes likes, Comment comment) {
        for (Likes like :comment.getLikes()) {
            if (like.getUser().getId().equals(likes.getUser().getId())) {
                comment.removeLike(like);
                //likesRepository.delete(like);
                comment.removeLike(like);
                return;
            }
        }
        likesRepository.save(likes);
        comment.addLike(likes);
    }
}
