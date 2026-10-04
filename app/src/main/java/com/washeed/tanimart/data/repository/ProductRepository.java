package com.washeed.tanimart.data.repository;

import com.washeed.tanimart.data.Entities.Category;
import com.washeed.tanimart.data.Entities.Price;
import com.washeed.tanimart.data.Entities.Product;
import com.washeed.tanimart.data.dao.CategoryDao;
import com.washeed.tanimart.data.dao.PriceDao;
import com.washeed.tanimart.data.dao.ProductDao;

import java.util.List;

public class ProductRepository {
    ProductDao productDao;
    CategoryDao categoryDao;
    PriceDao priceDao;

    public ProductRepository(ProductDao productDao, CategoryDao categoryDao, PriceDao priceDao) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
        this.priceDao = priceDao;
    }

    //--------------Product Logic----------------
    public void productInsert(Product product){
        productDao.insert(product);
    }
    public void productUpdate(Product product){
        productDao.update(product);
    }
    public void productDelete(Product product){
        productDao.delete(product);
    }
    public Product getProductById(int productID){
        return productDao.getProductById(productID);
    }
    public List<Product> getAllProducts(){
        return productDao.getAllProducts();
    }
    public List<Product> getProductsByCategory(int categoryID){
        return productDao.getProductsByCategory(categoryID);
    }
    public List<Product> searchProducts(String value){
        return productDao.searchProducts(value);
    }
    //----------Category Logic------------

    public void categoryInsert(Category category){
       categoryDao.insert(category);
    }
    public void categoryUpdate(Category category){
        categoryDao.update(category);
    }
    public void categoryDelete(Category category){
        categoryDao.delete(category);
    }
    public List<Category> categoryList(){
        return categoryDao.getAllCategories();
    }
    public Category getCategoryById(int categoryID){
        return categoryDao.getCategoryById(categoryID);
    }
    //-------------Price Logic------------

    public void priceInsert(Price price){
        priceDao.insert(price);
    }
    public void priceUpdate(Price price){
        priceDao.update(price);
    }
    public void priceDelete(Price price){
        priceDao.delete(price);
    }
    public Price getPriceById(int priceID){
        return priceDao.getPriceById(priceID);
    }
    public List<Price> getPricesByProduct(int productID){
        return priceDao.getPricesByProduct(productID);
    }
}
