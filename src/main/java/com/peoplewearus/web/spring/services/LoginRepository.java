package com.peoplewearus.web.spring.services;

import com.peoplewearus.web.spring.data.UserNotFoundException;
import com.peoplewearus.web.spring.domain.User;

public interface LoginRepository {

    public User findUser(String email, String password) throws UserNotFoundException;

}