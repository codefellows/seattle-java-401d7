package com.ncarignan.songr;

public class Emotion {
    public String emotionName;
    private int strength;
    String reason;

    public Emotion(String emotionName, int strength, String reason) {
        this.emotionName = emotionName;
        this.strength = strength;
        this.reason = reason;
    }

    public String toString(){
        return String.format("%s : is this many strong : %d because %s", this.emotionName, this.strength, this.reason);
    }

    public int getStrength(){
        return this.strength;
    }

}
