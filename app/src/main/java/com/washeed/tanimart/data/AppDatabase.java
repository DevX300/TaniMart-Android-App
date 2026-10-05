package com.washeed.tanimart.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.washeed.tanimart.data.entities.*;
import com.washeed.tanimart.data.dao.*;

@Database(entities = {
        User.class,
        Category.class,
        Product.class,
        Price.class,
        Cart.class,
        Order.class,
        OrderItem.class,
        Track.class,
        PromoReward.class,
        UserPromo.class,
        Article.class
},version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract CategoryDao categoryDao();
    public abstract ProductDao productDao();
    public abstract PriceDao priceDao();
    public abstract CartDao cartDao();
    public abstract OrderDao orderDao();
    public abstract OrderItemDao orderItemDao();
    public abstract TrackDao trackDao();
    public abstract PromoRewardDao promoRewardDao();
    public abstract UserPromoDao userPromoDao();
    public abstract ArticleDao articleDao();
    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
