package com.example.buycheapstuff;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BuyableItemAdapter extends RecyclerView.Adapter<BuyableItemAdapter.BuyableItemViewHolder> {

    private final List<BuyableItem> buyableItems;
    private final OnBuyableItemInteractionListener listener;

    // when I make an adapter, I pass it the activity that I want to listen
    public BuyableItemAdapter(List<BuyableItem> buyableItems, OnBuyableItemInteractionListener potatoListener){
        this.listener = potatoListener;
        this.buyableItems = buyableItems;
    }

    public static class BuyableItemViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public final View view;
        public BuyableItem myItem;
        public final TextView itemNameView;
        public final TextView itemPriceView;
        public BuyableItemViewHolder(View v) {
            super(v);
            view = v;
            itemNameView = (TextView) view.findViewById(R.id.itemName);
            itemPriceView = (TextView) view.findViewById(R.id.itemValue);

        }
    }

    @NonNull
    @Override
    public BuyableItemAdapter.BuyableItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_buyable_item, parent, false);

        // right now is when I have access to the view
        BuyableItemViewHolder viewHolder = new BuyableItemViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                System.out.println();

                // stands for mainactivity for us
                listener.onClickOnItemCallbackPotato(viewHolder.myItem);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BuyableItemAdapter.BuyableItemViewHolder holder, int position) {
        holder.itemNameView.setText(buyableItems.get(position).name);
        holder.itemPriceView.setText("$" + buyableItems.get(position).price);
        holder.myItem = buyableItems.get(position);

    }

    @Override
    public int getItemCount() {
        return buyableItems.size();
    }

    public static interface OnBuyableItemInteractionListener {
        public void onClickOnItemCallbackPotato(BuyableItem item);
    }
}
