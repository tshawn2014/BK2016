package com.hit.cqcoder.bk;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.security.PublicKey;


/**
 * Created by Administrator on 2015/7/7.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper{
    public static final String CREATE_ITEM = "create table Item("
            +"id integer primary key autoincrement, "
            +"cost real, "
            +"content text, "
            +"date text, "
            +"sort text)";
    private Context mContext;

    public MyDatabaseHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_ITEM);
        Log.d("Database","Success");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
