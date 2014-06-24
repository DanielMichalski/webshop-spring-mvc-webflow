package com.peoplewearus.web.spring.control;

import com.peoplewearus.web.spring.domain.Product;
import com.peoplewearus.web.spring.services.ECommerceService;
import com.peoplewearus.web.spring.validation.ProductFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collection;


@Controller
public class ProductController {
    @Autowired
    private ProductFormValidator productFormValidator;

    @Autowired
    private ECommerceService ecommerceService;

    // Confirmation Page
    @RequestMapping(value = "admin/createProductForm/confirmation", method = RequestMethod.GET)
    public ModelAndView showConfirmation(@ModelAttribute("product") Product product) {
        return new ModelAndView("productConfirmation", "product", product);
    }

    // Create Product
    @RequestMapping(value = "admin/createProductForm", method = RequestMethod.GET)
    public ModelAndView setupCreateProductForm() {
        return new ModelAndView("createProductForm", "product", new Product());
    }

    @RequestMapping(value = "admin/createProductForm", method = RequestMethod.POST)
    public ModelAndView submitCreateProductForm(@Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        productFormValidator.validate(product, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ModelAndView("createProductForm");
        } else {

            redirectAttributes.addFlashAttribute("productForm", product);

            boolean result = ecommerceService.checkProduct(product.getProductName(), product.getReferenceNumber(), product.getPrice(), product.getBrand());
            System.out.println(result);
            if (!result) {
                ecommerceService.addProduct(product.getProductName(), product.getReferenceNumber(), product.getPrice(), product.getBrand(), product.getDescription(), product.getGenre(), product.getState());
                return new ModelAndView("redirect:createProductForm/confirmation");
            } else {
                return new ModelAndView("createProductForm");
            }

        }
    }

    // Filips del get product

    @RequestMapping(value = "/getProductForm", method = RequestMethod.GET)
    public ModelAndView setupProductForm() {
        return new ModelAndView("getProductForm", "product", new Product());
    }

    @RequestMapping(value = "/getProductForm", method = RequestMethod.POST)
    public ModelAndView submitProductForm(@Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes) {


        if (product.getReferenceNumber() != null) {
            product = ecommerceService.getProduct(product.getReferenceNumber());
            redirectAttributes.addFlashAttribute("product", product);
            return new ModelAndView("redirect:getProductForm/display");
        } else {
            redirectAttributes.addFlashAttribute("product", product);
            return new ModelAndView("product");
        }

    }

    @RequestMapping(value = "getProductForm/display", method = RequestMethod.GET)
    public ModelAndView displayProductConfirmation(@ModelAttribute("product") Product product) {
        return new ModelAndView("displayProduct", "product", product);
    }

    // Display allProducts

    @RequestMapping(value = "/productsIndex", method = RequestMethod.GET)
    public ModelAndView setupIndex()

    {

        return new ModelAndView("displayAllProductsForm");
    }

    @RequestMapping(value = "/displayAllProductsForm", method = RequestMethod.GET)
    public ModelAndView setupDisplayAllProductsForm()

    {
        Collection<Product> products = ecommerceService.getAllProducts();
        return new ModelAndView("displayAllProducts", "products", products);
    }

    // Filips update Product

    @RequestMapping(value = "admin/updateProduct", method = RequestMethod.GET)
    public ModelAndView updateProductGet() {
        return new ModelAndView("getProduct", "product", new Product());
    }

    @RequestMapping(value = "admin/updateProduct", method = RequestMethod.POST)
    public ModelAndView updateProductPost(Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        return new ModelAndView("redirect:updateProduct/" + product.getReferenceNumber());
    }


    @RequestMapping(value = "admin/updateProduct/{productId}", method = RequestMethod.GET)
    public ModelAndView displayUpdateProduct(@PathVariable String productId, Model model) {
        Product product = ecommerceService.getProduct(productId);

        ModelAndView mv = new ModelAndView("displayUpdateProduct");

        mv.addObject("productForm", new Product());
        mv.addObject("product", product);

        return mv;
    }

    @RequestMapping(value = "admin/updateProduct/{productId}", method = RequestMethod.POST)
    public ModelAndView updateProduct(@PathVariable String productId, @Valid Product productForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        ecommerceService.updateProduct(productForm.getProductName(), productForm.getReferenceNumber(),
                productForm.getPrice(), productForm.getBrand(), productForm.getDescription(), productForm.getGenre(), productForm.getState());

        redirectAttributes.addFlashAttribute("product", productForm);
        return new ModelAndView("redirect:/admin/updateProduct/confirmation");
    }

    @RequestMapping(value = "admin/updateProduct/confirmation", method = RequestMethod.GET)
    public ModelAndView updateProductConfirmation(@ModelAttribute("product") Product product) {
        return new ModelAndView("updateProductConfirmation");
    }


    // PRODUCT CATEGORY

    @RequestMapping(value = "displayGenre/{category}", method = RequestMethod.GET)
    public ModelAndView displayProductByCategory(@PathVariable String category, Model model) {

        return new ModelAndView("displayAllProducts", "products", ecommerceService.getProductByCategory(category));

    }


}