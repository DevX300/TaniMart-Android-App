package com.washeed.tanimart.activities;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.washeed.tanimart.R;
import com.washeed.tanimart.adapter.PriceBoxAdapter;
import com.washeed.tanimart.adapter.ProductSpecificationAdapter;
import com.washeed.tanimart.data.AppDatabase;
import com.washeed.tanimart.data.entities.Price;
import com.washeed.tanimart.data.entities.Product;
import com.washeed.tanimart.data.entities.ProductSpecification;
import com.washeed.tanimart.data.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {
    TextView discountBasePrice, cutPrice, productName, productRatingNumber, reviewNumber, soldNumber, productAvailability, stockNumber, productDiscountPill;
    TextView productDetailDescription;
    ImageView productDetailImage, stockAvailableSign;
    RatingBar productRatingBar;
    RecyclerView productDetailReview, productDetailSpecification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        //-----------To View Model----------
        discountBasePrice = findViewById(R.id.discountBasePrice);
        cutPrice = findViewById(R.id.cutPrice);
        productName = findViewById(R.id.productTitleName);
        productRatingNumber = findViewById(R.id.productRatingNumber);
        reviewNumber = findViewById(R.id.reviewNumber);
        soldNumber = findViewById(R.id.soldNumber);
        productAvailability = findViewById(R.id.productAvailability);
        stockNumber = findViewById(R.id.stockNumber);
        productDiscountPill = findViewById(R.id.productDiscountPill);
        stockAvailableSign = findViewById(R.id.stockAvailableSign);
        productDetailImage = findViewById(R.id.productDetailImage);
        productRatingBar = findViewById(R.id.productRatingBar);
        productDetailDescription = findViewById(R.id.productDetailDescription);
        productDetailReview = findViewById(R.id.productDetailReview);
        productDetailSpecification = findViewById(R.id.productDetailSpecification);

        ProductRepository productRepository = new ProductRepository(AppDatabase.getDatabase(this).productDao(), AppDatabase.getDatabase(this).categoryDao(), AppDatabase.getDatabase(this).priceDao(), AppDatabase.getDatabase(this).productSpecificationDao());
        int productID = getIntent().getIntExtra("productID", -1);
        if (productID != -1) {

            // ------------------------------Set Product Detailed information ---------------------------------------
            productRepository.getProductById(productID).observe(this, product -> {
                //Product Object Has (categoryID, productName, description, productImageID, rating, totalReviews, soldCount, discountPercentage);
                if (product == null) return;
                productName.setText(product.getProductName());
                productRatingNumber.setText(String.valueOf(product.getRating()));
                productRatingBar.setRating(product.getRating());
                reviewNumber.setText("(" + product.getTotalReviews() + ")");
                soldNumber.setText(product.getSoldCount() + " Sold");
                Glide.with(this).load(product.getProductImageID()).centerCrop().placeholder(R.drawable.article1).error(R.drawable.ic_launcher_background).into(productDetailImage);

                // ---------------------------Sets all prices------------------------------------------
                productRepository.getPricesByProduct(productID).observe(this, priceList -> {
                    //Price Object Has (priceID, productID, weightKG, int stock, totalPrice);
                    RecyclerView priceBox = findViewById(R.id.pricePills);
                    priceBox.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                    PriceBoxAdapter priceBoxAdapter = new PriceBoxAdapter( (price, position)->{
                        updateSelectedPrice(price,product);  //Sets pricing visibility and discount visibility by using price pills when selected
                    });
                    priceBox.setAdapter(priceBoxAdapter);
                    priceBoxAdapter.setPriceList(priceList);
                    // Automatically display the first price
                    if (priceList != null && !priceList.isEmpty()) {
                        updateSelectedPrice(priceList.get(0), product);
                    }
                });
                //--------------------Tab Layout ---------------------------------
                TabLayout tabLayout = findViewById(R.id.tabLayout);
                tabLayout.addTab(tabLayout.newTab().setText("Description"));
                tabLayout.addTab(tabLayout.newTab().setText("Specification"));
                tabLayout.addTab(tabLayout.newTab().setText("Reviews"));
                // Default visibility when the page opens
                productDetailDescription.setVisibility(View.VISIBLE);
                productDetailSpecification.setVisibility(View.GONE);
                productDetailReview.setVisibility(View.GONE);
                //set descriptions
                productDetailDescription.setText(product.getDescription());
                //set specifications
                productRepository.getSpecificationsByProductId(productID).observe(ProductDetailActivity.this, specificationList -> {
                    //Specification Has (specificationID, productID, specificationName, specificationValue);
                    ProductSpecificationAdapter productSpecificationAdapter= new ProductSpecificationAdapter(specificationList);
                    productDetailSpecification.setLayoutManager(new LinearLayoutManager(ProductDetailActivity.this, LinearLayoutManager.VERTICAL, false));
                    productDetailSpecification.setAdapter(productSpecificationAdapter);
                });
                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        // Hide all three views first
                        productDetailDescription.setVisibility(View.GONE);
                        productDetailSpecification.setVisibility(View.GONE);
                        productDetailReview.setVisibility(View.GONE);
                        switch (tab.getPosition()) {
                            case 0:
                                productDetailDescription.setVisibility(View.VISIBLE);
//                                productDetailDescription.setText(product.getDescription());
                                break;
                            case 1:
                                //------------------------------------ Sets all specifications ------------------------------------------------
                                productDetailSpecification.setVisibility(View.VISIBLE);
                                break;
                            case 2:
                                //Empty no work
                                productDetailReview.setVisibility(View.VISIBLE);
                                break;
                        }
                    }
                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                    }
                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {}
                });
            });
        }
    }
    //Method to update price list
    private void updateSelectedPrice(Price price, Product product) {
        double basePrice = price.getTotalPrice();
        int percentage = product.getDiscountPercentage();
        //Sets the stock
        if (price.getStock() > 0) {
            stockNumber.setText(String.valueOf(price.getStock()));
            stockAvailableSign.setImageTintList(ColorStateList.valueOf(Color.GREEN));
            productAvailability.setText("In Stock: ");
            stockNumber.setVisibility(View.VISIBLE);
        } else {
            stockAvailableSign.setImageTintList(ColorStateList.valueOf(Color.RED));
            productAvailability.setText("Out of Stock");
            stockNumber.setVisibility(View.GONE);
        }
        //sets the discount
        if (percentage > 0 && percentage <= 100) {
            double discountedPrice = basePrice * (1 - percentage / 100.0);
            discountBasePrice.setText(String.format("RP %.0f", discountedPrice));
            cutPrice.setText(String.format("RP %.0f", basePrice));
            cutPrice.setPaintFlags(cutPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            cutPrice.setVisibility(View.VISIBLE);
            productDiscountPill.setText("-" + percentage + "% OFF");
            productDiscountPill.setVisibility(View.VISIBLE);
        } else {
            discountBasePrice.setText(String.format("RP %.0f", basePrice));
            cutPrice.setVisibility(View.GONE);
            productDiscountPill.setVisibility(View.GONE);
        }
    }
}
