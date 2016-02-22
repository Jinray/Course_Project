package com.KafanovAndRomanovich.user.repository;

import com.KafanovAndRomanovich.user.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByText(String text);
}
