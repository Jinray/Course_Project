package com.KafanovAndRomanovich.user.repository;

import com.KafanovAndRomanovich.user.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Alex on 18.02.2016.
 */
public interface LikesRepository extends JpaRepository<Likes, Long> {
}
