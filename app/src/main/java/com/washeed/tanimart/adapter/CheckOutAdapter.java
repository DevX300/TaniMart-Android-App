
package com.washeed.tanimart.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.washeed.tanimart.R;
import com.washeed.tanimart.logicLayer.Checkout;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CheckOutAdapter extends RecyclerView.Adapter<CheckOutAdapter.CheckOutViewHolder> {
    private List<Checkout> checkoutItems = new ArrayList<>();
    public CheckOutAdapter(List<Checkout> checkoutItems) {
        this.checkoutItems=checkoutItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CheckOutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkout_product, parent, false);
        return new CheckOutViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CheckOutViewHolder holder, int position) {
        Checkout item = checkoutItems.get(position);
        // Product information
        holder.productName.setText(item.getProductName());
        // Cart quantity
        holder.productQuantity.setText("x" + item.getProductQuantity());
        // Total price for this cart item
        holder.productPrice.setText("Rp " + item.getProductBasePrice()*item.getProductQuantity());
        // Product image loading will be implemented later.
        Glide.with(holder.itemView.getContext())
                .load(item.getProductImageID())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.productImage);

    }
    @Override
    public int getItemCount() {
        return checkoutItems != null ? checkoutItems.size() : 0;
    }
    static class CheckOutViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView productImage;
        TextView productName;
        TextView productQuantity;
        TextView productPrice;
        public CheckOutViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.checkoutProductImage);
            productName = itemView.findViewById(R.id.checkoutProductName);
            productQuantity = itemView.findViewById(R.id.checkoutProductQuantity);
            productPrice = itemView.findViewById(R.id.checkoutProductPrice);
        }
    }
}
