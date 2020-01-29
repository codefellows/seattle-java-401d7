package com.ncarignan.pumas.models;

import javax.persistence.*;

@Entity
public class Puma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long Id;

    @ManyToOne
    ApplicationUser applicationUser;

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
