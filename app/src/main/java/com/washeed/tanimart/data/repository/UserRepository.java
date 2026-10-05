package com.washeed.tanimart.data.repository;

import com.washeed.tanimart.data.dao.UserDao;
import com.washeed.tanimart.data.entities.User;

public class UserRepository {
    UserDao userDao;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    //-------------User Logic--------------

    public long userInsert(User user){
        return userDao.insert(user);
    }
    public void userUpdate(User user){
        userDao.update(user);
    }
    public void userDelete(User user){
        userDao.delete(user);
    }
    public User userLogin(String email, String password){
        return userDao.login(email, password);
    }
    public User getUserById(int userID){
        return userDao.getUserById(userID);
    }

}
