package com.example.kidhabit.data;

import android.content.Context;
import android.content.SharedPreferences;

public class UserRepository {

    private static final String PREFS_NAME = "user_prefs";
    private SharedPreferences sharedPreferences;

    public UserRepository(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveName(String name) {
        sharedPreferences.edit().putString("name", name).apply();
    }

    public String getName() {
        return sharedPreferences.getString("name", "");
    }

    public void saveAvatar(String avatar) {
        sharedPreferences.edit().putString("avatar", avatar).apply();
    }

    public String getAvatar() {
        return sharedPreferences.getString("avatar", "");
    }

    public void saveStars(int stars) {
        sharedPreferences.edit().putInt("stars", stars).apply();
    }

    public int getStars() {
        return sharedPreferences.getInt("stars", 0);
    }

    public void saveBadgeUnlocked(int badgeId) {
        sharedPreferences.edit().putBoolean("badge_" + badgeId, true).apply();
    }

    public boolean isBadgeUnlocked(int badgeId) {
        return sharedPreferences.getBoolean("badge_" + badgeId, false);
    }
}
