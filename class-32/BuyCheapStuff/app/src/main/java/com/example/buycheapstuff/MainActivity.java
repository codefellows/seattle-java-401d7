package com.example.buycheapstuff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BuyableItemAdapter.OnBuyableItemInteractionListener {

    private String TAG = "MAINACTIVITY.BUYABLE";
    List<BuyableItem> buyableItems;
    MyDatabase myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "buy_cheap_stuff").allowMainThreadQueries().build();

        this.buyableItems = myDb.buyableItemDao().getAll();
        for(BuyableItem item : buyableItems){
            Log.i(TAG, item.name + item.price);
        }

        RecyclerView recyclerView = findViewById(R.id.cheapStuff);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BuyableItemAdapter(this.buyableItems, this));





        Button addToCartButton = findViewById(R.id.button);
        addToCartButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = findViewById(R.id.editText);
                // the word  "kleenex"
                String inputText = input.getText().toString();

                BuyableItem newItem = new BuyableItem(inputText, (float)Math.random() * 5);
                MainActivity.this.buyableItems.add(0, newItem);
                myDb.buyableItemDao().save(newItem);


                Log.i(TAG, "" + MainActivity.this.buyableItems.size());
                RecyclerView recyclerView = findViewById(R.id.cheapStuff);
                recyclerView.getAdapter().notifyItemInserted(0);
                recyclerView.getLayoutManager().scrollToPosition(0);


            }
        });

    }

    // Where we actually write click work
    @Override
    public void onClickOnItemCallbackPotato(BuyableItem item) {
        Log.i(TAG, item.name + "was clicked on");
        Intent goToCartIntent = new Intent(this, Cart.class);
        goToCartIntent.putExtra("itemName", item.name);
        goToCartIntent.putExtra("itemPrice", item.price);
        MainActivity.this.startActivity(goToCartIntent);

    }
}
