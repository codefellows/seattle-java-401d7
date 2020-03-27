package com.soyewale.codefellowship.controllers;

import com.soyewale.codefellowship.models.ApplicationUser;
import com.soyewale.codefellowship.models.ApplicationUserRepository;
import com.soyewale.codefellowship.models.Post;
import com.soyewale.codefellowship.models.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PostController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PostRepository postRepository;
    @PostMapping("/postDetails")
    public RedirectView makeAPost(long id, String body, String createdAt, boolean kidsAppropriate){
        ApplicationUser postInitiator = applicationUserRepository.findById(id).get();
        //save post
        Post post = new Post(postInitiator, body, createdAt, kidsAppropriate);
        postRepository.save(post);
        return new RedirectView("/users/" + id);
    }
@PostMapping("/post/mingling")
    public RedirectView generateMingling(long id, long iDeyFollowThemId, long deyTheyFollowThemId ){
        Post bh = postRepository.findById(iDeyFollowThemId).get();
        Post dis = postRepository.findById(deyTheyFollowThemId).get();
        bh.takeOutIAmFollowing(dis);
        postRepository.save(bh);

        return new RedirectView("/users/" + id);
}
}
