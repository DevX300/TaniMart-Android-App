package com.washeed.tanimart.fragments;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.washeed.tanimart.R;
import com.washeed.tanimart.activities.ProductDetailActivity;
import com.washeed.tanimart.adapter.ArticleAdapter;
import com.washeed.tanimart.adapter.CategoryAdapter;
import com.washeed.tanimart.adapter.ProductAdapter;
import com.washeed.tanimart.data.AppDatabase;
import com.washeed.tanimart.data.DatabasePopulator;
import com.washeed.tanimart.data.repository.ArticleRepository;
import com.washeed.tanimart.data.repository.ProductRepository;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    Button categoryAddButton,productAddButton,articleAddButton,adminPanelButton;
    TextView homeViewCategory;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        //------------------------------------------------
        //----------------Category Section----------------
        //------------------------------------------------
        RecyclerView categoryRecyclerView = view.findViewById(R.id.categoryListView);
        categoryRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        CategoryAdapter categoryAdapter = new CategoryAdapter(new ArrayList<>());
        categoryAdapter.setDisplayLimit(8);
        categoryRecyclerView.setAdapter(categoryAdapter);
        ProductRepository productsRepository = new ProductRepository(
                AppDatabase.getDatabase(requireContext()).productDao(),
                AppDatabase.getDatabase(requireContext()).categoryDao(),
                AppDatabase.getDatabase(requireContext()).priceDao(),
                AppDatabase.getDatabase(requireContext()).productSpecificationDao()
        );
        //            categoryAdapter.setCategories(categories.subList(0,8));
        productsRepository.categoryList().observe(getViewLifecycleOwner(), categoryAdapter::setCategories);

        //------------------------------------------------
        //----------------Best Selling Section------------
        //------------------------------------------------
        RecyclerView bestSellingRecyclerView = view.findViewById(R.id.bestSellingListView);
        bestSellingRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ProductAdapter bestSellingAdapter = new ProductAdapter((productInfo, position) ->{
            Intent showProduct = new Intent(requireContext(), ProductDetailActivity.class);
            showProduct.putExtra("productID", productInfo.getProductID());
            startActivity(showProduct);
        });
        bestSellingAdapter.setDisplayLimit(6);
        bestSellingRecyclerView.setAdapter(bestSellingAdapter);

        productsRepository.discountedProductCards().observe(getViewLifecycleOwner(), bestSellingAdapter::setProduct);

//        productsRepository.discountedProductList().observe(
//                getViewLifecycleOwner(),
//                discountedAdapter::setProduct
//        );

        //------------------------------------------------
        //----------------Article Tips Section------------
        //------------------------------------------------
        RecyclerView articleTipsRecyclerView = view.findViewById(R.id.homeArticleListView);
        articleTipsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        ArticleAdapter articleAdapter = new ArticleAdapter(new ArrayList<>());
        articleAdapter.setDisplayLimit(4);
        articleTipsRecyclerView.setAdapter(articleAdapter);
        ArticleRepository articleRepository = new ArticleRepository(AppDatabase.getDatabase(requireContext()).articleDao());
        //            articleAdapter.setArticles(products.subList(0,4));
        articleRepository.articleList().observe(getViewLifecycleOwner(), articleAdapter::setArticles);
//        adminPanelButton= view.findViewById(R.id.adminPanelButton);
//        adminPanelButton.setOnClickListener(v -> {
//            startActivity(new Intent(getContext(), InsertDataActivity.class));
//        });

        homeViewCategory= view.findViewById(R.id.homeViewCategory);
        homeViewCategory.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new CategoryFragment()).addToBackStack(null).commit();
        });






        categoryAddButton= view.findViewById(R.id.categoryPopulateButton);
        productAddButton= view.findViewById(R.id.productPopulateButton);
        articleAddButton= view.findViewById(R.id.articlePopulateButton);


        categoryAddButton.setOnClickListener(v -> {
            DatabasePopulator.populate(DatabasePopulator.PopulateType.CATEGORY,productsRepository,articleRepository);
        });

        productAddButton.setOnClickListener(v -> {
            DatabasePopulator.populate(DatabasePopulator.PopulateType.PRODUCT, productsRepository, articleRepository);
        });

        articleAddButton.setOnClickListener(v -> {
            DatabasePopulator.populate(DatabasePopulator.PopulateType.ARTICLE,productsRepository,articleRepository);
        });



        return view;
    }
}
