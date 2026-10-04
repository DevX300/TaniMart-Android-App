package com.washeed.tanimart.data.repository;

import com.washeed.tanimart.data.Entities.PromoReward;
import com.washeed.tanimart.data.Entities.UserPromo;
import com.washeed.tanimart.data.dao.PromoRewardDao;
import com.washeed.tanimart.data.dao.UserPromoDao;

import java.util.List;

public class PromoRepository {

    PromoRewardDao promoRewardDao;
    UserPromoDao userPromoDao;

    public PromoRepository(PromoRewardDao promoRewardDao, UserPromoDao userPromoDao) {
        this.promoRewardDao = promoRewardDao;
        this.userPromoDao = userPromoDao;
    }

    //---------------PromoReward Logic----------------------

    public void promoInsert(PromoReward promoReward){
        promoRewardDao.insert(promoReward);
    }
    public void promoUpdate(PromoReward promoReward){
        promoRewardDao.update(promoReward);
    }
    public void promoDelete(PromoReward promoReward){
        promoRewardDao.delete(promoReward);
    }

    public List<PromoReward> getAllPromos(){
        return promoRewardDao.getAllPromos();
    }
    public PromoReward getPromoById(int promoID){
        return promoRewardDao.getPromoById(promoID);
    }
    public PromoReward getPromoByCode(String promoCode){
        return promoRewardDao.getPromoByCode(promoCode);
    }

    //---------------UserPromo Logic------------------------

    public void userPromoInsert(UserPromo userPromo){
        userPromoDao.insert(userPromo);
    }
    public void userPromoUpdate(UserPromo userPromo){
        userPromoDao.update(userPromo);
    }
    public void userPromoDelete(UserPromo userPromo){
        userPromoDao.delete(userPromo);
    }
    public List<UserPromo> getPromoByUser(int userID){
        return userPromoDao.getPromosByUser(userID);
    }
    public UserPromo getUserPromo(int userID, int promoID){
        return userPromoDao.getUserPromo(userID, promoID);
    }
}
