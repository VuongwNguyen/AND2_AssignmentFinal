package com.example.and2_assignmentfinal.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "QLSP", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qND = "create table User(Username text primary key,Password text, Fullname text)";
        sqLiteDatabase.execSQL(qND);

        String qSP = "create table Products(ID Integer primary key autoincrement ," +
                "nameSP text,PriceSP Integer,Amount Integer, Avatar text)";
        sqLiteDatabase.execSQL(qSP);
//        nạp dữ liệu giả
        String data = "insert into Products(nameSP ,PriceSP ,Amount , Avatar ) values('bánh quy bơ',20000,10,'ic_all_inclusive')," +
                "('Kẹo Dừa',5000,20,'ic_clear')," +
                "('Bánh Gạo',15000,35,'https://i.pinimg.com/236x/0f/6d/ad/0f6dad872afb74e62a319425a7d40108.jpg')";
        sqLiteDatabase.execSQL(data);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists User");
        sqLiteDatabase.execSQL("drop table if exists Products");

    }

    //    hàm
    public void register(String Username, String Pass, String Fullname) {
        ContentValues values = new ContentValues();
        values.put("Username", Username);
        values.put("Password", Pass);
        values.put("Fullname", Fullname);
        SQLiteDatabase database = getWritableDatabase();
        database.insert("User", null, values);
        database.close();
    }

    public int Login(String User, String Pass) {
        int result = 0;
        String str[] = new String[2];
        str[0] = User;
        str[1] = Pass;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from User where Username=? and Password =?", str);
        if (cursor.moveToNext()) {
            result = 1;
        }
        return result;
    }

    //    hàm check username
    public boolean checkUsername(String Username) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from User where Username=?", new String[]{Username});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    public boolean changeNewPass(String Username, String Password) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Password", Password);
        long result = database.update("User", values, "Username=?",
                new String[]{Username});
        return result != -1;

    }
}
