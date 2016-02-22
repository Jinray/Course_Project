package com.KafanovAndRomanovich.user.repository;

import com.KafanovAndRomanovich.user.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment,Long> {
}
