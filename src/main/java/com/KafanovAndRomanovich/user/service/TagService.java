package com.KafanovAndRomanovich.user.service;



import com.KafanovAndRomanovich.user.model.Post;
import com.KafanovAndRomanovich.user.model.Tag;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface TagService {
    void addTag(String tagValue, Long postId);

    List<Tag> getAllTags(Long postId);

    void deleteTagInPost(Long tagId, Long postId);

    int getWeight(Long tagId);

    void deleteTagInPostByText(String tagText, Long postId);

    List<Tag> getAllResults();

    List<Post> gatAllPostsByTag(String value);
    Tag save(Tag tag);
    Post updateTags(Post post,List<Tag> tags);
    Tag findByText(String text);

}
