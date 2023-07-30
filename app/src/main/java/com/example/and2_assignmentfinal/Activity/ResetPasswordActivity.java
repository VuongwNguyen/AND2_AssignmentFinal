package com.example.and2_assignmentfinal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.and2_assignmentfinal.Database.DBHelper;
import com.example.and2_assignmentfinal.R;
import com.example.and2_assignmentfinal.Validation.Validate;
import com.google.android.material.textfield.TextInputEditText;

public class ResetPasswordActivity extends AppCompatActivity {
    private Validate validate;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        Toolbar toolbar=findViewById(R.id.toolbarResetPass);
        TextInputEditText edtUserForgot =findViewById(R.id.edtUserForgot);
        TextInputEditText edtNewpass =findViewById(R.id.edtNewpass);
        TextInputEditText edtConfirmNewPass=findViewById(R.id.edtConfirmnewpass);
        Button btnResetPass=findViewById(R.id.btnResetPass);


        dbHelper=new DBHelper(this);
        validate=new Validate(this,dbHelper);


        setSupportActionBar(toolbar);
        setTitle("Reset Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        set lại username
        String UserForgot=getIntent().getStringExtra("UsernameForgotten");
        edtUserForgot.setText(UserForgot);

        btnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate.ValdateForgot(edtNewpass.getText().toString(),edtConfirmNewPass.getText().toString(),edtUserForgot.getText().toString())){
                    if (dbHelper.changeNewPass(UserForgot,edtNewpass.getText().toString())){
                        Toast.makeText(ResetPasswordActivity.this, "Password Has Been Changed", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ResetPasswordActivity.this, MainActivity.class));
                        finish();
                    }
                }
            }
        });


    }
}