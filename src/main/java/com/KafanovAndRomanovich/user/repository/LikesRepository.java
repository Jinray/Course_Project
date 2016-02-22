package com.KafanovAndRomanovich.user.repository;

import com.KafanovAndRomanovich.user.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LikesRepository extends JpaRepository<Likes, Long> {
}
