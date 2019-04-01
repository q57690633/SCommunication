package com.tencent.qcloud.uikit.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HomeFragmentMsgDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "database.db";

    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "HOME_FRAGMENT";

    public static final String CURRENTUID = "currentUid";
    public static final String UID = "uid";
    public static final String NICKNAME = "nickName";
    public static final String MESSAGE = "message";
    public static final String TIME = "timeStamp";
    public static final String HEAD_URL = "headurl";
    public static final String TYPE = "type";
    public static final String UNREAD_NUM = "num";
    public static final String IS_READ = "isread";



    public HomeFragmentMsgDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String HOME_FRAGMENT_CREATE_TABLE_SQL = "create table if not exists " + TABLE_NAME + "("
                + "id integer primary key autoincrement,"
                + UID + " varchar2(20) unique,"
                + CURRENTUID + " varchar2(20),"
                + NICKNAME + " varchar2(200),"
                + MESSAGE + " varchar2(200),"
                + TIME + " varchar2(140),"
                + HEAD_URL + " varchar2(140),"
                + TYPE + " varchar2(140),"
                + UNREAD_NUM + " varchar2(140),"
                + IS_READ + " varchar2(20)"
                + ");";
        db.execSQL(HOME_FRAGMENT_CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
