package com.peoplewearus.web.spring.services;

import com.peoplewearus.web.spring.data.AdminNotFoundException;
import com.peoplewearus.web.spring.data.UserAlreadyExistsException;
import com.peoplewearus.web.spring.data.UserNotFoundException;
import com.peoplewearus.web.spring.domain.Admin;
import com.peoplewearus.web.spring.domain.Product;
import com.peoplewearus.web.spring.domain.User;

import java.util.Collection;
import java.util.List;


public final class ECommerceService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final AdminRepository adminRepository;
    private final OrderRepository orderRepository;

    public ECommerceService(UserRepository userRepository, ProductRepository productRepository, AdminRepository adminRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.adminRepository = adminRepository;
        this.orderRepository = orderRepository;

    }

    public User findUser(String email, String password) throws UserNotFoundException {
        return userRepository.findUser(email, password);
    }

    public User getUser(final String email) {
        return userRepository.getUser(email);
    }

    public boolean login(final String email, final String password) {
        return userRepository.authenticate(email, password);
    }

    public void addUser(String firstName, String lastName, String co, String street, String postal, String city,
                        String country, String phone, String gender, String email, String password) //throws UserAlreadyExistsException
    {
        userRepository.addUser(firstName, lastName, co, street, postal, city, country, phone, gender, email, password);
    }

    public Collection<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void updateUser(String firstName, String lastName, String co, String street, String postal, String city, String country, String phone, String gender, String email) {
        userRepository.updateUser(firstName, lastName, co, street, postal, city, country, phone, gender, email);
    }


    //Product del

    public Product getProduct(final String referenceNumber) {
        return productRepository.getProduct(referenceNumber);
    }

    public void addProduct(String productName, String referenceNumber, int price, String brand, String description, String genre, String state) {
        productRepository.addProduct(productName, referenceNumber, price, brand, description, genre, state);
    }

    public Collection<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public void updateProduct(String productName, String referenceNumber, int price, String brand, String description, String genre, String state) {
        productRepository.updateProduct(productName, referenceNumber, price, brand, description, genre, state);
    }


    // Admin

    public Admin getAdmin(final String email) {
        return adminRepository.getAdmin(email);
    }

    public Admin adminLogin(final String email, final String password) throws AdminNotFoundException {
        return adminRepository.authenticate(email, password);
    }

    public void addAdmin(String firstName, String lastName, String email, String password) {
        adminRepository.addAdmin(firstName, lastName, email, password);
    }

    public Collection<Admin> getAllAdmins() {
        return adminRepository.getAllAdmins();
    }

    public void updateAdmin(String password) {
        adminRepository.updateAdmin(password);
    }

    public boolean checkAdmin(String email, String firstName, String lastName, String password) {
        return adminRepository.checkAdmin(email, firstName, lastName, password);
    }

    public boolean checkProduct(String productName, String referenceNumber, int price, String brand) {
        return productRepository.checkProduct(productName, referenceNumber, price, brand);
    }

    public boolean checkUser(String firstName, String lastName, String co, String street, String postal, String city,
                             String country, String phone, String gender, String email, String password) throws UserAlreadyExistsException {
        return userRepository.checkUser(firstName, lastName, co, street, postal, city,
                country, phone, gender, email, password);
    }

    public List<Product> getProductByCategory(String category) {
        return productRepository.getProductsByCategory(category);
    }


}
