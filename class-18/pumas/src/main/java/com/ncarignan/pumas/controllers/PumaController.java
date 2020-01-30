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

import javax.persistence.EntityManager;

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

    @PostMapping("/puma/contract")
    public RedirectView generateContract(long id, long bountyHunterId, long preyId){
        Puma bountyHunter = pumaRepository.findById(bountyHunterId).get();
        Puma prey = pumaRepository.findById(preyId).get();
        bountyHunter.takeOutAHit(prey);
        pumaRepository.save(bountyHunter);

        return new RedirectView("/users/" + id);
    }

    @PostMapping("/puma/delete")
    public RedirectView deletePuma(long id, long pumaId){
        // delete a puma
        // before a puma is deleted - award points\
        Puma prey = pumaRepository.findById(pumaId).get();
        for(Puma hunter : prey.pumasThatAreHuntingMe){
            hunter.successfulHunts++;
            hunter.pumasIAmHunting.remove(prey);
            pumaRepository.save(hunter);
            // go to each hunter and clear yourself from the set
            // resave the hunter
        }
        prey.pumasThatAreHuntingMe.clear();
        prey.pumasIAmHunting.clear();
        pumaRepository.save(prey);

        pumaRepository.deleteById(pumaId);

        return new RedirectView("/users/" + id);
    }
}
