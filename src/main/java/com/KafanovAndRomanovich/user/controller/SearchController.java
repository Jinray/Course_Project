package com.KafanovAndRomanovich.user.controller;

import com.KafanovAndRomanovich.user.model.Post;
import com.KafanovAndRomanovich.user.model.PostUser;
import com.KafanovAndRomanovich.user.search.Search;
import com.KafanovAndRomanovich.user.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 17.02.2016.
 */
@RestController
public class SearchController {

    @Autowired
    private Search postSearchImpl;
    @Autowired
    private PostService postService;

    @RequestMapping(value="/search",method = RequestMethod.POST)
    public List<PostUser> search(@RequestBody String search) {
        List<Post> searchResults = new ArrayList<>();
        searchResults.addAll(postSearchImpl.search(search));
        List<PostUser> result=postService.getAllPosts(searchResults);
        return result;
    }


}
