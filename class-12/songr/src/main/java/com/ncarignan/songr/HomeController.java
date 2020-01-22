package com.ncarignan.songr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    // instance variable to hold our repository
    // connects to the database
    // have the methods to do CRUD on the db for just Emotions
    @Autowired
    EmotionRepository emotionRepository;


    // our controller should have routes
    // we want something like app.get("/", (req, res) => {})

    // must be a method that returns a string
    @GetMapping("/a/b/c/d/e/f")
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

    @GetMapping("/emotionsPotato")
    public String getEmotions(Model m){
//        Emotion[] emotions = new Emotion[]{new Emotion("accomplished", 10, "got all labs done and everything"), new Emotion("stressed", 12, "havent got all labs done yet and everything"), new Emotion("confused", 7, "nodes")};
        List<Emotion> emotions = emotionRepository.findAll();
        m.addAttribute("emotionLis", emotions);
        return "emotions";
    }

    @PostMapping("/emotions")
    // whatever is in my form as an input can be a parameter here
    public RedirectView postEmotions(String emotionName, String reason, int strength){
        Emotion newEmotion = new Emotion(emotionName, strength, reason);
        emotionRepository.save(newEmotion);
//        List<Emotion> emotions = emotionRepository.findAll();
//        m.addAttribute("emotionLis", emotions);
//        // have I anywhere, added emotions to a list and given it to that page?
//        return "emotions";

//        instead of the commented out code, I want to send them to the emotions get route
        // update the method signature to return a redirect view
        // and return a redirect view with the correct path
        return new RedirectView("/emotionsPotato");

    }

//    app.get("/monkey/:id", (req, res)=>
    @GetMapping("/sayHello/{personName}")
    public String getHello(@PathVariable String personName, Model m){
        System.out.println((personName));
        m.addAttribute("tomato", personName);
        return "hello";
    }
}
