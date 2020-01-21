package com.ncarignan.songr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class HomeController {
    // our controller should have routes
    // we want something like app.get("/", (req, res) => {})

    // must be a method that returns a string
    @GetMapping("/")
    // The syntax for getting getting a query param is just to add it as a function parameter
    public String getHome(@RequestParam(defaultValue = "rufus", required=false) String potato, Model m){
        // Model is a kinda poor name for this, it shapes data for the view
        // If I return the string home, it is expecting a file called home.html in resources/templates
        // m is a hashmap of key value pairs where the key is the varname for the view
        System.out.println(potato);
        m.addAttribute("username", potato);
        return "home";
    }

    // TODO: other params

    @GetMapping("/emotions")
    public String getEmotions(Model m){
        Emotion[] emotions = new Emotion[]{new Emotion("accomplished", 10, "got all labs done and everything"), new Emotion("stressed", 12, "havent got all labs done yet and everything"), new Emotion("confused", 7, "nodes")};
        m.addAttribute("emotionLis", emotions);
        return "emotions";


    }

//    app.get("/monkey/:id", (req, res)=>
    @GetMapping("/sayHello/{personName}")
    public String getHello(@PathVariable String personName, Model m){
        System.out.println((personName));
        m.addAttribute("tomato", personName);
        return "hello";
    }
}
