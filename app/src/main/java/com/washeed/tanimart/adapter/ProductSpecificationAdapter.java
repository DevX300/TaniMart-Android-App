package com.washeed.tanimart.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.washeed.tanimart.R;
import com.washeed.tanimart.data.entities.ProductSpecification;

import java.util.List;

public class ProductSpecificationAdapter extends RecyclerView.Adapter<ProductSpecificationAdapter.SpecificationViewHolder> {
    private List<ProductSpecification> specificationList;
    public ProductSpecificationAdapter(List<ProductSpecification> specificationList) {
        this.specificationList = specificationList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductSpecificationAdapter.SpecificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_specification_item, parent, false);
        return new SpecificationViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ProductSpecificationAdapter.SpecificationViewHolder holder, int position) {
        ProductSpecification specification = specificationList.get(position);
        holder.specificationName.setText(specification.getSpecificationName());
        holder.specificationValue.setText(specification.getSpecificationValue());
    }
    @Override
    public int getItemCount() {
        return specificationList != null ? specificationList.size() : 0;
    }
    static class SpecificationViewHolder extends RecyclerView.ViewHolder{
        TextView specificationName, specificationValue;
        public SpecificationViewHolder(@NonNull View itemView) {
            super(itemView);
            specificationName = itemView.findViewById(R.id.specificationName);
            specificationValue = itemView.findViewById(R.id.specificationValue);
        }
    }
}
