package com.washeed.tanimart.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.washeed.tanimart.R;
import com.washeed.tanimart.data.CategoryWithCount;
import com.washeed.tanimart.data.entities.Category;
import com.washeed.tanimart.data.repository.ProductRepository;

import java.util.List;

public class categoryScreenAdapter extends RecyclerView.Adapter<categoryScreenAdapter.CategoryViewHolder> {
    private List<CategoryWithCount> categoryList;
    private OnCategoryClickListener listener;
    public categoryScreenAdapter(List<CategoryWithCount> categoryList, OnCategoryClickListener listener) {
        this.categoryList = categoryList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryWithCount item = categoryList.get(position);
        holder.categoryEmoji.setText(item.category.getCategoryIcon());
        holder.categoryTitle.setText(item.category.getCategoryName());
        holder.productCount.setText(item.productCount + " products");
        holder.itemView.setOnClickListener(v -> {
            listener.onCategoryClick(item);
        });
    }
    @Override
    public int getItemCount() {return categoryList != null ? categoryList.size() : 0;}
    public void setCategories(List<CategoryWithCount> categories) {
        this.categoryList = categories;
        notifyDataSetChanged();
    }
    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView categoryEmoji;
        TextView categoryTitle;
        TextView productCount;
        ImageView getCategory;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryEmoji = itemView.findViewById(R.id.categoryEmoji);
            categoryTitle = itemView.findViewById(R.id.categoryTitle);
            productCount = itemView.findViewById(R.id.productCount);
            getCategory = itemView.findViewById(R.id.getCategory);
        }
    }

    //interface added to handle onitemlongclicklistener
    public interface OnCategoryClickListener {
        void onCategoryClick(CategoryWithCount category);
    }
}
