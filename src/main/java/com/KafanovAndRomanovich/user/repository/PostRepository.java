package com.KafanovAndRomanovich.user.repository;

import com.KafanovAndRomanovich.user.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByCategory(String category);
}
