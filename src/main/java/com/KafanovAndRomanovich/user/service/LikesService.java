package com.KafanovAndRomanovich.user.service;

import com.KafanovAndRomanovich.user.model.Comment;
import com.KafanovAndRomanovich.user.model.Likes;


public interface LikesService {
    void saveOrDeleteLikes(Likes likes, Comment comment);
}
