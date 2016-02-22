package com.KafanovAndRomanovich.user.controller;

import com.KafanovAndRomanovich.user.model.Post;
import com.KafanovAndRomanovich.user.model.Tag;
import com.KafanovAndRomanovich.user.model.User;
import com.KafanovAndRomanovich.user.service.PostService;
import com.KafanovAndRomanovich.user.service.TagService;
import com.KafanovAndRomanovich.user.service.UserService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.List;


@RestController
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    TagService tagService;

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public List<User> getPosts() {
        return userService.findAll();
    }

    @RequestMapping(value = "/banOrUnBanUser", method = RequestMethod.POST)
    public User banOrUnBanUser(@RequestBody Long id) {
        User user = userService.findOne(id);
        user.setBaned(!user.getBaned());
        userService.save(user);
        return user;
    }

    @RequestMapping(value = "/deletePostByAdmin", method = RequestMethod.POST)
    public User deletePostByAdmin(@RequestBody Long id) {
        Post post = postService.findOne(id);
        User user = userService.findOne(post.getUser().getId());
        List<Tag> tags = tagService.getAllTags(post.getId());
        for (Tag tag : tags) {
            tagService.deleteTagInPost(tag.getTagId(), post.getId());
        }
        user.deletePost(post);
        userService.save(user);
        return user;
    }

    @RequestMapping(value = "/savePostByAdmin", method = RequestMethod.POST)
    public
    @ResponseBody
    Integer savePostByAdmin(@RequestBody String data) throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Post post = mapper.readValue(data, Post.class);
        User user=userService.findOne(postService.findOne(post.getId()).getUser().getId());
        List<Tag> tags = tagService.getAllTags(post.getId());
        for (Tag tag : tags) {
            tagService.deleteTagInPost(tag.getTagId(), post.getId());
        }
        List<Tag> allTags = tagService.getAllResults();
        post = tagService.updateTags(post, allTags);
        post.setUser(user);
        user.updatePost(post);
        userService.save(user);
        return 200;
    }
}
