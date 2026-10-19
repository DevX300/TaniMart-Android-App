package com.washeed.tanimart.data;

import androidx.room.Embedded;

import com.washeed.tanimart.data.entities.Category;

public class CategoryWithCount {

    @Embedded
    public Category category;

    public int productCount;
}
