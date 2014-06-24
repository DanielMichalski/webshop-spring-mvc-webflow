package com.peoplewearus.web.spring.domain;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;


public class Order implements Serializable {
    private static final long serialVersionUID = 9098365034246796265L;
    private List<Orderline> productsOrdered;
    private User user;
    private int id;
    private String state;

    public Order() {
        this.user = new User();
    }

    public void removeItem(String id) {
        Iterator<Orderline> it = productsOrdered.iterator();
        while (it.hasNext()) {
            Orderline next = it.next();
            if (next.getProduct().getReferenceNumber().equals(id)) {
                it.remove();
            }
        }
    }

    public void changeQuantityItem(String referenceNumber, String quant) {
        int intQuant = Integer.parseInt(quant);
        Iterator<Orderline> it = productsOrdered.iterator();
        while (it.hasNext()) {
            Orderline line = it.next();
            if (line.getProduct().getReferenceNumber().equals(referenceNumber)) {
                line.setQuantity(intQuant);
            }
        }
    }

    public void setProductsOrdered(List<Orderline> productsOrdered) {
        this.productsOrdered = productsOrdered;
    }

    public void setUser(User userDetails) {
        this.user = userDetails;
    }

    public List<Orderline> getProductsOrdered() {
        System.out.println("hej från getProductsOrdered");
        return productsOrdered;
    }

    public User getUser() {
        return user;
    }

    public String toString() {
        return "Order id for " + user.getFirstName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


}
