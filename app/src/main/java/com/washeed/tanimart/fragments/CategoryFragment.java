package com.washeed.tanimart.fragments;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.washeed.tanimart.R;
import com.washeed.tanimart.activities.ProductListingActivity;
import com.washeed.tanimart.adapter.CategoryAdapter;
import com.washeed.tanimart.adapter.categoryScreenAdapter;
import com.washeed.tanimart.data.AppDatabase;
import com.washeed.tanimart.data.repository.ProductRepository;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {


    ImageView categoryBack;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_fragment, container, false);

        RecyclerView categoryRecyclerView = view.findViewById(R.id.categoryListView);
        categoryRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        ProductRepository productsRepository = new ProductRepository(AppDatabase.getDatabase(requireContext()).productDao(),AppDatabase.getDatabase(requireContext()).categoryDao(), AppDatabase.getDatabase(requireContext()).priceDao());
        categoryScreenAdapter categoryAdapter = new categoryScreenAdapter(new ArrayList<>(), category -> {
            Intent intent = new Intent(requireContext(), ProductListingActivity.class);
            intent.putExtra("categoryId", category.category.getCategoryID());
            intent.putExtra("categoryName", category.category.getCategoryName());
            intent.putExtra("filters", category.category.getFilters());
            startActivity(intent);
        });
        categoryRecyclerView.setAdapter(categoryAdapter);
        productsRepository.getCategoriesWithCount().observe(getViewLifecycleOwner(), categoryAdapter::setCategories);


        categoryBack= view.findViewById(R.id.categoryBack);
        categoryBack.setOnClickListener(v -> {
            requireActivity().getOnBackPressedDispatcher().onBackPressed();
        });
        return view;
    }


}
