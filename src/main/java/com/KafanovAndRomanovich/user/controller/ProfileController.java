package com.KafanovAndRomanovich.user.controller;



import com.KafanovAndRomanovich.user.model.Post;
import com.KafanovAndRomanovich.user.model.User;

import com.KafanovAndRomanovich.user.service.UserService;
import com.cloudinary.Cloudinary;
import com.cloudinary.Url;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class ProfileController {

    private static String CLOUD_PROFILE="cloudinary://755363552657861:iq9Ne5O6BaM1Mxbd-ewBTFPjBRE@bbproject";
    @Autowired
    private UserService userService;

    Cloudinary cloudinary;

    @RequestMapping(value = "/getprofile", method = RequestMethod.GET)
    public User getProfile(Principal principal)
    {
        return userService.findUser(principal.getName());
    }


    @RequestMapping(value = "/saveprofile", method = RequestMethod.POST)
    public @ResponseBody
    String saveProfile(@RequestBody String data, Principal principal) throws IOException {

        if (principal != null)
        {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            User user = mapper.readValue(data, User.class);
            userService.updateUser(user);
            return "OK";
        }
        else return "";
    }

    @RequestMapping(value = "/saveavatar", method = RequestMethod.POST)
    public @ResponseBody
    Integer saveAvatar(@RequestBody String data, Principal principal) throws IOException {
        System.out.println(data);
        Cloudinary cloudinary=new Cloudinary(CLOUD_PROFILE);
        Map map=cloudinary.uploader().upload(data, ObjectUtils.emptyMap());
        String url= map.get("url").toString();
        User user=userService.findUser(principal.getName());
        user.setPhoto(url);
        userService.save(user);
        return 200;
    }

}
