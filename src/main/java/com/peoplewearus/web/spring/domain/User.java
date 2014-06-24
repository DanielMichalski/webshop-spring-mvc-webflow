package com.peoplewearus.web.spring.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Scope("session")
public class User implements Serializable {
    private static final long serialVersionUID = -7181300965357147471L;

    private String firstName;

    private String lastName;

    private String co;

    private String street;

    private String postal;

    private String city;

    private String country;

    private String phone;

    private String gender;

    private String email;

    private String password;


    public User(String firstName, String lastName, String co, String street, String postal, String city, String country, String phone, String gender, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.co = co;
        this.street = street;
        this.postal = postal;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.gender = gender;
        this.email = email;
        this.password = password;

    }

    public User() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
