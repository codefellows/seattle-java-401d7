package com.ncarignan.pumas.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Puma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long Id;


    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name="contracts", // name of the table in sql
            joinColumns = { @JoinColumn(name="bounty_hunter")},
            inverseJoinColumns = {@JoinColumn(name="prey")}
    )
    public Set<Puma> pumasIAmHunting;

    public Set<Puma> getPumasIAmHunting(){
        return this.pumasIAmHunting;
    }

    public void takeOutAHit(Puma prey){
        this.pumasIAmHunting.add(prey);
    }

    @ManyToMany(mappedBy = "pumasIAmHunting")
    public Set<Puma> pumasThatAreHuntingMe;





    @ManyToOne
    ApplicationUser applicationUser;

    public int successfulHunts = 0;
    String firstName;
    String lastName;
    String color;
    int age;
    boolean domestic;

    public long getId() {
        return Id;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getColor() {
        return color;
    }

    public int getAge() {
        return age;
    }

    public boolean isDomestic() {
        return domestic;
    }

    public Puma(ApplicationUser applicationUser, String firstName, String lastName, String color, int age, boolean domestic) {
        this.applicationUser = applicationUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.color = color;
        this.age = age;
        this.domestic = domestic;
    }

    public Puma(){

    }


}
