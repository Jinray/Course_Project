package com.KafanovAndRomanovich.user.service;

import com.KafanovAndRomanovich.user.model.Comment;

/**
 * Created by Alex on 14.02.2016.
 */
public interface CommentService {
    Comment findOne(Long id);
    Comment save(Comment comment);
}
