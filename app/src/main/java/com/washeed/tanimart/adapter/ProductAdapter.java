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
import com.google.android.material.card.MaterialCardView;
import com.washeed.tanimart.R;
import com.washeed.tanimart.data.ProductCard;

import java.util.Collections;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<ProductCard> productList;
    private int displayLimit = Integer.MAX_VALUE;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(ProductCard productCard, int position);
    }

    public ProductAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setProduct(List<ProductCard> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }
    public void setDisplayLimit(int displayLimit) {
        this.displayLimit = displayLimit;
        notifyDataSetChanged();
    }
    public void reverseProducts() {
        Collections.reverse(productList);
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

        //Product Set
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

        //Price Discount Pill Show/Invisible and StrikeThrough
        double basePrice = product.getTotalPrice();
        int percentage = product.getDiscountPercentage();
        if (percentage > 0 && percentage <= 100) {
            double discountPrice = basePrice - (basePrice * percentage / 100.0);
            holder.discountBasePriceCard.setText(String.format("RP %.0f", discountPrice));
            holder.cutPriceCard.setText(String.format("RP %.0f", basePrice));
            holder.cutPriceCard.setVisibility(View.VISIBLE);
            holder.cutPriceCard.setPaintFlags(holder.cutPriceCard.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.discountPill.setText("-" + percentage + "%");
            holder.discountPill.setVisibility(View.VISIBLE);

        } else {
            holder.discountBasePriceCard.setText(String.format("RP %.0f", basePrice));
            holder.cutPriceCard.setVisibility(View.GONE);
            holder.discountPill.setVisibility(View.GONE);
        }

        //onItemClick View Products
        holder.showProductDetail.setOnClickListener(v -> {
            int pos = holder.getBindingAdapterPosition();
            if (pos!=RecyclerView.NO_POSITION){
                listener.onItemClick(productList.get(pos),pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList != null ? Math.min(productList.size(), displayLimit) : 0;
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
        MaterialCardView showProductDetail;

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
            showProductDetail = itemView.findViewById(R.id.showProductDetail);
        }
    }
}
