package com.ncarignan.songr;

import javax.persistence.*;
import java.util.List;

// make a table for this, treat it as if it belongs in a db
@Entity
public class Emotion {


    // id makes it the primary key of the table
    // generated value makes it start at one and increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToMany(mappedBy = "emotion")
    public List<Expression> expressions;

    public String emotionName;
    private int strength;
    String reason;

    public Emotion(String emotionName, int strength, String reason) {
        this.emotionName = emotionName;
        this.strength = strength;
        this.reason = reason;
    }

    // Add a default constructor
    public Emotion(){

    }

    public String toString(){
        return String.format("%s : is this many strong : %d because %s", this.emotionName, this.strength, this.reason);
    }

    public int getStrength(){
        return this.strength;
    }

    public long getId() {
        return id;
    }

}
