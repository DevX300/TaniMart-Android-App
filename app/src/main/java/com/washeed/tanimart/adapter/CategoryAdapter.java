package com.washeed.tanimart.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.washeed.tanimart.R;
import com.washeed.tanimart.data.ProductCard;
import com.washeed.tanimart.data.entities.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<Category> categoryList;
    private int displayLimit = Integer.MAX_VALUE;
    public void setDisplayLimit(int displayLimit) {
        this.displayLimit = displayLimit;
        notifyDataSetChanged();
    }
    public CategoryAdapter(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.emoji.setText(category.getCategoryIcon());   // emoji string
        holder.label.setText(category.getCategoryName());   // category name
    }
    @Override
    public int getItemCount() {
        return categoryList != null ? Math.min(categoryList.size(), displayLimit): 0;
    }
    public void setCategories(List<Category> categories) {
        this.categoryList = categories;
        notifyDataSetChanged();
    }
    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView emoji, label;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            emoji = itemView.findViewById(R.id.homeCategoryEmoji);
            label = itemView.findViewById(R.id.homeCategoryLabel);
        }
    }
}
