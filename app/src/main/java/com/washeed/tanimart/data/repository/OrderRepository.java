package com.washeed.tanimart.data.repository;

import com.washeed.tanimart.data.entities.Order;
import com.washeed.tanimart.data.entities.OrderItem;
import com.washeed.tanimart.data.entities.Track;
import com.washeed.tanimart.data.dao.OrderDao;
import com.washeed.tanimart.data.dao.OrderItemDao;
import com.washeed.tanimart.data.dao.TrackDao;

import java.util.List;

public class OrderRepository {
    OrderDao orderDao;
    OrderItemDao orderItemDao;
    TrackDao trackDao;

    public OrderRepository(OrderDao orderDao, OrderItemDao orderItemDao, TrackDao trackDao) {
        this.orderDao = orderDao;
        this.orderItemDao = orderItemDao;
        this.trackDao = trackDao;
    }

    //----------------Order logic--------------------------------

    public long orderInsert(Order order){
        return orderDao.insert(order);
    }
    public void orderUpdate(Order order){
        orderDao.update(order);
    }
    public void orderDelete(Order order){
        orderDao.delete(order);
    }
    //for my order section
    public List<Order> getOrdersByUser(int userID){
        return orderDao.getOrdersByUser(userID);
    }
    public Order getOrderByIds(int orderID, int userID){
        return orderDao.getOrderByIds(orderID, userID);
    }

    //---------------Order Item Logic-------------------------

    public long orderItemInsert(OrderItem orderItem){
        return orderItemDao.insert(orderItem);
    }
    public void orderItemUpdate(OrderItem orderItem){
        orderItemDao.update(orderItem);
    }
    public void orderItemDelete(OrderItem orderItem){
        orderItemDao.delete(orderItem);
    }
    public List<OrderItem> getItemsByOrder(int orderID){
        return orderItemDao.getItemsByOrder(orderID);
    }  //gets list of items by specific orderID
    public void deleteItemsByOrder(int orderID){
        orderItemDao.deleteItemsByOrder(orderID);
    }  //deletes list of items by specific orderID

    public OrderItem getOrderItemById(int orderItemID){
        return orderItemDao.getOrderItemById(orderItemID);
    } //gets single orderItem for 1 product

    //-------------------Track Logic---------------------------
    public long trackInsert(Track track){
        return trackDao.insert(track);
    }   //automatic insert when order created
    public void trackDelete(Track track){
        trackDao.delete(track);
    }
    public Track getTrackByOrder(int orderID){
        return trackDao.getTrackByOrder(orderID);
    }  // gets the track of a specific order

    public Order getOrderById(int orderID) {
        return orderDao.getOrderById(orderID);
    }

    public Track getTrackById(int trackID) {
        return trackDao.getTrackById(trackID);
    }
}
