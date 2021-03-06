package org.niray.wechatmoneyhook.util;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MyProvider extends ContentProvider {
    private final static String AUTHORITIES = "com.example.hookdemo.provider";
    private final static int DICE = 1;
    private final static int MORRA = 2;
    private static UriMatcher mUriMatcher;

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(AUTHORITIES, "wx_plugs_setting", DICE); // 骰子
        mUriMatcher.addURI(AUTHORITIES, "wx_plugs_setting/#", DICE); // 骰子
        mUriMatcher.addURI(AUTHORITIES, "wx_plugs_setting", MORRA); // 猜拳
        mUriMatcher.addURI(AUTHORITIES, "wx_plugs_setting/#", MORRA); // 猜拳
    }

    private MyDatabaseHelper mMyDatabaseHelpser;

    @Override
    public boolean onCreate() {
        mMyDatabaseHelpser = new MyDatabaseHelper(getContext());
        return mMyDatabaseHelpser != null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return DaoHandler.getInstance().query(projection, selection, selectionArgs, sortOrder);
    }

    @Override
    public String getType(Uri uri) {
        return "vnd.android.cursor.dir/vnd." + AUTHORITIES + "" + MyDatabaseHelper.TABLE_NAME;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
