package com.example.buycheapstuff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // gets called automatically when we open this activity -
    // since its in MainActivity it will be called when we first open the app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // do high level stuff like even listeners and handlers
        // loading state
        // fetching data from an api

        // R stands for resources
        // element to click on - need an id

//        Button addToCartButton = findViewById(R.id.button);
//        addToCartButton.setOnClickListener(() -> {
//
//            TextView item = findViewById(R.id.textView2);
//            item.setText("pens");
//
//        });

        // addEvent Listner
        // type of event
        // callback

        // click on the add to search button
        // show the thing below (visibility)
        // dynamic name of the content
        Button addToCartButton = findViewById(R.id.button);
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = findViewById(R.id.editText);
                String inputText = input.getText().toString();

                TextView item = MainActivity.this.findViewById(R.id.textView2);
                item.setText(inputText);

                ConstraintLayout results = MainActivity.this.findViewById(R.id.resultsContainer);
                results.setVisibility(View.VISIBLE);
            }
        });

        Button goToOtherPageButton = findViewById(R.id.button2);
        goToOtherPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go somewhere else
                // Share intent (SEND) is registered with many apps
                // I get the text of the title of the item
                // send it somewhere else

//                Intent shareToPhoneIntent = new Intent(Intent.ACTION_SEND);
//                TextView text2 = findViewById(R.id.textView2);
//                String text2text = text2.getText().toString();
//
//                // putting the text into the intent so it can be sent elsewhere
//                shareToPhoneIntent.putExtra(Intent.EXTRA_TEXT, text2text);
//                // I forgot this last time - remember this
//                shareToPhoneIntent.setType("text/plain");
//                // tell the app to share
//                MainActivity.this.startActivity(Intent.createChooser(shareToPhoneIntent, "where you wanna share this huh?"));


                // go to another page like clicking an a tag
                Intent goToCartIntent = new Intent(MainActivity.this, Cart2.class);
                // put extras here
                MainActivity.this.startActivity(goToCartIntent);
            }
        });




    }
}
