package com.KafanovAndRomanovich.user.controller;

import com.KafanovAndRomanovich.user.model.Tag;
import com.KafanovAndRomanovich.user.service.PostService;
import com.KafanovAndRomanovich.user.service.TagService;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alex on 12.02.2016.
 */
@RestController
public class TagController {

    @Autowired
    PostService postService;

    @Autowired
    TagService tagService;

    @RequestMapping(value = "/get-tags-list",method = RequestMethod.GET)
    public List<Tag> getAllTags(){
        List<Tag> tags=tagService.getAllResults();
        return tags;
    }
    @RequestMapping(value = "/get-tags",method = RequestMethod.GET)
    public ArrayList<Long> getPostTagIds(@RequestParam Long postId){
        List<Tag> allTags= postService.findOne(postId).getTags();
        ArrayList<Long> tagIds = new ArrayList<Long>();
        for(Tag tag : allTags ){
            if(tag.getWeight()!=0)
                tagIds.add(tag.getTagId());
        }
        return tagIds;
    }
    @RequestMapping(value = "/getCloudTags",method = RequestMethod.GET)
    public List<Tag> getCloudTags(){
        List<Tag> tags=tagService.getAllResults();
        Collections.sort(tags);
        try {
            tags = tags.subList(0,15);
            Collections.shuffle(tags);
            return tags;
        }catch (IndexOutOfBoundsException e){
            Collections.shuffle(tags);
            return tags;
        }
    }
}


