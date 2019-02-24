package com.huxin.communication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.huxin.communication.controls.Constanst;

import static com.huxin.communication.HuXinApplication.mContext;

/**
 * time: 15/7/17
 * description: sharedPreference管理类
 *
 * @author sunjianfei
 */
public class PreferenceUtil {

    public static void putString(String key, String value) {
        if (!TextUtils.isEmpty(value) & !TextUtils.isEmpty(key)) {
            SharedPreferences.Editor editor = getEditor();
            editor.putString(key, value);
            editor.commit();
        }
    }

    public static String getString(String key) {
        return getString(key, null);
    }


    /**
     * 为了兼容1.0.3的版本，所以需要加这个方法，仅限于首次得到用户登陆信息的时候调用
     *
     * @param key    得到字符串的key值
     * @param spName SharePreference的名称
     * @return
     */
    public static String getPreferenceString(String key, String spName) {
        SharedPreferences preferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        if (null != preferences) {
            return preferences.getString(key, null);
        }
        return null;
    }

    /**
     * 为了兼容1.0.3的版本，所以需要加这个方法，仅限于首次得到用户登陆信息的时候调用
     *
     * @param key    需要删除的key值
     * @param spName SharePreference的名称
     * @return
     */
    public static void removeSp(String key, String spName) {
        SharedPreferences preferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        if (null != preferences) {
            SharedPreferences.Editor editor = preferences.edit();
            if (null != editor) {
                editor.remove(key);
                editor.commit();
            }

        }
    }

    /**
     * 为了兼容1.0.3的版本，所以需要加这个方法，仅限于首次是否是第一次登陆的时候调用
     *
     * @param key    得到boolean值的key值
     * @param spName SharePreference的名称
     * @return
     */
    public static boolean getPreferenceBoolean(String key, String spName, boolean defaultValue) {
        SharedPreferences preferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        if (null != preferences) {
            return preferences.getBoolean(key, defaultValue);
        }
        return false;
    }

    /**
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(String key, String defaultValue) {
        String origin = getSharedPreference().getString(key, null);
        if (!TextUtils.isEmpty(origin)) {
            return origin;
        }
        return defaultValue;
    }

    public static void putInt(String key, int value) {
        if (!TextUtils.isEmpty(key)) {
            SharedPreferences.Editor editor = getEditor();
            editor.putInt(key, value);
            editor.commit();
        }
    }

    public static void putLong(String key, long value) {
        if (!TextUtils.isEmpty(key)) {
            SharedPreferences.Editor editor = getEditor();
            editor.putLong(key, value);
            editor.commit();
        }
    }

    public static void putBoolean(String key, boolean value) {
        if (!TextUtils.isEmpty(key)) {
            SharedPreferences.Editor editor = getEditor();
            editor.putBoolean(key, value);
            editor.commit();
        }
    }

    public static void remove(String... keys) {
        SharedPreferences.Editor editor = getEditor();
        for (String key : keys) {
            if (!TextUtils.isEmpty(key)) {
                editor.remove(key);
            }
        }
        editor.commit();
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static long getLong(String key) {
        return getLong(key,0L);
    }

    public static int getInt(String key, int defaultValue) {
        return getSharedPreference().getInt(key,defaultValue);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return getSharedPreference().getBoolean(key,defaultValue);
    }
    public static long getLong(String key, long defaultValue) {
        return getSharedPreference().getLong(key,defaultValue);
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key,true);
    }

    private static SharedPreferences getSharedPreference() {
        return mContext.getSharedPreferences(Constanst.SP_NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor() {
        SharedPreferences sp = mContext.getSharedPreferences(Constanst.SP_NAME, Context.MODE_PRIVATE);
        return sp.edit();
    }
}
