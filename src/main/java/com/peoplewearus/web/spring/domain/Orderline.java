package com.peoplewearus.web.spring.domain;

import java.io.Serializable;

public class Orderline implements Serializable {
    private static final long serialVersionUID = 154671656247049791L;
    Product product;
    private int quantity = 0;
    private int totalPrice;


    public Orderline(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        totalPrice = product.getPrice() * quantity;
        return totalPrice;
    }

}
