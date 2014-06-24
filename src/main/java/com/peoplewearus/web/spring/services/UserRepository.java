package com.peoplewearus.web.spring.services;

import com.peoplewearus.web.spring.data.UserAlreadyExistsException;
import com.peoplewearus.web.spring.data.UserNotFoundException;
import com.peoplewearus.web.spring.domain.User;

import java.util.Collection;


public interface UserRepository {

    User getUser(String email);

    public void addUser(String firstName, String lastName, String co, String street, String zipCode, String city,
                        String country, String phone, String gender, String email, String password); //throws UserAlreadyExistsException;

    boolean authenticate(String userId, String password);

    public Collection<User> getAllUsers();

    public void updateUser(String firstName, String lastName, String co, String street, String postal, String city, String country, String phone, String gender, String email);

    User findUser(String email, String password) throws UserNotFoundException;

    boolean checkUser(String firstName, String lastName, String co, String street, String postal, String city,
                      String country, String phone, String gender, String email, String password) throws UserAlreadyExistsException;

}
