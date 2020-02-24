package com.example.buycheapstuff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amazonaws.amplify.generated.graphql.CreateBuyableItemMutation;
import com.amazonaws.amplify.generated.graphql.CreateShelfMutation;
import com.amazonaws.amplify.generated.graphql.ListBuyableItemsQuery;
import com.amazonaws.amplify.generated.graphql.ListShelfsQuery;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import type.CreateBuyableItemInput;
import type.CreateShelfInput;

public class MainActivity extends AppCompatActivity implements BuyableItemAdapter.OnBuyableItemInteractionListener {

    private String TAG = "MAINACTIVITY.BUYABLE";
    List<BuyableItem> buyableItems;
    MyDatabase myDb;

    private AWSAppSyncClient awsAppSyncClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        awsAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();



        myDb = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "buy_cheap_stuff").allowMainThreadQueries().build();

        this.buyableItems = new ArrayList<BuyableItem>();
//        this.buyableItems = myDb.buyableItemDao().getAll();
//        for(BuyableItem item : buyableItems){
//            Log.i(TAG, item.name + item.price);
//        }

        RecyclerView recyclerView = findViewById(R.id.cheapStuff);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BuyableItemAdapter(this.buyableItems, this));

        getBuyableItems();
//        runShelfCreateMutation("Tortoise");
//        runShelfCreateMutation("Books");





        Button addToCartButton = findViewById(R.id.button);
        addToCartButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = findViewById(R.id.editText);
                // the word  "kleenex"
                String inputText = input.getText().toString();

//                BuyableItem newItem = new BuyableItem(inputText, (float)Math.random() * 5);
//                MainActivity.this.buyableItems.add(0, newItem);
//                myDb.buyableItemDao().save(newItem);
//
//
//                Log.i(TAG, "" + MainActivity.this.buyableItems.size());
//                RecyclerView recyclerView = findViewById(R.id.cheapStuff);
//                recyclerView.getAdapter().notifyItemInserted(0);
//                recyclerView.getLayoutManager().scrollToPosition(0);

                runBuyableItemCreateMutation(inputText, (float) Math.random() * 5);


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

    public void runBuyableItemCreateMutation(String name, float price){
        // I need to know shelf ids.
        awsAppSyncClient.query(ListShelfsQuery.builder().build())
                .enqueue(new GraphQLCall.Callback<ListShelfsQuery.Data>() {
                    @Override
                    public void onResponse(@Nonnull Response<ListShelfsQuery.Data> response) {
                        ListShelfsQuery.Item item = response.data().listShelfs().items().get(1);

                        // old code goes here
                        CreateBuyableItemInput createBuyableItemInput = CreateBuyableItemInput.builder()
                                .title(name)
                                .buyableItemShelfId(item.id())
                                .price((double) price)
                                .build();
                        awsAppSyncClient.mutate(CreateBuyableItemMutation.builder().input(createBuyableItemInput).build())
                                .enqueue(addMutationCallback);

                    }

                    @Override
                    public void onFailure(@Nonnull ApolloException e) {

                    }
                });




    }

    private GraphQLCall.Callback<CreateBuyableItemMutation.Data> addMutationCallback = new GraphQLCall.Callback<CreateBuyableItemMutation.Data>() {
        @Override
        public void onResponse(@Nonnull Response<CreateBuyableItemMutation.Data> response) {
            Log.i(TAG, "Added Buyable Item");
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e(TAG, e.toString());
        }
    };

    public void getBuyableItems(){
        awsAppSyncClient.query(ListBuyableItemsQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(buyableItemsCallback);
    }

    private GraphQLCall.Callback<ListBuyableItemsQuery.Data> buyableItemsCallback = new GraphQLCall.Callback<ListBuyableItemsQuery.Data>() {
        @Override
        public void onResponse(@Nonnull Response<ListBuyableItemsQuery.Data> response) {
            Log.i(TAG, response.data().listBuyableItems().items().toString());
            if(buyableItems.size() == 0 || response.data().listBuyableItems().items().size() != buyableItems.size()){

                buyableItems.clear();

                for(ListBuyableItemsQuery.Item item : response.data().listBuyableItems().items()){
                BuyableItem a = new BuyableItem(item.title(), item.price().floatValue());
                buyableItems.add(a);
            };


                // this is necessary any time you modify content in the view
//            looper lets us send an action to the main ui thread (getMainLooper)
            Handler handlerForMainThread = new Handler(Looper.getMainLooper()){
                @Override
                public void handleMessage(Message inputMessage){
                    RecyclerView recyclerView = findViewById(R.id.cheapStuff);
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
            };

            handlerForMainThread.obtainMessage().sendToTarget();
            }

        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e(TAG, e.toString());
        }
    };

    public void runShelfCreateMutation(String name){
        CreateShelfInput createShelfInput = CreateShelfInput.builder()
                .name(name)
                .build();
        awsAppSyncClient.mutate(CreateShelfMutation.builder().input(createShelfInput).build())
                .enqueue(addShelfCallback);

    }

    private GraphQLCall.Callback<CreateShelfMutation.Data> addShelfCallback = new GraphQLCall.Callback<CreateShelfMutation.Data>() {
        @Override
        public void onResponse(@Nonnull Response<CreateShelfMutation.Data> response) {
            Log.i(TAG, "Added Shelf");
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e(TAG, e.toString());
        }
    };


}
