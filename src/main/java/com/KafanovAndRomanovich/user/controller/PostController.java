package com.KafanovAndRomanovich.user.controller;

import com.KafanovAndRomanovich.user.model.*;
import com.KafanovAndRomanovich.user.service.PostService;
import com.KafanovAndRomanovich.user.service.RatingService;
import com.KafanovAndRomanovich.user.service.TagService;
import com.KafanovAndRomanovich.user.service.UserService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.cloudinary.json.JSONObject;
import org.hibernate.mapping.*;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 10.02.2016.
 */
@RestController
public class PostController {

    private static final String CLOUD_PROFILE = "cloudinary://755363552657861:iq9Ne5O6BaM1Mxbd-ewBTFPjBRE@bbproject";

    @Autowired
    private UserService userService;
    @Autowired
    private TagService tagService;
    @Autowired
    private PostService postService;
    @Autowired
    private RatingService ratingService;

    private Cloudinary cloud;

    @RequestMapping(value = "/getposts", method = RequestMethod.GET)
    public List<Post> getPosts(Principal principal) {
        User user = userService.findUser(principal.getName());
        List<Post> result = user.getPosts();
        Collections.reverse(result);
        return result;
    }

    @RequestMapping(value = "/getPopularPosts", method = RequestMethod.GET)
    public List<PostUser> getPopularPosts() {
        List<Post> posts = postService.findAll();
        List<PostUser> result = postService.getAllPosts(posts);
        Collections.sort(result);
        try {
            return result.subList(0, 5);
        } catch (IndexOutOfBoundsException e) {
            return result;
        }
    }

    @RequestMapping(value = "/savepost", method = RequestMethod.POST)
    public
    @ResponseBody
    Integer savePost(@RequestBody String data, Principal principal) throws IOException, ParseException {

        if (principal != null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Post post = mapper.readValue(data, Post.class);
            if (post.getId() == null) {
                List<Tag> tags = tagService.getAllResults();
                post = tagService.updateTags(post, tags);
            } else {
                List<Tag> tags = tagService.getAllTags(post.getId());
                for (Tag tag : tags) {
                    tagService.deleteTagInPost(tag.getTagId(), post.getId());
                }
                List<Tag> allTags = tagService.getAllResults();
                post = tagService.updateTags(post, allTags);
            }
            User user = userService.findUser(principal.getName());
            post.setUser(user);
            user.updatePost(post);
            userService.save(user);
            return 200;
        } else return 404;
    }


    @RequestMapping(value = "/deletepost", method = RequestMethod.POST)
    public
    @ResponseBody
    String deletePost(@RequestBody String posted, Principal principal) throws IOException {
        if (principal != null) {
            ObjectMapper mapper = new ObjectMapper();
            Post post = mapper.readValue(posted, Post.class);
            List<Tag> tags = tagService.getAllTags(post.getId());
            for (Tag tag : tags) {
                tagService.deleteTagInPost(tag.getTagId(), post.getId());
            }
            User user = userService.findUser(principal.getName());
            post.setUser(user);
            user.deletePost(post);
            userService.save(user);
            return "OK";
        } else return "";
    }

    @RequestMapping(value = "/saveimage", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    String saveImage(@RequestBody String data, Principal principal) throws IOException {


        Cloudinary cloudinary = new Cloudinary(CLOUD_PROFILE);
        Map map = cloudinary.uploader().upload(data, ObjectUtils.emptyMap());
        String url = map.get("url").toString();
        JSONObject obj = new JSONObject();
        obj.put("data", url);
        return obj.toString();

    }

    @RequestMapping(value = "/getSinglePost", method = RequestMethod.POST)
    public Post getSinglePost(@RequestBody Long id) {
        return postService.findOne(id);

    }

    @RequestMapping(value = "/getUserName", method = RequestMethod.POST)
    public String getUserName(@RequestBody Long id) {
        JSONObject obj = new JSONObject();
        Post post = postService.findOne(id);
        obj.put("name", post.getUser().getFirstName() + " " + post.getUser().getLastName());
        obj.put("userId", post.getUser().getId());
        return obj.toString();

    }

    @RequestMapping(value = "/getAllPosts", method = RequestMethod.GET)
    public List<PostUser> getAllPosts() {
        List<Post> posts = postService.findAll();
        List<PostUser> result = postService.getAllPosts(posts);
        Collections.reverse(result);
        return result;
    }

    @RequestMapping(value = "/getCategoryPosts", method = RequestMethod.POST)
    public List<PostUser> getCategoryPosts(@RequestBody String category) {
        List<Post> posts = postService.findByCategory(category);
        List<PostUser> result = postService.getAllPosts(posts);
        Collections.reverse(result);
        return result;
    }

    @RequestMapping(value = "/getTagsPost", method = RequestMethod.POST)
    public List<PostUser> getTagsPosts(@RequestBody String tags) {
        Tag tag = tagService.findByText(tags);
        List<Post> posts = tag.getPosts();
        List<PostUser> result = postService.getAllPosts(posts);
        Collections.reverse(result);
        return result;
    }

    @RequestMapping(value = "/changeRating", method = RequestMethod.POST)
    public Integer changeRating(@RequestBody String ratings, Principal principal) throws IOException {
        if (principal != null) {
            ObjectMapper mapper = new ObjectMapper();
            PostRating postRating = mapper.readValue(ratings, PostRating.class);
            Post post = postService.findOne(postRating.getPostId());
            User user = userService.findUser(principal.getName());
            Rating rating = new Rating();
            rating.setUser(user);
            rating.setPost(post);
            rating.setPositive(postRating.isPositive());
            ratingService.saveOrDeleteRating(rating, post);
            int result = ratingService.getScore(post);
            return result;
        }
        return null;
    }

    @RequestMapping(value = "/getRating", method = RequestMethod.POST)
    public Integer getRating(@RequestBody Long id) {
        Post post = postService.findOne(id);
        int result = ratingService.getScore(post);
        return result;

    }

    @RequestMapping(value = "/getPersonalRating", method = RequestMethod.POST)
    public
    @ResponseBody
    Rating getPersonalRating(@RequestBody Long id, Principal principal) {
        if (principal != null) {
            Post post = postService.findOne(id);
            User user = userService.findUser(principal.getName());
            Rating rating = ratingService.findByUserAndPost(user, post);
            return rating;
        }
        return null;
    }

    @RequestMapping(value = "/getUserHomePagePosts", method = RequestMethod.POST)
    public List<Post> getUserHomePagePosts(@RequestBody Long id) {
        User user = userService.findOne(id);
        List<Post> result = user.getPosts();
        Collections.reverse(result);
        return result;
    }


    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public User getUserInfo(@RequestBody Long id) {
        User user = userService.findOne(id);
        return user;

    }


}