package com.washeed.tanimart.data.repository;

import androidx.lifecycle.LiveData;

import com.washeed.tanimart.data.CategoryWithCount;
import com.washeed.tanimart.data.ProductCard;
import com.washeed.tanimart.data.dao.ProductSpecificationDao;
import com.washeed.tanimart.data.entities.Category;
import com.washeed.tanimart.data.entities.Price;
import com.washeed.tanimart.data.entities.Product;
import com.washeed.tanimart.data.dao.CategoryDao;
import com.washeed.tanimart.data.dao.PriceDao;
import com.washeed.tanimart.data.dao.ProductDao;
import com.washeed.tanimart.data.entities.ProductSpecification;

import java.util.List;

public class ProductRepository {
    ProductDao productDao;
    CategoryDao categoryDao;
    PriceDao priceDao;
    ProductSpecificationDao productSpecificationDao;

    public ProductRepository(ProductDao productDao, CategoryDao categoryDao, PriceDao priceDao) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
        this.priceDao = priceDao;
    }

    public ProductRepository(ProductDao productDao, CategoryDao categoryDao, PriceDao priceDao, ProductSpecificationDao productSpecificationDao) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
        this.priceDao = priceDao;
        this.productSpecificationDao = productSpecificationDao;
    }

    public ProductRepository(ProductDao productDao) {
        this.productDao = productDao;
    }

    public ProductRepository(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public ProductRepository(PriceDao priceDao) {
        this.priceDao = priceDao;
    }

    public ProductRepository(ProductSpecificationDao productSpecificationDao) {
        this.productSpecificationDao = productSpecificationDao;
    }

    //--------------Product Logic----------------
    public long productInsert(Product product){
        return productDao.insert(product);
    }
    public void productUpdate(Product product){
        productDao.update(product);
    }
    public void productDelete(Product product){
        productDao.delete(product);
    }
    public LiveData<Product> getProductById(int productID){
        return productDao.getProductById(productID);
    }
    public List<Product> getAllProducts(){
        return productDao.getAllProducts();
    }
    public  LiveData<List<ProductCard>> getProductsByCategory(int categoryID){
        return productDao.getProductsByCategory(categoryID);
    }
    public List<Product> searchProducts(String value){
        return productDao.searchProducts(value);
    }
    public int getProductCountByCategory(int categoryId){
        return  productDao.getProductCountByCategory(categoryId);
    }

    public LiveData<List<ProductCard>> searchProductsByCategory(int categoryID, String keyword){
        return productDao.searchProductsByCategory(categoryID,keyword);
    }


    //----------Category Logic------------

    public long categoryInsert(Category category){
        return categoryDao.insert(category);
    }
    public void categoryUpdate(Category category){
        categoryDao.update(category);
    }
    public void categoryDelete(Category category){
        categoryDao.delete(category);
    }
    public LiveData<List<Category>>  categoryList(){
        return categoryDao.getAllCategories();
    }
    public List<Integer> getAllCategoryIds(){
        return categoryDao.getAllCategoryIds();
    }
    public Category getCategoryById(int categoryID){
        return categoryDao.getCategoryById(categoryID);
    }

    public LiveData<List<CategoryWithCount>> getCategoriesWithCount(){
        return categoryDao.getCategoriesWithCount();
    }

    //-------------Price Logic------------

    public long priceInsert(Price price){
        return priceDao.insert(price);
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
    public  LiveData<List<Price>> getPricesByProduct(int productID){return priceDao.getPricesByProduct(productID);}

    //-------------Product Spcification Logic--------
    public long specificationInsert(ProductSpecification specification){
        return productSpecificationDao.insert(specification);
    }

    public List<Long> specificationInsertAll(List<ProductSpecification> specifications){
        return productSpecificationDao.insertAll(specifications);
    }

    public void specificationUpdate(ProductSpecification specification){
        productSpecificationDao.update(specification);
    }

    public void specificationDelete(ProductSpecification specification){
        productSpecificationDao.delete(specification);
    }

    public void specificationDeleteById(int specificationId){
        productSpecificationDao.deleteById(specificationId);
    }

    public ProductSpecification getSpecificationById(int specificationId){
        return productSpecificationDao.getSpecificationById(specificationId);
    }

    public LiveData<List<ProductSpecification>> getSpecificationsByProductId(int productId){
        return productSpecificationDao.getSpecificationsByProductId(productId);
    }

    public void deleteSpecificationsByProductId(int productId){
        productSpecificationDao.deleteSpecificationsByProductId(productId);
    }


    //-------------------connected product card

    public List<ProductCard> getProductCards() {
        return productDao.getProductCards();
    }


    public LiveData<List<ProductCard>> discountedProductCards(){
        return productDao.discountedProductCards();
    }
}