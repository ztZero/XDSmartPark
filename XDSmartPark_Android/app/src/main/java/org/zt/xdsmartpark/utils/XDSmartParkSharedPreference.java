package org.zt.xdsmartpark.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class XDSmartParkSharedPreference {

    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "org.zt.xdsmartpark";
    public static final String PREF_TOKEN = "token";
    public static final String PREF_USER_NAME = "userName";
    public static final String PREF_USER_ID = "userId";
    public static final String PREF_PHONE = "phone";
    public static final String PREF_CAR_TYPE = "car_type";
    public static final String PREF_CAR_NO = "carPlate";
    public static final String PREF_LOGGED = "logged";
    public static final String PREF_RESERVED = "reserved";
    public static final String PREF_ARRIVED = "arrived";
    public static final String PREF_ID = "reservation_id";
    public static final String PREF_SLOT_NAME = "slot_name";
    public static final String PREF_TIME_FROM = "time_from";
    public static final String PREF_TIME_TO = "time_to";
    public static final String PREF_TIME_START = "time_start";
    public static final String PREF_PRICE = "price";

    public XDSmartParkSharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor clear() {
        sharedPreferences.edit().clear().apply();
        return null;
    }

    public void setString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void setBoolean(String key, Boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public Boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void setInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public void setLong(String key, long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }

    public long getLong(String key) {
        return sharedPreferences.getLong(key, 0);
    }

    public void setFloat(String key, float value) {
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    public float getFloat(String key) {
        return sharedPreferences.getFloat(key, 0);
    }
}
