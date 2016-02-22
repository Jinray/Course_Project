package com.KafanovAndRomanovich.user.service;

import org.springframework.security.core.AuthenticationException;


public class AccountUserBannedException extends AuthenticationException {
    public AccountUserBannedException(String message) {
        super(message);
    }

}
