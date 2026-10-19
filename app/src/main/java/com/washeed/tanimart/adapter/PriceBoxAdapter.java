package com.washeed.tanimart.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.washeed.tanimart.R;
import com.washeed.tanimart.data.entities.Price;

import java.util.List;

public class PriceBoxAdapter extends RecyclerView.Adapter<PriceBoxAdapter.PriceBoxViewHolder> {
    private List<Price> priceList;
    private int selectedPosition = 0;  //base selected position
    public interface onItemClickListener{
        void onItemClick(Price price, int position);
    }
    onItemClickListener listener;
    public PriceBoxAdapter(onItemClickListener listener) {
        this.listener = listener;
    }
    public void setPriceList(List<Price> priceList) {
        this.priceList = priceList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PriceBoxAdapter.PriceBoxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.price_list_view,parent,false);
        return new PriceBoxViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PriceBoxAdapter.PriceBoxViewHolder holder, int position) {
        //Button changing position logic
        Price price = priceList.get(position);
        holder.priceButtonText.setText(price.getWeightKG() + " Kg");

        // Highlight selected button
        holder.priceButton.setSelected(selectedPosition == position);
        if (selectedPosition == position) {
            holder.priceButtonText.setTextColor(Color.WHITE);
        } else {
            holder.priceButtonText.setTextColor(Color.parseColor("#6B7280"));
        }
        // When Clicked
        holder.priceButton.setOnClickListener(v -> {
            int clickedPosition = holder.getBindingAdapterPosition();
            if (clickedPosition == RecyclerView.NO_POSITION) {
                return;
            }
            int previousPosition = selectedPosition;
            // Update selected position FIRST
            selectedPosition = clickedPosition;
            // Update previous and current button appearance
            if (previousPosition != RecyclerView.NO_POSITION) {
                notifyItemChanged(previousPosition);
            }
            notifyItemChanged(selectedPosition);
            // Send CURRENTLY clicked price to activity
            listener.onItemClick(priceList.get(clickedPosition), clickedPosition);
        });
    }
    @Override
    public int getItemCount() {
        return priceList!=null? priceList.size(): 0;
    }
    static class PriceBoxViewHolder extends RecyclerView.ViewHolder {
        LinearLayout priceButton;
        TextView priceButtonText;
        public PriceBoxViewHolder(@NonNull View itemView) {
            super(itemView);
            priceButton = itemView.findViewById(R.id.priceButton);
            priceButtonText = itemView.findViewById(R.id.priceButtonText);
        }
    }
}
