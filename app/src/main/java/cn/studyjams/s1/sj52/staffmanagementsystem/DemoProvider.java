package cn.studyjams.s1.sj52.staffmanagementsystem;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Apc on 2016/10/9.
 */
public class DemoProvider extends ContentProvider { //通过DemoProvider对SQLITE做增、删、改、查。

    static final Uri URI = Uri.parse("content://sally.staffmanagementsystem");
    DatabaseHelper databaseHelper;
    @Override
    public boolean onCreate() {
        databaseHelper = new DatabaseHelper(getContext(),"DatabaseHelper", null,1);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = databaseHelper.getReadableDatabase().query("staffsInfos", projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        databaseHelper.getReadableDatabase().insert("staffsInfos", null, values);
        getContext().getContentResolver().notifyChange(uri, null);//来通知注册在此URI上的访问者
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count;
        count = databaseHelper.getReadableDatabase().delete("staffsInfos", selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);  //来通知注册在此URI上的访问者
         if(databaseHelper == null){
            return -1;
        }else
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count;
        count = databaseHelper.getReadableDatabase().update("staffsInfos", values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);  //来通知注册在此URI上的访问者
        if(databaseHelper == null){
            return -1;
        }else
            return count;
    }
}
