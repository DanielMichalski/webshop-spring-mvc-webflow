package com.peoplewearus.web.spring.control;

import com.peoplewearus.web.spring.data.UserAlreadyExistsException;
import com.peoplewearus.web.spring.domain.User;
import com.peoplewearus.web.spring.services.ECommerceService;
import com.peoplewearus.web.spring.validation.UserFormValidator;
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
public class UserController {
    @Autowired
    private UserFormValidator userFormValidator;

    @Autowired
    private ECommerceService ecommerceService;

    // Create User

    @RequestMapping(value = "/createUserForm", method = RequestMethod.GET)
    public ModelAndView setupCreateUserForm() {
        return new ModelAndView("createUserForm", "user", new User());
    }

    @RequestMapping(value = "/createUserForm", method = RequestMethod.POST)
    public ModelAndView submitCreateUserForm(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws UserAlreadyExistsException {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("createUserForm");
        } else {

            redirectAttributes.addFlashAttribute("user", user);

            boolean result = ecommerceService.checkUser(user.getFirstName(), user.getLastName(), user.getCo(), user.getStreet(), user.getPostal(), user.getCity(), user.getCountry(), user.getPhone(), user.getGender(), user.getEmail(), user.getPassword());
            if (!result) {

                ecommerceService.addUser(user.getFirstName(), user.getLastName(), user.getCo(), user.getStreet(), user.getPostal(), user.getCity(), user.getCountry(), user.getPhone(), user.getGender(), user.getEmail(), user.getPassword());
                return new ModelAndView("redirect:createUserForm/confirmation");
            } else {
                return new ModelAndView("createUserForm");
            }

        }
    }

    @RequestMapping(value = "/createUserForm/confirmation", method = RequestMethod.GET)
    public ModelAndView setupCreateUserFormConfirmation(@ModelAttribute("user") User user) {
        return new ModelAndView("userFormConfirmation", "user", user);
    }

    // Alex: Display User

    @RequestMapping(value = "/getUserForm", method = RequestMethod.GET)
    public ModelAndView setupDisplayUserForm() {
        return new ModelAndView("getUserForm", "User", new User());
    }

    @RequestMapping(value = "/getUserForm", method = RequestMethod.POST)
    public ModelAndView submitDisplayUserForm(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (ecommerceService.getUser(user.getEmail()) != null) {
            user = ecommerceService.getUser(user.getEmail());
            redirectAttributes.addFlashAttribute("user", user);
            return new ModelAndView("redirect:getUserForm/display");
        } else {

            redirectAttributes.addFlashAttribute("getUserForm", user);
            return new ModelAndView("getUserForm");
        }

    }

    @RequestMapping(value = "getUserForm/display", method = RequestMethod.GET)
    public ModelAndView showDisplay(@ModelAttribute("userForm") User user) {
        return new ModelAndView("displayUser", "user", user);
    }


    // Display allUser

    @RequestMapping(value = "/usersIndex", method = RequestMethod.GET)
    public ModelAndView setupIndex()

    {

        return new ModelAndView("displayAllUsersForm");
    }

    @RequestMapping(value = "/displayAllUsersForm", method = RequestMethod.GET)
    public ModelAndView setupDisplayAllUsersForm()

    {
        Collection<User> users = ecommerceService.getAllUsers();
        return new ModelAndView("displayAllUsers", "users", users);
    }

    // update user

    @RequestMapping(value = "/updateUser/{userEmail}", method = RequestMethod.GET)
    public ModelAndView displayUpdateUser(@PathVariable String userEmail, Model model) {
        User user = ecommerceService.getUser(userEmail);

        // FIXA FIXA FIXA

        ModelAndView mv = new ModelAndView("displayUpdateUser");
        //	mv.addObject("userForm", new UserForm());
        mv.addObject("user", user);

        return mv;
    }

    @RequestMapping(value = "/updateUser/{userId}", method = RequestMethod.POST)
    public ModelAndView updateUser(@PathVariable String userId, @Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        ecommerceService.updateUser(user.getFirstName(), user.getLastName(),
                user.getCo(), user.getStreet(), user.getPostal(), user.getCity(), user.getCountry(),
                user.getPhone(), user.getGender(), user.getEmail());

        return new ModelAndView("redirect:/updateUser/" + user.getEmail());
    }

}
