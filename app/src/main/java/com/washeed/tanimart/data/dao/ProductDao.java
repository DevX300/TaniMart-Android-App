package com.washeed.tanimart.data.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.washeed.tanimart.data.ProductCard;
import com.washeed.tanimart.data.entities.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    long insert(Product product);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

    @Query("SELECT * FROM product")
    List<Product> getAllProducts();

    @Query("SELECT * FROM product WHERE productID = :productID")
    LiveData<Product> getProductById(int productID);

//    @Query("SELECT * FROM product WHERE categoryID = :categoryID")
//    LiveData<List<ProductCard>> getProductsByCategory(int categoryID);


    @Query("SELECT p.productID, p.productImageID, p.productName, " +
            "p.rating, p.totalReviews, p.discountPercentage, " +
            "pr.priceID, pr.weightKG, pr.totalPrice " +
            "FROM product AS p " +
            "INNER JOIN price AS pr ON p.productID = pr.productID " +
            "WHERE p.categoryID = :categoryID " +
            "AND pr.weightKG = (" +
            "SELECT MIN(pr2.weightKG) " +
            "FROM price AS pr2 " +
            "WHERE pr2.productID = p.productID)")
    LiveData<List<ProductCard>> getProductsByCategory(int categoryID);


    @Query("SELECT * FROM product WHERE productName LIKE '%' || :searchText || '%'")
    List<Product> searchProducts(String searchText);

    @Query("DELETE FROM product")
    void deleteAll();

    //special product discount
    @Query("SELECT * FROM product WHERE categoryID")
    List<Product> getDiscountedProducts();

    @Query("SELECT p.productID, p.productImageID, p.productName, " +
            "p.rating, p.totalReviews, p.discountPercentage, " +
            "pr.priceID, pr.weightKG, pr.totalPrice " +
            "FROM product AS p " +
            "INNER JOIN price AS pr ON p.productID = pr.productID " +
            "WHERE pr.weightKG = (" +
            "SELECT MIN(pr2.weightKG) FROM price AS pr2 " +
            "WHERE pr2.productID = p.productID) " +
            "ORDER BY p.productID ASC")
    List<ProductCard> getProductCards();

    @Query("SELECT p.productID, p.productImageID, p.productName, " +
            "p.rating, p.totalReviews, p.discountPercentage, " +
            "pr.priceID, pr.weightKG, pr.totalPrice " +
            "FROM product AS p " +
            "INNER JOIN price AS pr ON p.productID = pr.productID " +
            "WHERE p.discountPercentage > 0 " +
            "AND pr.weightKG = (" +
            "SELECT MIN(pr2.weightKG) FROM price AS pr2 " +
            "WHERE pr2.productID = p.productID) " +
            "ORDER BY p.discountPercentage DESC")
    LiveData<List<ProductCard>> discountedProductCards();

    @Query("SELECT COUNT(*) FROM product WHERE categoryID = :categoryId")
    int getProductCountByCategory(int categoryId);




    //to search products from the product lisitngs
    @Query("SELECT p.productID, p.productImageID, p.productName, " +
                    "p.rating, p.totalReviews, p.discountPercentage, " +
                    "pr.priceID, pr.weightKG, pr.totalPrice " +
                    "FROM product p " +
                    "INNER JOIN price pr ON p.productID = pr.productID " +               // here price table is joined with the product table by product id
                    "WHERE p.categoryID = :categoryID " +
                    "AND p.productName LIKE '%' || :keyword || '%' " +
                    "AND pr.weightKG = (" + "SELECT MIN(pr2.weightKG) " + "FROM price pr2 " + "WHERE pr2.productID = p.productID)"
    )
    LiveData<List<ProductCard>> searchProductsByCategory(int categoryID, String keyword);
}