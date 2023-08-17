package com.example.and2_assignmentfinal.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.and2_assignmentfinal.Database.DBHelper;
import com.example.and2_assignmentfinal.Model.Avatar;
import com.example.and2_assignmentfinal.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final DBHelper dbHelper;

    public ProductDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    //    hàm hiển thị
    public List<Product> getListPD() {
        List<Product> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try {
            Cursor cursor = database.rawQuery("select * from Products", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    list.add(new Product(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getInt(3),
                            cursor.getString(4)));
                } while (cursor.moveToNext());
                database.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.e("Error", "getListPD(): " + e);
        }
        database.endTransaction();
        return list;
    }

    //    hàm them
    public boolean addProducts(Product product) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nameSP", product.getName());
        values.put("PriceSP", String.valueOf(product.getPrice()));
        values.put("Amount", String.valueOf(product.getAmount()));
        values.put("Avatar", product.getAvatar());

        long check = database.insert("Products", null, values);
        return check != -1;
    }

    //    hàm xoá
    public boolean deleteProducts(int ID) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        long check = database.delete("Products", "ID=?", new String[]{String.valueOf(ID)});
        return check != -1;
    }

    //    hàm sửa
    public boolean updateProducts(Product product) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nameSP", product.getName());
        values.put("PriceSP", String.valueOf(product.getPrice()));
        values.put("Amount", String.valueOf(product.getAmount()));
        values.put("Avatar", product.getAvatar());

        long check = database.update("Products", values, "ID=?", new String[]{String.valueOf(product.getID())});
        return check != -1;
    }
}










