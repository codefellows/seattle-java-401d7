package com.soyewale.codefellowship.controllers;

import com.soyewale.codefellowship.models.ApplicationUser;
import com.soyewale.codefellowship.models.ApplicationUserRepository;
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
    public RedirectView createNewApplicationUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio){
       //make new user, salt and has the password
        //passwordEncoder.encode(password) salts and hashes.
        ApplicationUser newUser = new ApplicationUser(username, passwordEncoder.encode(password), firstName, lastName, dateOfBirth, bio);
       //save new user to database.
        applicationUserRepository.save(newUser);
        //send them back home
        return new RedirectView("/");
    }

//    @PostMapping("/login")
//    public RedirectView login(){
//        return new RedirectView(("/");
//    }
    @GetMapping("/login")
    public String showLogInForm(){
        return "login";
    }

    @GetMapping("/users/{id}") //id is dynamic
    public String showUserDetails(@ PathVariable long id, Principal p, Model m){
        ApplicationUser theUser = applicationUserRepository.findById(id).get();  //create an instance of the application user and find and get id
        m.addAttribute("usernameWeAreVisiting", theUser.getUsername());  //passs that as the value of the username key.
        m.addAttribute("userIdWeAreVisiting", theUser.id);
        m.addAttribute("userWeAreVisiting", theUser);
        m.addAttribute("principalTheAndroid", p.getName()); //get name of who is logged in.
        return "userDetails";

    }

    @PostMapping("/followMe")
    public RedirectView follow(long toBeFollowedId, Principal p){
        ApplicationUser follower = applicationUserRepository.findByUsername(p.getName());
        ApplicationUser toBeFollowed = applicationUserRepository.findById(toBeFollowedId).get();
        toBeFollowed.usersFollowingMe.add(follower);
        applicationUserRepository.save(toBeFollowed);
        return new RedirectView("/users/" + toBeFollowedId);
    }
}
