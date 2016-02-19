package com.KafanovAndRomanovich.user.repository;

import com.KafanovAndRomanovich.user.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Alex on 14.02.2016.
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
