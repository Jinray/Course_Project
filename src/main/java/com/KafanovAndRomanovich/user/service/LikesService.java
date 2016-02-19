package com.KafanovAndRomanovich.user.service;

import com.KafanovAndRomanovich.user.model.Comment;
import com.KafanovAndRomanovich.user.model.Likes;

/**
 * Created by Alex on 18.02.2016.
 */
public interface LikesService {
    void saveOrDeleteLikes(Likes likes, Comment comment);
}
