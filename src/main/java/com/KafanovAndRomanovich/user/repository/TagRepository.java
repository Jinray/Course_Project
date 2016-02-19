package com.KafanovAndRomanovich.user.repository;

import com.KafanovAndRomanovich.user.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Alex on 12.02.2016.
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByText(String text);
}
