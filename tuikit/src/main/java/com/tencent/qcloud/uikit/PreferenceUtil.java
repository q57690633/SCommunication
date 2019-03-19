package com.tencent.qcloud.uikit;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * time: 15/7/17
 * description: sharedPreference管理类
 *
 * @author sunjianfei
 */
public class PreferenceUtil {

    public static void putString(Context context, String key, String value) {
        if (!TextUtils.isEmpty(value) & !TextUtils.isEmpty(key)) {
            SharedPreferences.Editor editor = getEditor(context);
            editor.putString(key, value);
            editor.commit();
        }
    }

    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }


    /**
     * 为了兼容1.0.3的版本，所以需要加这个方法，仅限于首次得到用户登陆信息的时候调用
     *
     * @param key    得到字符串的key值
     * @param spName SharePreference的名称
     * @return
     */
    public static String getPreferenceString(Context context, String key, String spName) {
        SharedPreferences preferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
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
    public static void removeSp(Context context, String key, String spName) {
        SharedPreferences preferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
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
    public static boolean getPreferenceBoolean(Context context, String key, String spName, boolean defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
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
    public static String getString(Context context, String key, String defaultValue) {
        String origin = getSharedPreference(context).getString(key, null);
        if (!TextUtils.isEmpty(origin)) {
            return origin;
        }
        return defaultValue;
    }

    public static void putInt(Context context, String key, int value) {
        if (!TextUtils.isEmpty(key)) {
            SharedPreferences.Editor editor = getEditor(context);
            editor.putInt(key, value);
            editor.commit();
        }
    }

    public static void putLong(Context context, String key, long value) {
        if (!TextUtils.isEmpty(key)) {
            SharedPreferences.Editor editor = getEditor(context);
            editor.putLong(key, value);
            editor.commit();
        }
    }

    public static void putBoolean(Context context, String key, boolean value) {
        if (!TextUtils.isEmpty(key)) {
            SharedPreferences.Editor editor = getEditor(context);
            editor.putBoolean(key, value);
            editor.commit();
        }
    }

    public static void remove(Context context, String... keys) {
        SharedPreferences.Editor editor = getEditor(context);
        for (String key : keys) {
            if (!TextUtils.isEmpty(key)) {
                editor.remove(key);
            }
        }
        editor.commit();
    }

    public static int getInt(Context context, String key) {
        return getInt(context, key, 0);
    }

    public static long getLong(Context context, String key) {
        return getLong(context, key,0L);
    }

    public static int getInt(Context context, String key, int defaultValue) {
        return getSharedPreference(context).getInt(key,defaultValue);
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getSharedPreference(context).getBoolean(key,defaultValue);
    }
    public static long getLong(Context context, String key, long defaultValue) {
        return getSharedPreference(context).getLong(key,defaultValue);
    }

    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key,true);
    }

    private static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences("hx", Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences sp = context.getSharedPreferences("hx", Context.MODE_PRIVATE);
        return sp.edit();
    }


}
