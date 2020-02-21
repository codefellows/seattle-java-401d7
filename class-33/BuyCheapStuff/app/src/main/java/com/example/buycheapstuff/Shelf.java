package com.example.buycheapstuff;

import java.util.ArrayList;

public class Shelf {
    String name;
    ArrayList<BuyableItem> buyableItems;

    public Shelf(String name, ArrayList<BuyableItem> buyableItems) {
        this.name = name;
        this.buyableItems = buyableItems;
    }
}
