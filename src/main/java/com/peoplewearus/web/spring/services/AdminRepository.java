package com.peoplewearus.web.spring.services;

import com.peoplewearus.web.spring.data.AdminNotFoundException;
import com.peoplewearus.web.spring.domain.Admin;

import java.util.Collection;


public interface AdminRepository {

    public Admin getAdmin(String email);

    public void addAdmin(String firstName, String lastName, String email, String password);

    Admin authenticate(String userId, String password) throws AdminNotFoundException;

    public Collection<Admin> getAllAdmins();

    public void updateAdmin(String password);

    public boolean checkAdmin(String firstName, String lastName, String email, String password);


}
