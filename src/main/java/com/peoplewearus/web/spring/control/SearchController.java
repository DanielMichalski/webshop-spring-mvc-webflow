package com.peoplewearus.web.spring.control;

import com.peoplewearus.web.spring.domain.Product;
import com.peoplewearus.web.spring.services.ECommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private ECommerceService ecommerceService;

    @Autowired
    private ECommerceService searchManager;


    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ModelAndView showSearch() {
        return new ModelAndView("searchProduct");
    }


    @RequestMapping(value = "searchProduct")
    public
    @ResponseBody
    List<Product> performSearch(@RequestParam("CHARS") String chars) {

        if (chars.length() < 2) {
            return null;
        } else {

            return searchProductsByMatch(chars);
        }
    }


    public List<Product> searchProductsByMatch(String chars) {

        List<Product> returnList = new ArrayList<Product>();
        List<Product> testProducts = (List<Product>) ecommerceService.getAllProducts();


        for (Product next : testProducts) {
            if (next.getProductName().toLowerCase().contains(chars.toLowerCase())) {
                returnList.add(next);
            }
        }

        Collections.sort(returnList, new Comparator<Product>() {

            public int compare(Product p1, Product p2) {
                return p1.getProductName().compareTo(p1.getProductName());
            }
        });

        return returnList;

    }


}
