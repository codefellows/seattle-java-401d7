package com.ncarignan.pumas.controllers;

import com.ncarignan.pumas.models.ApplicationUser;
import com.ncarignan.pumas.models.ApplicationUserRepository;
import com.ncarignan.pumas.models.Puma;
import com.ncarignan.pumas.models.PumaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import sun.security.krb5.internal.ccache.CredentialsCache;

@Controller
public class PumaController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PumaRepository pumaRepository;

    @PostMapping("/pumaDetails")
    public RedirectView makeAPuma(long id, String firstName, String lastName, String color, int age, boolean domestic){
        ApplicationUser adopter = applicationUserRepository.findById(id).get();
        // save a puma
        Puma adoptedPuma = new Puma(adopter, firstName, lastName, color, age, domestic);
        pumaRepository.save(adoptedPuma);
        return new RedirectView("/users/" + id );
    };
}
