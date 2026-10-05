package com.washeed.tanimart.data.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "track")
public class Track {

    @PrimaryKey(autoGenerate = true)
    private int trackId;
    private int orderID;
    private String deliveryLocation;
    private String description;
    private long deliveryDate;
    private String trackingStatus;

    public Track(int orderID, String deliveryLocation, String description, long deliveryDate, String trackingStatus) {
        this.orderID = orderID;
        this.deliveryLocation = deliveryLocation;
        this.description = description;
        this.deliveryDate = deliveryDate;
        this.trackingStatus = trackingStatus;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(long deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getTrackingStatus() {
        return trackingStatus;
    }

    public void setTrackingStatus(String trackingStatus) {
        this.trackingStatus = trackingStatus;
    }
}
