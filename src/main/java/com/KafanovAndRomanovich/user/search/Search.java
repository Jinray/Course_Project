package com.KafanovAndRomanovich.user.search;

import com.KafanovAndRomanovich.user.model.Post;

import java.util.List;


public interface Search {
    List<Post> search(String text);
}

