package com.peoplewearus.web.spring.control;

import com.peoplewearus.web.spring.domain.Admin;
import com.peoplewearus.web.spring.services.ECommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collection;

@Controller
public class AdminController {

    @Autowired
    private ECommerceService ecommerceService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView setupAdminLogin() {
        return new ModelAndView("welcomeAdmin");
    }

    @RequestMapping(value = "/admin/index", method = RequestMethod.GET)
    public ModelAndView setupAdminIndex() {
        Collection<Admin> admins = ecommerceService.getAllAdmins();
        return new ModelAndView("adminIndex", "admins", admins);
    }

    // CREATE ADMIN

    @RequestMapping(value = "admin/createAdmin/confirmation", method = RequestMethod.GET)
    public ModelAndView showConfirmation(@ModelAttribute("admin") Admin admin) {
        return new ModelAndView("createAdminConfirmation", "admin", admin);
    }

    @RequestMapping(value = "admin/createAdmin", method = RequestMethod.GET)
    public ModelAndView setupCreateProductForm() {
        return new ModelAndView("createAdmin", "admin", new Admin());
    }

    @RequestMapping(value = "admin/createAdmin", method = RequestMethod.POST)
    public ModelAndView submitCreateProductForm(@Valid Admin admin, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        //admin validator
        //productFormValidator.validate(product, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ModelAndView("createAdmin");
        } else {

            redirectAttributes.addFlashAttribute("admin", admin);

            boolean result = ecommerceService.checkAdmin(admin.getEmail(), admin.getFirstName(), admin.getLastName(), admin.getPassword());
            if (result) {
                return new ModelAndView("createAdmin");
            } else {
                ecommerceService.addAdmin(admin.getEmail(), admin.getFirstName(), admin.getLastName(), admin.getPassword());
                return new ModelAndView("redirect:createAdmin/confirmation");
            }
        }


    }
}
