package com.ncarignan.pumas.controllers;

import com.ncarignan.pumas.models.ApplicationUser;
import com.ncarignan.pumas.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public RedirectView createNewApplicationUser(String username, String password){
        System.out.println("You are adding a user");
        // make the user AND salt and hash the password
        // this does the salting and hashing for you
        ApplicationUser newUser = new ApplicationUser(username, passwordEncoder.encode(password));

        // save the user to db
        applicationUserRepository.save(newUser);

        // send them back home
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    @GetMapping("/users/{id}")
    public String showUserDetails(@PathVariable long id, Principal p, Model m){
        ApplicationUser theUser = applicationUserRepository.findById(id).get();
        // set the attribute on Model
        m.addAttribute("usernameWeAreVisiting", theUser.getUsername());
        m.addAttribute("userIdWeAreVisiting", theUser.id);
        m.addAttribute("userWeAreVisiting", theUser);
        m.addAttribute("principalTheAndroid", p.getName());
        return "userDetails";
    }

}
