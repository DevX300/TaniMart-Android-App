package com.washeed.tanimart.data.repository;

import com.washeed.tanimart.data.Entities.Cart;
import com.washeed.tanimart.data.dao.CartDao;

import java.util.List;

public class CartRepository {

    CartDao cartDao;
    public CartRepository(CartDao cartDao) {
        this.cartDao = cartDao;
    }
    //----------Cart logic--------------
    public void cartInsert(Cart cart){
        cartDao.insert(cart);
    }
    public void cartUpdate(Cart cart){
        cartDao.update(cart);
    }
    public void cartDelete(Cart cart){
        cartDao.delete(cart);
    }
    public List<Cart> getCartsByUser(int userID){
        return cartDao.getCartsByUser(userID);
    }  //gets all the carts under the user
    public Cart getCartsByIds(int cartID, int userID){
        return cartDao.getCartByIds(cartID, userID);
    }  //gets a specific cart from a specific user
    public void deleteUserCart(int userID){
        cartDao.deleteUserCart(userID);
    } //deletes the cart of a user


}
