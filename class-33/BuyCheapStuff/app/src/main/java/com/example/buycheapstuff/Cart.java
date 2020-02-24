package com.example.buycheapstuff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity implements BuyableItemAdapter.OnBuyableItemInteractionListener {

    MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart2);

        db = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "buy_cheap_stuff").allowMainThreadQueries().build();

//        List<BuyableItem> myItems = db.buyableItemDao().getAll();
        List<BuyableItem> items = new ArrayList<>();
        items.add(new BuyableItem("banana", 3.5f));
        items.add(new BuyableItem("apple", 1.5f));
        items.add(new BuyableItem("transformer", 2.7f));

        RecyclerView rv = findViewById(R.id.cart);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new BuyableItemAdapter(items, this));

    }

    @Override
    public void onClickOnItemCallbackPotato(BuyableItem item) {
        CharSequence chars = "clicked from the cart";
        Toast.makeText(getApplicationContext(), chars, Toast.LENGTH_SHORT).show();
    }
}
