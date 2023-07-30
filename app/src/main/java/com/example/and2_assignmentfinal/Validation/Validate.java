package com.example.and2_assignmentfinal.Validation;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;


import com.example.and2_assignmentfinal.Database.DBHelper;

public class Validate {

    private Context context;
    DBHelper dbHelper;

    public Validate(Context context, DBHelper dbHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
    }



    public boolean ValdateForgot(String nPass, String nPassConfirm,String Username){
        if(nPass.isEmpty()||nPassConfirm.isEmpty()){
            Toast.makeText(context, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
            return  false;
        } else if (!dbHelper.checkUsername(Username)) {
            Toast.makeText(context, "Account Already Exists", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!nPass.equalsIgnoreCase(nPassConfirm)) {
            Toast.makeText(context, "Password Mismatch !", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean ValidateRegister( String UserName, String Pass, String Passag, String Fullname) {
        if (UserName.isEmpty() || Pass.isEmpty() || Passag.isEmpty()||Fullname.isEmpty()) {
            Toast.makeText(context, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
            return false;
        } else if (dbHelper.checkUsername(UserName)) {
            Toast.makeText(context, "Account Already Exists", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Pass.equalsIgnoreCase(Passag)) {
            Toast.makeText(context, "Password Mismatch !", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public boolean ValidateLogin(String Username,String Pass){
        if (Username.isEmpty()||Pass.isEmpty()){
            Toast.makeText(context, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
            return false;
        } else if (dbHelper.Login(Username,Pass)!=1) {
            Toast.makeText(context, "Wrong Account Or Password", Toast.LENGTH_SHORT).show();
            return false;
        }else if (dbHelper.Login(Username,Pass)==1){
            SharedPreferences preferences= context.getSharedPreferences("data",0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("Username",Username);
            editor.apply();//lưu dữ liệu với key và value
            return true;
        }
        return true;
    }
}


























