package com.KafanovAndRomanovich.user.search;

import com.KafanovAndRomanovich.user.model.Post;

import java.util.List;

/**
 * Created by Alex on 17.02.2016.
 */
public interface Search {
    List<Post> search(String text);
}

