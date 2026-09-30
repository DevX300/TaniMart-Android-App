package com.washeed.tanimart.Data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Locale;

@Entity(tableName = "product")
public class Product  {
    @PrimaryKey(autoGenerate = true)
    private int productID;
    private int categoryID;
    private String productName;
    private String description;
    private String specification;
    private int productImageID;
}
