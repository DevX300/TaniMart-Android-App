package com.washeed.tanimart.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.washeed.tanimart.R;
import com.washeed.tanimart.data.models.CartItem;

import java.util.List;

public class PurchaseSummaryAdapter extends RecyclerView.Adapter<PurchaseSummaryAdapter.PurchaseSummaryViewHolder> {
    private List<CartItem> cartItems;
    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PurchaseSummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spending_summary_cart_item, parent, false);
        return new PurchaseSummaryViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PurchaseSummaryViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        String name = item.getProduct().getProductName() + " x" + item.getCart().getQuantity();
        int subtotal = item.getCart().getTotalPrice() * item.getCart().getQuantity();
        holder.specificationName.setText(name);
        holder.specificationValue.setText("Rp " + subtotal);
    }

    @Override
    public int getItemCount() {
        return cartItems == null ? 0 : cartItems.size();
    }
    static class PurchaseSummaryViewHolder extends RecyclerView.ViewHolder {
        TextView specificationName;
        TextView specificationValue;
        public PurchaseSummaryViewHolder(@NonNull View itemView) {
            super(itemView);
            specificationName = itemView.findViewById(R.id.specificationName);
            specificationValue = itemView.findViewById(R.id.specificationValue);
        }
    }
}