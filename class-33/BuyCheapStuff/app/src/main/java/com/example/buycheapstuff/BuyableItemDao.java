package com.example.buycheapstuff;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BuyableItemDao {
    @Query("SELECT * FROM buyableitem ORDER BY id DESC")
    List<BuyableItem> getAll();

    @Query("SELECT * FROM buyableitem WHERE id= :id")
    BuyableItem getOne(long id);

    @Insert
    void save(BuyableItem item);
}
