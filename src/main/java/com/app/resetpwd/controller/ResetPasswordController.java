package com.app.resetpwd.controller;

import com.app.resetpwd.model.User;
import com.app.resetpwd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ResetPasswordController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView showResetPassword(ModelAndView modelAndView) {
        modelAndView.clear();
        modelAndView.setViewName("resetpasswordform");
        return modelAndView;
    }

    @RequestMapping(value = "/resetpwd", method = RequestMethod.POST)
    public ModelAndView resetPassword(ModelAndView modelAndView, @RequestParam("email") String userEmail,
                                      @RequestParam("password") String password) {

        // Lookup user in database by e-mail
        Optional<User> optional = userService.findByEmailId(userEmail);

        if (!optional.isPresent()) {
            modelAndView.addObject("error", "E-mail address doesn't exists.");
            modelAndView.setViewName("resetpasswordform");
        } else {
            User user = optional.get();
            user.setPassword(password);
            userService.save(user);
            // Add success message to view
            modelAndView.addObject("message", "Password has been changed successfully " +
                    "for the e-mail address: " + userEmail);
            modelAndView.setViewName("resetPasswordSuccess");
        }

        return modelAndView;
    }
}
