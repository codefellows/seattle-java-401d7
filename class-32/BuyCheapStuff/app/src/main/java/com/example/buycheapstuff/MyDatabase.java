package com.example.buycheapstuff;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {BuyableItem.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract BuyableItemDao buyableItemDao();
}
