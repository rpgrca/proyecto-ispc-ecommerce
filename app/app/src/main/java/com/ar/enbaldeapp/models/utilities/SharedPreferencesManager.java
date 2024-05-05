package com.ar.enbaldeapp.models.utilities;

import static com.ar.enbaldeapp.ui.IntentConstants.CURRENT_USER;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;

import com.ar.enbaldeapp.models.User;

public class SharedPreferencesManager {
    private final String PREFS_NAME = "com.ar.enbaldeapp";
    private final SharedPreferences sharedPreferences;

    public SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveCurrentUser(User user) {
        sharedPreferences.edit().putString(CURRENT_USER, new Gson().toJson(user)).apply();
    }

    public User loadCurrentUser() {
        String json = sharedPreferences.getString(CURRENT_USER, null);
        if (json == null) {
            return null;
        }

        return new Gson().fromJson(json, User.class);
    }

    public void deleteCurrentUser() {
        sharedPreferences.edit().remove(CURRENT_USER).commit();
    }
}
