package com.KafanovAndRomanovich.user.controller;

import com.KafanovAndRomanovich.user.model.*;
import com.KafanovAndRomanovich.user.service.CommentService;
import com.KafanovAndRomanovich.user.service.LikesService;
import com.KafanovAndRomanovich.user.service.PostService;
import com.KafanovAndRomanovich.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 16.02.2016.
 */
@RestController
public class CommentController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private LikesService likesService;

    @RequestMapping(value = "/saveComment", method = RequestMethod.POST)
    public CommentPost saveComment(@RequestBody String comments, Principal principal) throws IOException, ParseException {
        if (principal != null) {
            ObjectMapper mapper = new ObjectMapper();
            CommentPost commentPost = mapper.readValue(comments, CommentPost.class);
            User user = userService.findUser(principal.getName());
            Post post = postService.findOne(commentPost.getId());
            Comment comment = new Comment();
            comment.setUser(user);
            comment.setText(commentPost.getText());
            comment.setPost(post);
            Date today = new Date();
            SimpleDateFormat format = new SimpleDateFormat("MMM d yyyy");
            String result = format.format(today);
            Date date = format.parse(result);
            comment.setDate(date);
            post.addComment(comment);
            postService.save(post);
            commentPost.setUser(user);
            commentPost.setDate(date);
            return commentPost;
        }
        return new CommentPost();//todo  and refactor
    }

    @RequestMapping(value = "/getComments", method = RequestMethod.POST)
    public List<CommentPost> getComments(@RequestBody Long id) {
        Post post = postService.findOne(id);
        List<CommentPost> result = new ArrayList<>();
        CommentPost commentPost;
        for (Comment comment : post.getComments()) {
            commentPost = new CommentPost();
            commentPost.setText(comment.getText());
            commentPost.setUser(comment.getUser());
            commentPost.setDate(comment.getDate());
            commentPost.setLikes(comment.getLikes());
            commentPost.setId(comment.getId());
            result.add(commentPost);
        }
        return result;
    }

    @RequestMapping(value = "/changeLikes", method = RequestMethod.POST)
    public List<Likes> changeLikes(@RequestBody Long id, Principal principal) {
        Comment comment = commentService.findOne(id);
        if (principal != null) {
            Likes like = new Likes();
            like.setComment(comment);
            like.setUser(userService.findUser(principal.getName()));
            likesService.saveOrDeleteLikes(like, comment);
        }
        return comment.getLikes();

    }
}
