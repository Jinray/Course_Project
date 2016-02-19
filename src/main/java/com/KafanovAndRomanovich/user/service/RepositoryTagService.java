package com.KafanovAndRomanovich.user.service;

import com.KafanovAndRomanovich.user.model.Post;
import com.KafanovAndRomanovich.user.model.Tag;
import com.KafanovAndRomanovich.user.repository.PostRepository;
import com.KafanovAndRomanovich.user.repository.TagRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by Alex on 12.02.2016.
 */
@Service
@Transactional
public class RepositoryTagService implements TagService {


    TagRepository tagRepository;
    PostRepository postRepository;

    @Autowired
    public RepositoryTagService(PostRepository postRepository,TagRepository tagRepository) {
        this.tagRepository=tagRepository;
        this.postRepository=postRepository;
    }

    @Override
    public void addTag(String tagValue, Long tagId){
        Tag tag = tagRepository.findByText(tagValue);
        Post post = postRepository.findOne(tagId);
        tag = nullTagCheck(tagValue, tag);
        addPostAndTag(tag, post);
        saveTagAndPost(tag, post);
    }

    private Tag nullTagCheck(String tagValue, Tag tag) {
        if(tag == null) {
            tag = new Tag();
            tag.setText(tagValue);
        }
        return tag;
    }

    private void saveTagAndPost(Tag tag, Post post) {
        tagRepository.save(tag);
        postRepository.save(post);
    }

    private void addPostAndTag(Tag tag, Post post) {
        generateTag(tag, post);
        List<Tag> tags = post.getTags();
        tags.add(tag);
        post.setTags(tags);
    }

    private void generateTag(Tag tag, Post post) {
        List<Post> posts = tag.getPosts();
        posts.add(post);
        tag.setPosts(posts);
        tag.setWeight(posts.size());
    }

    @Override
    public List<Tag> getAllTags(Long postId) {
        Post post = postRepository.findOne(postId);
        return post.getTags();
    }

    @Override
    public void deleteTagInPost(Long tagId, Long postId){
        Tag tag = tagRepository.findOne(tagId);
        deleteTag(postId, tag);
    }

    private void deleteTag(Long postId, Tag tag) {
        Post post = postRepository.findOne(postId);
        decrementWeight(tag, post);
        List<Tag> tags = post.getTags();
        tags.remove(tag);
        if(tag.getWeight()!=0)
            saveTagAndPost(tag, post);
        else tagRepository.delete(tag);
    }

    private void decrementWeight(Tag tag, Post post) {
        List<Post> posts = tag.getPosts();
        posts.remove(post);
        tag.setWeight(posts.size());

    }

    @Override
    public void deleteTagInPostByText(String tagText , Long postId){
        Tag tag = tagRepository.findByText(tagText);
        deleteTag(postId, tag);
    }

    @Override
    public List<Tag> getAllResults() {
        return tagRepository.findAll();
    }

    @Override
    public List<Post> gatAllPostsByTag(String text) {
        return tagRepository.findByText(text).getPosts();
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }



    @Override
    public int getWeight(Long tagId) {
        Tag tag = tagRepository.findOne(tagId);
        return tag.getWeight();
    }
    @Override
    public Post updateTags(Post post,List<Tag> tags){
        for (Tag postTag : post.getTags()) {
            for (Tag tag : tags) {
                if (tag.getText().equals(postTag.getText())) {
                    postTag.setPosts(tag.getPosts());
                    postTag.setTagId(tag.getTagId());
                }
            }
            postTag.getPosts().add(post);
            postTag.setWeight(postTag.getPosts().size());
            save(postTag);
        }
        return post;
    }

    @Override
    public Tag findByText(String text) {
        return tagRepository.findByText(text);
    }
}
