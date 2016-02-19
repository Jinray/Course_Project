package com.KafanovAndRomanovich.user.service;

import com.KafanovAndRomanovich.user.model.Post;
import com.KafanovAndRomanovich.user.model.PostUser;

import java.util.List;


public interface PostService {
    Post findOne(Long id);
    Post save(Post post);
    List<Post> findAll();
    List<Post> findByCategory(String category);
    List<PostUser> getAllPosts(List<Post> posts);
}
