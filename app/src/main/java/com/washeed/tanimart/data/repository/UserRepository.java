package com.washeed.tanimart.data.repository;

import com.washeed.tanimart.data.Entities.User;
import com.washeed.tanimart.data.dao.UserDao;

public class UserRepository {
    UserDao userDao;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    //-------------User Logic--------------

    public void userInsert(User user){
        userDao.insert(user);
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


}
