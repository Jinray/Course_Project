package com.KafanovAndRomanovich.user.service;

import com.KafanovAndRomanovich.user.model.Comment;
import com.KafanovAndRomanovich.user.model.Post;
import com.KafanovAndRomanovich.user.repository.CommentRepository;
import com.KafanovAndRomanovich.user.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RepositoryCommentService implements CommentService {

    PostRepository postRepository;
    CommentRepository commentRepository;


    @Autowired
    public RepositoryCommentService(PostRepository postRepository,CommentRepository commentRepository) {
        this.postRepository=postRepository;
        this.commentRepository=commentRepository;
    }


    @Override
    public Comment findOne(Long id) {
        return commentRepository.findOne(id);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
