package com.wildcardenter.myfab.foodie.helpers;

/*
    Class On Package com.wildcardenter.myfab.foodie.helpers
    
    Created by Asif Mondal on 21-09-2019 at 16:53
*/


import android.content.Context;
import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static com.wildcardenter.myfab.foodie.helpers.Constants.TYPE_BOOLEAN;
import static com.wildcardenter.myfab.foodie.helpers.Constants.TYPE_FLOAT;
import static com.wildcardenter.myfab.foodie.helpers.Constants.TYPE_INTEGER;
import static com.wildcardenter.myfab.foodie.helpers.Constants.TYPE_STRING;

public class SharedPref {
    private SharedPreferences sharedPreferences;
    private static SharedPref pref;

    private SharedPref(@NotNull Context context, String filename) {
        sharedPreferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
    }

    public static void setNullMethod() {
        if (pref != null) {
            pref = null;
        }
    }

    public void setNull() {
        pref = null;
    }

    public static SharedPref getSharedPref(Context context, String filename) {
        if (pref == null) {
            pref = new SharedPref(context, filename);
        }
        return pref;
    }


    public void setData(String key, Object value, int type) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (type == TYPE_BOOLEAN) {
            editor.putBoolean(key, (Boolean) value);
        } else if (type == TYPE_STRING) {
            editor.putString(key, (String) value);
        } else if (type == TYPE_INTEGER) {
            editor.putInt(key, (Integer) value);
        } else if (type == TYPE_FLOAT) {
            editor.putFloat(key, (Float) value);
        }
        editor.apply();

    }

    public Object getData(String key, int type) {
        Object data = null;
        if (type == TYPE_BOOLEAN) {
            data = sharedPreferences.getBoolean(key, false);
        } else if (type == TYPE_STRING) {
            data = sharedPreferences.getString(key, "");
        } else if (type == TYPE_INTEGER) {
            data = sharedPreferences.getInt(key, 1);
        } else if (type == TYPE_FLOAT) {
            data = sharedPreferences.getFloat(key, 1f);
        }
        return data;
    }

    public Map<String, ?> getAllvalues() {
        return sharedPreferences.getAll();
    }

    public void removeValues() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
