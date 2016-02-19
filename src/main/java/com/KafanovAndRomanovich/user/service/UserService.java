package com.KafanovAndRomanovich.user.service;

import com.KafanovAndRomanovich.user.model.User;
import com.KafanovAndRomanovich.user.dto.RegistrationForm;


public interface UserService {

    /**
     * Creates a new user account to the service.
     * @param userAccountData   The information of the created user account.
     * @return  The information of the created user account.
     * @throws DuplicateEmailException Thrown when the email address is found from the database.
     */
    public User registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException;
    public User findUser(String name);
    public User updateUser(User user);
    User save(User user);
}
