package com.KafanovAndRomanovich.user.service;


public class DuplicateEmailException extends Exception {

    public DuplicateEmailException(String message) {
        super(message);
    }
}
