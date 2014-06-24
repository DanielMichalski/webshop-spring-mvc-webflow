package com.peoplewearus.web.spring.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("session")
public class ShoppingCart implements Serializable {
    private static final long serialVersionUID = -6571914213989137573L;
    //private List<Product> shopping = new ArrayList<Product>();
    private List<Orderline> lines = new ArrayList<Orderline>();

    public void addItem(Product newItem) {
        java.util.Iterator<Orderline> iterator = lines.iterator();
        boolean success = false;

        while (iterator.hasNext()) {
            Orderline orderl = iterator.next();
            if (orderl.getProduct().getReferenceNumber().equals(newItem.getReferenceNumber())) {

                int quant = orderl.getQuantity() + 1;
                orderl.setQuantity(quant);

                success = true;
            }
        }

        if (success == false) {
            Orderline orderline = new Orderline(newItem, 1);
            lines.add(orderline);
        }


    }

    public void removeOneItem() {

    }

    public List<Orderline> getAllItems() {
        System.out.println("Hej från ShoppingCart getAllItems");
        return lines;
    }

    public void clear() {
        this.lines.clear();
    }


    public void addOrderline(Orderline line) {
        lines.add(line);
    }

}
