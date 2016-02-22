package com.KafanovAndRomanovich.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class SignInController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignInController.class);

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String showStartPage() {
        LOGGER.debug("Rendering SignIn page");

        return "redirect:user/signIn";
    }
}
