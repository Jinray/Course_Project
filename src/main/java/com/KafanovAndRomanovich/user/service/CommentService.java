package com.KafanovAndRomanovich.user.service;

import com.KafanovAndRomanovich.user.model.Comment;


public interface CommentService {
    Comment findOne(Long id);
    Comment save(Comment comment);
}
