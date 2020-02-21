package com.example.buycheapstuff;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BuyableItem {

    @PrimaryKey(autoGenerate = true)
    long id;

    public String name;
    public float price;

    public BuyableItem(String name, float price) {
        this.name = name;
        this.price = price;
    }



}
