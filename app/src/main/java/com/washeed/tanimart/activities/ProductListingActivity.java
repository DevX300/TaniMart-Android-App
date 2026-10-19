package com.washeed.tanimart.activities;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.RangeSlider;
import com.washeed.tanimart.R;
import com.washeed.tanimart.adapter.FixedFilterButtonsAdapter;
import com.washeed.tanimart.adapter.ProductAdapter;
import com.washeed.tanimart.data.AppDatabase;
import com.washeed.tanimart.data.ProductCard;
import com.washeed.tanimart.data.repository.ProductRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductListingActivity extends AppCompatActivity {

    TextView categoryNameText;
    MaterialButton sortList, filterButton;
    LinearLayout filterLayout;
    Boolean isAscending=true;
    Boolean isFilter=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_listing);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        Intent getIntent= getIntent();
        int categoryID = getIntent.getIntExtra("categoryId",-1);
        String categoryName= getIntent.getStringExtra("categoryName");
        String categoryFilters= getIntent.getStringExtra("filters");
        List<String> filterList = Arrays.asList(categoryFilters.split(","));

        categoryNameText= findViewById(R.id.productListingName);
        categoryNameText.setText(categoryName);
        //-----------------------Setting Product Adapter------------------------
        ProductRepository productsRepository = new ProductRepository(
                AppDatabase.getDatabase(this).productDao(),
                AppDatabase.getDatabase(this).categoryDao(),
                AppDatabase.getDatabase(this).priceDao()
        );
        RecyclerView productRecyclerView = findViewById(R.id.productListingView);
        productRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));   //grid layout is used for grid with columns and their spans
        ProductAdapter productAdapter = new ProductAdapter((productInfo, position) ->{
            Intent showProduct = new Intent(this, ProductDetailActivity.class);
            showProduct.putExtra("productID", productInfo.getProductID());
            startActivity(showProduct);
        });
        productRecyclerView.setAdapter(productAdapter);
        productsRepository.getProductsByCategory(categoryID).observe( this, productAdapter::setProduct);

        //---------------------------------------------
        //----------------Search Products--------------------
        //---------------------------------------------
        SearchView searchBar = findViewById(R.id.searchBarProductListing);
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                productsRepository.searchProductsByCategory(categoryID, newText).observe(ProductListingActivity.this, productAdapter::setProduct);
                return true;
            }
            @Override
            public boolean onQueryTextSubmit(String query) {return false;}
        });
        searchBar.setOnClickListener(v -> {
            searchBar.setIconified(false);
            searchBar.requestFocus();
        });

        //----------------------Filter Toggle-------------------------
        filterLayout= findViewById(R.id.filterLayout);
        filterButton= findViewById(R.id.filterProductListing);
        filterButton.setOnClickListener(v -> {
            if (!isFilter){
                filterLayout.setVisibility(VISIBLE);
                isFilter=true;
            }
            else {
                filterLayout.setVisibility(GONE);
                isFilter=false;
            }
        });

        //---------------------------------------------
        //------------Fixed Filter Buttons-------------
        //---------------------------------------------
        RecyclerView filterRecyclerView = findViewById(R.id.fixedFilterButtons);
        filterRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)); //linear layout is used for horizontal or verticl view only
        FixedFilterButtonsAdapter fixedFilterButtonsAdapter = new FixedFilterButtonsAdapter((CommonName, position) -> {
            if (!CommonName.equals("All")) {
                productsRepository.searchProductsByCategory(categoryID, CommonName).observe(ProductListingActivity.this, productAdapter::setProduct);
            }
            if (CommonName.equals("All")){
                productsRepository.getProductsByCategory(categoryID).observe(ProductListingActivity.this, productAdapter::setProduct);
            }
        });
        filterRecyclerView.setAdapter(fixedFilterButtonsAdapter);
        fixedFilterButtonsAdapter.setFixedCommonNames(filterList);
        //---------------------------------------------
        //----------------Sort List by ASC and DSC--------------------
        //---------------------------------------------
        sortList = findViewById(R.id.sortProductListing);
        sortList.setOnClickListener(v -> {
            productAdapter.reverseProducts();
            if (isAscending) {
                sortList.setIconResource(R.drawable.outline_arrow_upward_alt_24);
            } else {
                sortList.setIconResource(R.drawable.outline_arrow_downward_alt_24);
            }
            isAscending = !isAscending;
        });
        //---------------------------------------------
        //----------------Filter More--------------------
        //---------------------------------------------



        //Must Learn Price Range Slider logic
        RangeSlider priceRangeSlider = findViewById(R.id.priceRangeSlider);
        EditText minPriceInput = findViewById(R.id.minPriceInput);
        EditText maxPriceInput = findViewById(R.id.maxPriceInput);
        // Slider -> EditTexts
        TextWatcher priceWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    String minText = minPriceInput.getText().toString();
                    String maxText = maxPriceInput.getText().toString();
                    if (!minText.isEmpty() && !maxText.isEmpty()) {
                        float min = Float.parseFloat(minText);
                        float max = Float.parseFloat(maxText);
                        if (min >= 0 && max <= 10000 && min < max && min % 100 == 0 && max % 100 == 0) {
                            priceRangeSlider.setValues(min, max);
                        }
                    }
                } catch (NumberFormatException ignored) {}
            }
            @Override
            public void afterTextChanged(Editable s) {}
        };
        minPriceInput.addTextChangedListener(priceWatcher);
        maxPriceInput.addTextChangedListener(priceWatcher);
        // Slider → EditTexts
        priceRangeSlider.addOnChangeListener((slider, value, fromUser) -> {
            List<Float> values = slider.getValues();
            minPriceInput.setText(String.valueOf(values.get(0).intValue()));
            maxPriceInput.setText(String.valueOf(values.get(1).intValue()));
        });

    }



    private void resetPriceInputs(RangeSlider slider, EditText minInput, EditText maxInput) {
        List<Float> values = slider.getValues();
        minInput.setText(String.valueOf(values.get(0).intValue()));
        maxInput.setText(String.valueOf(values.get(1).intValue()));
    }
}