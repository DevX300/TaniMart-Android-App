package com.washeed.tanimart.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "TaniMartSession";
    private static final String KEY_USER_ID = "userID";
    private final SharedPreferences preferences;
    public SessionManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }
    public void saveUserID(int userID) {
        preferences.edit().putInt(KEY_USER_ID, userID).apply();
    }

    public int getUserID() {
        return preferences.getInt(KEY_USER_ID, -1);
    }
    public void logout() {
        preferences.edit().clear().apply();
    }
}