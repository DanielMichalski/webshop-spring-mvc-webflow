package com.peoplewearus.web.spring.control;

import com.peoplewearus.web.spring.domain.Orderline;
import com.peoplewearus.web.spring.domain.Product;
import com.peoplewearus.web.spring.domain.ShoppingCart;
import com.peoplewearus.web.spring.services.ECommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Scope("session")
@Controller
public class ShoppingCartController {
    @Autowired
    private ECommerceService ecommerceService;

    @Autowired
    private ShoppingCart cart = new ShoppingCart();

    // id ska vara int senare
    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    public ModelAndView addToCart(@RequestParam("id") String id) {
        Product product = ecommerceService.getProduct(id);
        cart.addItem(product);
        return new ModelAndView("productAddedToCart", "product", product);
    }

    @RequestMapping("/viewCart")
    public ModelAndView viewCart() {
        System.out.println("Hej från /viewCart controller");
        List<Orderline> allOrderlines = cart.getAllItems();
        return new ModelAndView("cartContents", "lines", allOrderlines);
    }

}
