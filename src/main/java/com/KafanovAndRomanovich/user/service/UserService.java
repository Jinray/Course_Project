package com.KafanovAndRomanovich.user.service;

import com.KafanovAndRomanovich.user.model.User;
import com.KafanovAndRomanovich.user.dto.RegistrationForm;

import java.util.List;


public interface UserService {

    /**
     * Creates a new user account to the service.
     * @param userAccountData   The information of the created user account.
     * @return  The information of the created user account.
     * @throws DuplicateEmailException Thrown when the email address is found from the database.
     */
    User registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException;
    User findUser(String name);
    User findOne(Long id);
    User updateUser(User user);
    User save(User user);
    List<User> findAll();
}
