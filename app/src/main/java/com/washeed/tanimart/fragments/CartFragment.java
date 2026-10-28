package com.washeed.tanimart.fragments;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.washeed.tanimart.R;
import com.washeed.tanimart.activities.CheckoutActivity;
import com.washeed.tanimart.adapter.CartAdapter;
import com.washeed.tanimart.adapter.PurchaseSummaryAdapter;
import com.washeed.tanimart.data.AppDatabase;
import com.washeed.tanimart.data.entities.Cart;
import com.washeed.tanimart.data.entities.Price;
import com.washeed.tanimart.data.entities.Product;
import com.washeed.tanimart.data.models.CartItem;
import com.washeed.tanimart.data.repository.CartRepository;
import com.washeed.tanimart.data.repository.ProductRepository;
import com.washeed.tanimart.data.viewModel.CartSelectionViewModel;
import com.washeed.tanimart.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CartFragment extends Fragment {
    Executor executor= Executors.newSingleThreadExecutor();
    TextView cartFooterPrice, cartFooterItem, selectedNumber;
    MaterialButton cartFooterToCheckout;
    SessionManager userSession;
    List<CartItem> cartItems, selectedCartItems;
    CheckBox cartSelectAll;
    CartAdapter cartAdapter;
    PurchaseSummaryAdapter purchaseSummaryAdapter;
    boolean updatingSelectAll = false;
    CartSelectionViewModel cartSelectionViewModel;
    MaterialCardView purchaseSummaryView;

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_fragment, container, false);
        cartFooterPrice = view.findViewById(R.id.cartFooterPrice);
        cartFooterItem = view.findViewById(R.id.cartFooterItem);     //Total(0 Item)
        selectedNumber = view.findViewById(R.id.selectedNumber);     //0 Selected
        cartFooterToCheckout = view.findViewById(R.id.cartFooterToCheckout); //Checkout(0)
        cartSelectAll = view.findViewById(R.id.cartSelectAll);
        purchaseSummaryView = view.findViewById(R.id.purchaseSummaryView);
        userSession = new SessionManager(requireContext());
        purchaseSummaryView.setVisibility(GONE);
        int userID=userSession.getUserID();
        cartItems = new ArrayList<>();
        selectedCartItems = new ArrayList<>();
        cartSelectionViewModel = new ViewModelProvider(requireActivity()).get(CartSelectionViewModel.class);
        //Repository Initialize
        CartRepository cartRepository = new CartRepository(AppDatabase.getDatabase(requireContext()).cartDao());
        ProductRepository productRepository =  new ProductRepository(AppDatabase.getDatabase(requireContext()).productDao(),AppDatabase.getDatabase(requireContext()).categoryDao(),AppDatabase.getDatabase(requireContext()).priceDao());

        //------------------------------------------------------------------
        //---------------   Set Up Purchase Summary item    ----------------
        //------------------------------------------------------------------
        RecyclerView purchaseSummaryView = view.findViewById(R.id.spendingSummaryCartList);
        purchaseSummaryView.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false));
        purchaseSummaryAdapter = new PurchaseSummaryAdapter();
        purchaseSummaryView.setAdapter(purchaseSummaryAdapter);
        purchaseSummaryAdapter.setCartItems(selectedCartItems);

        //------------------------------------------------------------------
        //----------------------   Set Up Cart Item    ---------------------
        //------------------------------------------------------------------
        RecyclerView cartListView = view.findViewById(R.id.cartListView);
        cartListView.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false));
        executor.execute(()->{
            List<Cart> cartList= cartRepository.getCartsByUser(userID);
            cartItems.clear();
            for (Cart cart : cartList) {
                Product cartProduct = productRepository.getUserProductById(cart.getProductID());
                Price cartProductPrice = productRepository.getPriceById(cart.getPriceID());
                if (cartProduct != null && cartProductPrice != null) {
                    cartItems.add(new CartItem(cart, cartProduct, cartProductPrice));
                }
            }    // (CartItem Populator) here the cart with their product and price is being added to an object that is sent to the list view
            requireActivity().runOnUiThread(() -> {
                selectedCartItems.clear();
                //------ View Modle---------------------
                for (CartItem item : cartItems) {
                    int cartID = item.getCart().getCartID();
                    if (cartSelectionViewModel.getSelectedCartIDs().contains(cartID)) {
                        selectedCartItems.add(item);
                        refreshSelectionUI();
                    }
                } // Adds the saved state from the view model to the selectedcartitems
                //-----------------------------------------------
                cartAdapter = new CartAdapter(cartItems,
                        selectedCartItems,
                        (updateCart) -> {
                            executor.execute(() -> cartRepository.cartUpdate(updateCart));
                            purchaseSummaryAdapter.notifyDataSetChanged();   //notifies the below adapter
                            setSelectedItems();  // updates all the texts
                        },   // update a cart
                        (deleteCart)->{
                            cartItems.removeIf(item -> item.getCart().getCartID() == deleteCart.getCartID());  //removes from cartItem object if exists
                            selectedCartItems.removeIf(item -> item.getCart().getCartID() == deleteCart.getCartID()); // removes from selected items object if exist
                            executor.execute(() -> cartRepository.cartDelete(deleteCart));  // deletes from the database
                            refreshSelectionUI();
                        },   // delte a cart
                        (cartItem, checked) -> {
                            int cartID = cartItem.getCart().getCartID();
                            if (checked) {
                                if (!selectedCartItems.contains(cartItem)) {
                                    selectedCartItems.add(cartItem);
                                }
                                cartSelectionViewModel.getSelectedCartIDs().add(cartID);
                            } else {
                                selectedCartItems.remove(cartItem);
                                cartSelectionViewModel.getSelectedCartIDs().remove(cartID);
                            }
                            refreshSelectionUI();
                        }  //View model fix
                );
                cartListView.setAdapter(cartAdapter);
                refreshSelectionUI();
            });
        });
        //----------------------   Set Up Select All    ---------------------
        cartSelectAll.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedCartItems.clear();
                selectedCartItems.addAll(cartItems);
                cartSelectionViewModel.getSelectedCartIDs().clear();
                for (CartItem item : cartItems) {
                    cartSelectionViewModel.getSelectedCartIDs().add(item.getCart().getCartID());
                }
            } else {
                selectedCartItems.clear();
                cartSelectionViewModel.getSelectedCartIDs().clear();
                purchaseSummaryView.setVisibility(GONE);
            }
            refreshSelectionUI();
        });

        //------------------------------------------------------------------
        //----------------------   Set Up Checkout  ---------------------
        //------------------------------------------------------------------
        cartFooterToCheckout.setOnClickListener(v -> {
            //send cart items here
            if (selectedCartItems.isEmpty()) {
                Toast.makeText(requireContext(), "Please select at least one product", Toast.LENGTH_SHORT).show();
                return;
            }
            ArrayList<Integer> selectedCartItemIDs = new ArrayList<>();
            for (CartItem cart: selectedCartItems){
                selectedCartItemIDs.add(cart.getCart().getCartID());
            } // gets all the cart ids
            Intent sendCartItems = new Intent(requireContext(), CheckoutActivity.class);
            sendCartItems.putExtra("PRODUCT ITEMS", selectedCartItemIDs);
            startActivity(sendCartItems);
        });
        return view;
    }
    @SuppressLint("SetTextI18n")
    void setSelectedItems(){
        int selectedCount = selectedCartItems.size();
        int grandTotal = 0;
        for (CartItem item : selectedCartItems) {
            grandTotal += item.getCart().getTotalPrice() * item.getCart().getQuantity();
        }
        cartFooterItem.setText("Total(" + selectedCount + " Item)");  //Total(0 Item)
        selectedNumber.setText(selectedCount + " Selected"); //0 Selected
        cartFooterPrice.setText("Rp " + grandTotal);
        cartFooterToCheckout.setText("Checkout(" + selectedCount + ")"); //Checkout(0)
    }
    @SuppressLint("SetTextI18n")
    private void refreshSelectionUI() {
        if (cartAdapter != null) {
            cartAdapter.notifyDataSetChanged();   //if cartAdapter has items then notify any changes
        }
        if (purchaseSummaryAdapter != null) {
            purchaseSummaryAdapter.notifyDataSetChanged();  //if purchaseSummaryAdapter has items then notify any changes
        }
        setSelectedItems(); //sets all four parameters
        boolean hasSelection = !selectedCartItems.isEmpty();
        purchaseSummaryView.setVisibility(hasSelection ? VISIBLE : GONE);
        cartFooterToCheckout.setBackgroundColor(Color.parseColor(hasSelection ? "#2E7D32" : "#CDD1D7"));
        updatingSelectAll = true;
        cartSelectAll.setChecked(!cartItems.isEmpty() && selectedCartItems.size() == cartItems.size());
        updatingSelectAll = false;
    }


}
