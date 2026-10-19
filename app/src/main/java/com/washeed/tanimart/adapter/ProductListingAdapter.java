package com.washeed.tanimart.adapter;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.washeed.tanimart.R;
import com.washeed.tanimart.data.ProductCard;

import java.util.List;

public class ProductListingAdapter extends RecyclerView.Adapter<ProductListingAdapter.ProductViewHolder> {

    private List<ProductCard> productList;

    public void setProduct(List<ProductCard> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_view, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        ProductCard product = productList.get(position);
        holder.productTitleCard.setText(product.getProductName());
        holder.productWeightCard.setText(product.getWeightKG() + " kg");
        holder.productRatingCard.setText(String.valueOf(product.getRating()));
        holder.productReviewsCard.setText("(" + product.getTotalReviews() + ")");

        // Load product image
        Glide.with(holder.itemView.getContext())
                .load(product.getProductImageID())
                .centerCrop()
                .error(R.drawable.article1)
                .placeholder(R.drawable.article1)
                .into(holder.productImageCard);
        double basePrice = product.getTotalPrice();
        int percentage = product.getDiscountPercentage();

        if (percentage > 0 && percentage <= 100) {
            double discountPrice = basePrice - (basePrice * percentage / 100.0);
            holder.discountBasePriceCard.setText(String.format("৳ %.0f", discountPrice));
            holder.cutPriceCard.setText(String.format("৳ %.0f", basePrice));
            holder.cutPriceCard.setVisibility(View.VISIBLE);
            holder.cutPriceCard.setPaintFlags(holder.cutPriceCard.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.discountPill.setText("-" + percentage + "%");
            holder.discountPill.setVisibility(View.VISIBLE);

        } else {
            holder.discountBasePriceCard.setText(String.format("৳ %.0f", basePrice));
            holder.cutPriceCard.setVisibility(View.GONE);
            holder.discountPill.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return productList != null ? productList.size() : 0;
    }
    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImageCard;
        TextView productTitleCard;
        TextView productWeightCard;
        TextView productRatingCard;
        TextView productReviewsCard;
        TextView discountBasePriceCard;
        TextView cutPriceCard;
        TextView discountPill;
        MaterialButton addProductToCart;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productImageCard = itemView.findViewById(R.id.productImageCard);
            productTitleCard = itemView.findViewById(R.id.productTitleCard);
            productWeightCard = itemView.findViewById(R.id.productWeightCard);
            productRatingCard = itemView.findViewById(R.id.productRatingCard);
            productReviewsCard = itemView.findViewById(R.id.productReviewscard);

            discountBasePriceCard = itemView.findViewById(R.id.dicountBasePriceCard);
            cutPriceCard = itemView.findViewById(R.id.cutPriceCard);
            discountPill = itemView.findViewById(R.id.discountPill);

            addProductToCart = itemView.findViewById(R.id.addProductToCart);
        }
    }
}
