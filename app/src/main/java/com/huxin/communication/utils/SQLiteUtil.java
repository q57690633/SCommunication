package com.huxin.communication.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.huxin.communication.base.HomeFragmentMsgDBHelper;

public class SQLiteUtil {

    private Context mContext;
    private static SQLiteUtil SQLiteUtil;
    private HomeFragmentMsgDBHelper helper;
    private SQLiteDatabase database;

    public SQLiteUtil(Context context) {
        this.mContext = context;
        helper = new HomeFragmentMsgDBHelper(mContext);
        database = helper.getWritableDatabase();
    }

    private SQLiteUtil getInstance(Context context) {
        if(SQLiteUtil == null) {
            synchronized (SQLiteUtil.class) {
                if(SQLiteUtil == null) {
                    SQLiteUtil = new SQLiteUtil(context);
                }
            }
        }
        return SQLiteUtil;
    }

    public void insert(String tableName, ContentValues values) {
        if(database != null) {
            if(!database.isOpen()) {
                database = helper.getWritableDatabase();
            }
            database.insert(tableName, null, values);
        }
    }

    public void update(String tableName, ContentValues contentValues, String whereClause, String[] whereArgs) {
        if(database != null) {
            if(!database.isOpen()) {
                database = helper.getWritableDatabase();
            }
            database.update(tableName, contentValues, whereClause, whereArgs);
        }
    }

    public void delete(String tableName, String whereClause, String[] whereArgs) {
        if(database != null) {
            if(!database.isOpen()) {
                database = helper.getWritableDatabase();
            }
            database.delete(tableName, whereClause, whereArgs);
        }
    }

    public Cursor query(String tableName, String[] columns) {
        Cursor cursor = null;
        if(database != null) {
            if(!database.isOpen()) {
                database = helper.getWritableDatabase();
            }
            cursor = database.query(false, tableName, columns, null, null, null, null, null, null);
        }
        return cursor;
    }

}
