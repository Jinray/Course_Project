package com.KafanovAndRomanovich.user.controller;

import com.KafanovAndRomanovich.user.model.Post;
import com.KafanovAndRomanovich.user.model.User;
import com.KafanovAndRomanovich.user.service.UserService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;


@RestController
public class AdminController {
    @Autowired
    UserService userService;


    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public List<User> getPosts() {
        return userService.findAll();
    }

    @RequestMapping(value = "/banOrUnBanUser", method = RequestMethod.POST)
    public User banOrUnBanUser(@RequestBody Long id) {
        User user=userService.findOne(id);
        user.setBaned(!user.getBaned());
        userService.save(user);
        return user;
    }
}
