package com.ncarignan.songr;

import javax.persistence.*;

@Entity
public class Expression {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    Emotion emotion;

    public String action;
    public int intensity;

    public Expression(){

    }

    public Expression(String action, int intensity){
        this.action = action;
        this.intensity = intensity;
    }

    public String toString(){
        return String.format("I feel %s this many : %d", action, intensity);
    }
}
