package com.washeed.tanimart.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.washeed.tanimart.R;
import com.washeed.tanimart.data.entities.Cart;
import com.washeed.tanimart.data.entities.Price;
import com.washeed.tanimart.data.entities.Product;
import com.washeed.tanimart.data.models.CartItem;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<CartItem> cartList;
    private final List<CartItem> selectedCartItems;
    public CartAdapter(List<CartItem> cartList, List<CartItem> selectedCartItems, OnQuantityChangedListener listener, OnItemDeleteListener deleteListener, OnCartSelectionChanged checkBoxListener) {
        this.cartList = cartList;
        this.selectedCartItems = selectedCartItems;
        this.listener = listener;
        this.deleteListener = deleteListener;
        this.checkBoxListener = checkBoxListener;
    }
    public interface OnQuantityChangedListener{
        void onQuantityChanged(Cart cart);
    }
    public interface OnItemDeleteListener{
        void onItemDelete(Cart cart);
    }
    public interface OnCartSelectionChanged {
        void onCartSelectionChanged(CartItem cartItem, boolean selected);
    }
    private final OnCartSelectionChanged checkBoxListener;
    private final OnQuantityChangedListener listener;
    private final OnItemDeleteListener deleteListener;
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartList.get(position);
        Cart cart = item.getCart();
        Product product = item.getProduct();
        Price price = item.getPrice();
        // Product Details
        holder.cartItemName.setText(product.getProductName());
        holder.cartItemVariant.setText(price.getWeightKG()+" Kg");
        // Discounted Unit Price
        holder.cartItemPrice.setText("Rp " + cart.getTotalPrice());
        // Quantity
        holder.cartQuantity.setText(String.valueOf(cart.getQuantity()));
        // Subtotal = discounted price × quantity
        holder.cartItemSubtotal.setText("Rp " + (cart.getTotalPrice() * cart.getQuantity()));
        // Product Image
        Glide.with(holder.itemView.getContext())
                .load(product.getProductImageID())
                .placeholder(R.drawable.article1)
                .into(holder.cartItemImage);
        // Plus
        holder.plusButton.setOnClickListener(v -> {
            int quantity = cart.getQuantity() + 1;
            cart.setQuantity(quantity);
            holder.cartQuantity.setText(String.valueOf(quantity));
            holder.cartItemSubtotal.setText("Rp " + (cart.getTotalPrice() * quantity));
            //callback to update cart
            listener.onQuantityChanged(cart);
        });
        // Minus
        holder.minusButton.setOnClickListener(v -> {
            if (cart.getQuantity() > 1) {
                int quantity = cart.getQuantity() - 1;
                cart.setQuantity(quantity);
                holder.cartQuantity.setText(String.valueOf(quantity));
                holder.cartItemSubtotal.setText("Rp " + (cart.getTotalPrice() * quantity));
                //callback to update cart
                listener.onQuantityChanged(cart);
            }
        });
        // Cart Delete
        holder.cartItemDelete.setOnClickListener(v -> {
            deleteListener.onItemDelete(cart);
        });
        // Cart Select
//        holder.cartItemCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            checkBoxListener.onCartSelectionChanged(item, isChecked);
//        });
        // -------------------------------------Cart Select----------------------------------------
        holder.cartItemCheckbox.setOnCheckedChangeListener(null);   // at first sends null
        holder.cartItemCheckbox.setChecked(selectedCartItems.contains(item));  //sends true if there are items
        holder.cartItemCheckbox.setOnCheckedChangeListener((buttonView, checked) -> checkBoxListener.onCartSelectionChanged(item, checked));
        //-----------------------------------------------------------------------------------------
    }
    @Override
    public int getItemCount() {
        return cartList == null ? 0 : cartList.size();
    }
    static class CartViewHolder extends RecyclerView.ViewHolder {

        TextView cartItemPrice;
        TextView cartItemSubtotal;
        TextView cartQuantity;

        TextView plusButton;
        TextView minusButton;

        CheckBox cartItemCheckbox;

        ImageView cartItemDelete;
        ImageView cartItemImage;

        TextView cartItemName;
        TextView cartItemVariant;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            cartItemPrice = itemView.findViewById(R.id.cartItemPrice);
            cartItemSubtotal = itemView.findViewById(R.id.cartItemSubtotal);
            cartQuantity = itemView.findViewById(R.id.cartQuantity);

            plusButton = itemView.findViewById(R.id.plusButton);
            minusButton = itemView.findViewById(R.id.minusButton);

            cartItemCheckbox = itemView.findViewById(R.id.cartItemCheckbox);

            cartItemDelete = itemView.findViewById(R.id.cartItemDelete);
            cartItemImage = itemView.findViewById(R.id.cartItemImage);

            cartItemName = itemView.findViewById(R.id.cartItemName);
            cartItemVariant = itemView.findViewById(R.id.cartItemVariant);
        }
    }
}