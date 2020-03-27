package com.soyewale.codefellowship.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
    //principal means logged in user
    @GetMapping("/")
    public String getHome(Principal p, Model m ){
        if(p != null){
            m.addAttribute("username", p.getName());

        }
        return "home";
    }
}
