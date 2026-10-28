package com.washeed.tanimart.activities;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.washeed.tanimart.R;
import com.washeed.tanimart.adapter.CheckOutAdapter;
import com.washeed.tanimart.data.AppDatabase;
import com.washeed.tanimart.data.entities.Cart;
import com.washeed.tanimart.data.entities.Product;
import com.washeed.tanimart.data.entities.User;
import com.washeed.tanimart.data.repository.CartRepository;
import com.washeed.tanimart.data.repository.ProductRepository;
import com.washeed.tanimart.data.repository.UserRepository;
import com.washeed.tanimart.logicLayer.Checkout;
import com.washeed.tanimart.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CheckoutActivity extends AppCompatActivity {

    //---------------Shipping Method Radio Logic--------------------------------------------
    private final String[] shippingNames = {"JNE Regular", "JNE YES", "SiCepat Express", "GoSend Same Day"};
    private final String[] shippingEstimates = {"Estimated 3–5 days", "Estimated 1–2 days", "Estimated 2–3 days", "Estimated arrival today"};
    private final int[] shippingPricesText = {18, 32, 22, 45};
    private int selectedShippingMethodId = 1; // JNE Regular (ID 1 )selected by default
    private final List<MaterialCardView> shippingCards = new java.util.ArrayList<>();
    private final List<View> shippingIndicators = new java.util.ArrayList<>();
    //-----------------------------------------------------------
    //---------------Payment Method Radio Logic--------------------------------------------
    private final String[] paymentNames = {"JTransfer Bank BCA / BRI / BNI", "GoPay / OVO / DANA", "QRIS", "Cash On Delivery (COD)"};
    private final String[] paymentIcons = {"🏦", "💳", "⬛", "💵"};
    private int selectedPaymentMethodId = 1; // (ID 1 )selected by default
    private final List<MaterialCardView> paymentCards = new java.util.ArrayList<>();
    private final List<View> paymentIndicators = new java.util.ArrayList<>();
    //-----------------------------------------------------------

    Executor executor = Executors.newSingleThreadExecutor();

    //----------------------
    ArrayList<Checkout> checkoutItems = new ArrayList<>();
    CheckOutAdapter checkOutAdapter;
    MaterialButton checkOutPayButton;
    TextView addressReceiver, addressDetail, productSubtotalValue, shippingCostValue, voucherDiscountValue, totalPrice;
    int productSubTotal=0;
    int checkoutShippingFee=0;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_checkout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        CartRepository cartRepository = new CartRepository(AppDatabase.getDatabase(this).cartDao());
        ProductRepository productRepository = new ProductRepository(AppDatabase.getDatabase(this).productDao());
        UserRepository userRepository = new UserRepository(AppDatabase.getDatabase(this).userDao());

        addressReceiver= findViewById(R.id.addressReceiver);
        addressDetail= findViewById(R.id.addressDetail);
        productSubtotalValue= findViewById(R.id.productSubtotalValue);
        shippingCostValue= findViewById(R.id.shippingCostValue);
        voucherDiscountValue= findViewById(R.id.voucherDiscountValue);
        totalPrice=findViewById(R.id.totalPrice);
        checkOutPayButton = findViewById(R.id.checkOutPayButton);
        //--------------------for Shipping Method-----------------------------------
        setupShippingOptions();
        //---------------------------------------------------------------------------

        //--------------------for Payment Method-----------------------------------
        setupPaymentOptions();
        String paymentName =  paymentNames[selectedPaymentMethodId - 1];
        //---------------------------------------------------------------------------

        //---------------------Setup CheckOut Items--------------------------------
        checkoutItems.clear();
        RecyclerView checkoutRecyclerView = findViewById(R.id.checkOutProduct);
        checkoutRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ArrayList<Integer> selectedCartIDs = getIntent().getIntegerArrayListExtra("PRODUCT ITEMS");
        if (selectedCartIDs != null && !selectedCartIDs.isEmpty()) {
            // ----------------------------------------------Checkout from Cart--------------------------------------------------
            executor.execute(() -> {
                for (int cartID : selectedCartIDs) {
                    Cart cart = cartRepository.getCartById(cartID);
                    Product product = productRepository.getProductByID(cart.getProductID());
                    runOnUiThread(() -> {
                        Checkout checkout = new Checkout(product.getProductName(), product.getProductImageID(), cart.getQuantity(), cart.getTotalPrice());
                        checkoutItems.add(checkout);

                        productSubTotal+=cart.getQuantity()*cart.getTotalPrice();
                        productSubtotalValue.setText(String.valueOf(productSubTotal));
                    });
                }
                runOnUiThread(()->{
                    checkOutAdapter = new CheckOutAdapter(checkoutItems);
                    checkoutRecyclerView.setAdapter(checkOutAdapter);
                    updatePaymentDetails();
                });
            });
        }
        else {
            // ---------------------------Buy Now from Product Details--------------------------------
            int singleProductID = getIntent().getIntExtra("PRODUCT ID", -1);
            int singleProductQuantity = getIntent().getIntExtra("PRODUCT QUANTITY", -1);
            int singleProductBasePrice = getIntent().getIntExtra("PRODUCT PRICE", -1);
            if (singleProductID != -1) {
                executor.execute(() -> {
                    Product product = productRepository.getProductByID(singleProductID);
                    if (product != null) {
                        runOnUiThread(() -> {
                            Checkout checkout = new Checkout(product.getProductName(), product.getProductImageID(), singleProductQuantity, singleProductBasePrice);
                            checkoutItems.add(checkout);
                            checkOutAdapter = new CheckOutAdapter(checkoutItems);
                            checkoutRecyclerView.setAdapter(checkOutAdapter);

                            productSubTotal+=singleProductQuantity*singleProductBasePrice;
                            productSubtotalValue.setText("Rp "+productSubTotal);
                            updatePaymentDetails();
                        });
                    }
                });
            }
        }

        //-------------------Back Action----------------------------
        ImageView checkOutBack = findViewById(R.id.checkOutBack);
        checkOutBack.setOnClickListener(v -> {
            this.getOnBackPressedDispatcher().onBackPressed();
        });

        //----------------Set up user name and address-------------
        SessionManager sessionManager = new SessionManager(this);
        int userID = sessionManager.getUserID();
        executor.execute(()->{
            User user= userRepository.getUserById(userID);
            runOnUiThread(()->{
                addressReceiver.setText(user.getUserName()+" - 01805108004");
                addressDetail.setText(user.getLocation());
            });
        });

        if(Objects.equals(paymentName, paymentNames[3])){
            checkOutPayButton.setText("Place Order");
        }

        checkOutPayButton.setOnClickListener(v -> {
            String paymentCard = paymentNames[selectedPaymentMethodId - 1];
            String amount = totalPrice.getText().toString();
            boolean isCOD = selectedPaymentMethodId == 4;
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_payment_confirmation, null);
            TextView confirmationText = dialogView.findViewById(R.id.paymentConfirmationText);
            TextView confirmationAmount = dialogView.findViewById(R.id.paymentConfirmationAmount);
            MaterialButton dialogPaymentButton = dialogView.findViewById(R.id.dialogPaymentButton);
            confirmationText.setText("Do you want to continue with " + paymentCard + "?");
            confirmationAmount.setText(amount);
            // Change the button according to the payment method
            if (isCOD) {
                dialogPaymentButton.setText("Place Order");
            } else {
                dialogPaymentButton.setText("Pay " + amount);
            }
            AlertDialog dialog = new MaterialAlertDialogBuilder(this)
                    .setView(dialogView)
                    .setNegativeButton("Cancel", (d, which) -> d.dismiss())
                    .create();
            dialogPaymentButton.setOnClickListener(button -> {
                dialog.dismiss();
                if (isCOD) {
                    // TODO: Save the order as Cash on Delivery
                    Toast.makeText(this, "Continue with COD order", Toast.LENGTH_SHORT).show();
                } else {
                    // TODO: Start the selected online payment process
                    Toast.makeText(this, "Continue with " + paymentCard, Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
        });



    }//on create

    @SuppressLint("SetTextI18n")
    private void updatePaymentDetails() {
        int shippingIndex = selectedShippingMethodId - 1;
        // Shipping fee in full currency units
        checkoutShippingFee = shippingPricesText[shippingIndex];
        shippingCostValue.setText("Rp " + checkoutShippingFee);
        voucherDiscountValue.setText("-50"); // Demo voucher
        int total = productSubTotal + checkoutShippingFee - 50;
        totalPrice.setText("Rp " + total);
    }

    @SuppressLint("SetTextI18n")
    private void setupShippingOptions() {
        LinearLayout container = findViewById(R.id.shippingOptionsContainer);
        for (int i = 0; i < shippingNames.length; i++) {
            // Create one shipping card from the reusable XML
            View item = getLayoutInflater().inflate(R.layout.item_shipping_method, container, false); // loops and adds the same layout
            MaterialCardView card = item.findViewById(R.id.shippingOptionCard);
            View indicator = item.findViewById(R.id.shippingRadioIndicator);
            TextView name = item.findViewById(R.id.shippingName);
            TextView estimate = item.findViewById(R.id.shippingEstimate);
            TextView price = item.findViewById(R.id.shippingPrice);
            // Fill in this method's information by loop process
            name.setText(shippingNames[i]);
            estimate.setText(shippingEstimates[i]);
            price.setText("Rp "+shippingPricesText[i]);
            // Keep references so their selection can be updated
            shippingCards.add(card);
            shippingIndicators.add(indicator);
            // IDs are 1, 2, 3 and 4
            final int methodId = i + 1;
            card.setOnClickListener(v -> {
                selectShippingMethod(methodId);
                updatePaymentDetails();
            }); // when any card is pressed
            container.addView(item);
        }
        // Apply the default selection
        selectShippingMethod(selectedShippingMethodId);
        checkoutShippingFee = shippingPricesText[selectedShippingMethodId - 1];
        shippingCostValue.setText("Rp" + shippingPricesText[selectedShippingMethodId - 1]);
    }
    private void selectShippingMethod(int methodId) {
        selectedShippingMethodId = methodId;  //updates the index value
        for (int i = 0; i < shippingCards.size(); i++) {
            boolean selected = (i + 1) == methodId;
            // Updates background and border using your color selectors
            shippingCards.get(i).setSelected(selected);
            // Updates the radio indicator using your drawable selector
            shippingIndicators.get(i).setSelected(selected);
        }
    }
    private void setupPaymentOptions() {
        LinearLayout container = findViewById(R.id.paymentOptionsContainer);
        for (int i = 0; i < paymentNames.length; i++) {
            // Create one shipping card from the reusable XML
            View item = getLayoutInflater().inflate(R.layout.item_payment_method, container, false); // loops and adds the same layout
            MaterialCardView card = item.findViewById(R.id.paymentOptionCard);
            View indicator = item.findViewById(R.id.paymentRadioIndicator);
            TextView name = item.findViewById(R.id.paymentName);
            TextView icons = item.findViewById(R.id.paymentIcon);
            // Fill in this method's information by loop process
            name.setText(paymentNames[i]);
            icons.setText(paymentIcons[i]);
            // Keep references so their selection can be updated
            paymentCards.add(card);
            paymentIndicators.add(indicator);
            // IDs are 1, 2, 3 and 4
            final int paymentMethodId = i + 1;
            card.setOnClickListener(v -> {
                if(paymentMethodId==4){
                    checkOutPayButton.setText("Place Order");
                }else checkOutPayButton.setText("Pay Now");
                selectPaymentMethod(paymentMethodId);
            }); // when any card is pressed
            container.addView(item);
        }
        // Apply the default selection
        selectPaymentMethod(selectedPaymentMethodId);
    }
    private void selectPaymentMethod(int methodId) {
        selectedPaymentMethodId = methodId;
        for (int i = 0; i < paymentCards.size(); i++) {
            boolean selected = (i + 1) == methodId;
            // Updates background and border using your color selectors
            paymentCards.get(i).setSelected(selected);
            // Updates the radio indicator using your drawable selector
            paymentIndicators.get(i).setSelected(selected);

        }
    }
}