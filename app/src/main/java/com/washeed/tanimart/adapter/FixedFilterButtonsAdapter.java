package com.washeed.tanimart.adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.washeed.tanimart.R;

import java.util.List;

public class FixedFilterButtonsAdapter extends RecyclerView.Adapter<FixedFilterButtonsAdapter.ButtonsViewHolder> {

    private List<String> fixedCommonNames;
    private OnItemClickListener listener;

    private int selectedPosition = 0; //initial position is always (ALL)
    public FixedFilterButtonsAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public FixedFilterButtonsAdapter(List<String> fixedCommonNames) {
        this.fixedCommonNames = fixedCommonNames;
    }
    public void setFixedCommonNames(List<String> fixedCommonNames) {
        this.fixedCommonNames = fixedCommonNames;
    }
    @NonNull
    @Override
    public FixedFilterButtonsAdapter.ButtonsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fixed_common_name_filters_buttons, parent, false);
        return new ButtonsViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ButtonsViewHolder holder, int position) {
        String commonName = fixedCommonNames.get(position);
        holder.fixedFilterButton.setText(commonName);
        // Set color based on selection
        if (position == selectedPosition) {
            holder.fixedFilterButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#2E7D32")));
            holder.fixedFilterButton.setTextColor(Color.WHITE);
        } else {
            holder.fixedFilterButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F3F4F6")));
            holder.fixedFilterButton.setTextColor(Color.parseColor("#1A1A1A"));
        }
        // Handle button click
        holder.fixedFilterButton.setOnClickListener(v -> {                   //when button pressed
            int pos = holder.getBindingAdapterPosition();                         //if there is a position
            if (pos != RecyclerView.NO_POSITION) {
                int previousPosition = selectedPosition;                          //gets the current selected position and assigns as previous posiiton
                selectedPosition = pos;                                           // after pressing the button on the list that button position is assigned to selected position
                notifyItemChanged(previousPosition);                              //updates previous button
                notifyItemChanged(selectedPosition);                              //updates new button
                listener.onItemClick(fixedCommonNames.get(pos), pos);             //calls the listener
            }
        });
    }

    @Override
    public int getItemCount() {
        return fixedCommonNames!=null? fixedCommonNames.size():0;
    }
    static  class ButtonsViewHolder extends RecyclerView.ViewHolder{
        Button fixedFilterButton;
        public ButtonsViewHolder(@NonNull View itemView) {
            super(itemView);
            fixedFilterButton = itemView.findViewById(R.id.fixedCommonNameFilters);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(String CommonName,int position);
    }
}
