package com.peoplewearus.web.spring.control;

import com.peoplewearus.web.spring.domain.User;
import com.peoplewearus.web.spring.services.ECommerceService;
import com.peoplewearus.web.spring.validation.UserFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@Scope("session")
public class LoginController {

    @Autowired
    private UserFormValidator userFormValidator;

    @Autowired
    private User user = new User();

    @Autowired
    private ECommerceService ecommerceService;

    //Login
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView setupUserForm() {
        return new ModelAndView("userForm", "user", user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView submitUserForm(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        userFormValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ModelAndView("userForm");
        } else {

            redirectAttributes.addFlashAttribute("user", user);

            if (ecommerceService.login(user.getEmail(), user.getPassword())) {
                return new ModelAndView("redirect:login/confirmation");
            } else {
                return new ModelAndView("userForm");
            }

        }
    }

    @RequestMapping(value = "login/confirmation", method = RequestMethod.GET)
    public ModelAndView showConfirmation(@ModelAttribute("user") User user) {
        return new ModelAndView("userConfirmation", "user", user);
    }


    @RequestMapping("viewUser")
    public ModelAndView viewUser() {
        // hämta userForm från session

        return new ModelAndView("loginIndex", "user", user);
    }

}
