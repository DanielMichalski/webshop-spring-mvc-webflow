package com.peoplewearus.web.spring.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import java.io.Serializable;


public class Product implements Serializable {
    private static final long serialVersionUID = 5135828214369975191L;
    @NotEmpty
    private String productName;
    @Min(value = 0)
    private int price;
    @NotEmpty
    private String brand;
    @NotEmpty
    private String referenceNumber;
    @NotEmpty
    private String description;
    @NotEmpty
    private String genre;
    @NotEmpty
    private String state;

    public Product(String referenceNumber, String productName, int price, String brand, String description, String genre, String state) {
        this.productName = productName;
        this.referenceNumber = referenceNumber;
        this.price = price;
        this.brand = brand;
        this.description = description;
        this.genre = genre;
        this.state = state;
    }

    public Product() {

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
