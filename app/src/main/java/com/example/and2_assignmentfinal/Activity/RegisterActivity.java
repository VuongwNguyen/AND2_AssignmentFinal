package com.example.and2_assignmentfinal.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.and2_assignmentfinal.Database.DBHelper;
import com.example.and2_assignmentfinal.R;
import com.example.and2_assignmentfinal.Validation.Validate;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    private DBHelper dbHelper = new DBHelper(RegisterActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView txtPresented = findViewById(R.id.txtPresented);
        TextInputEditText edtRUsername = findViewById(R.id.edtRUsername);
        TextInputEditText edtRPass = findViewById(R.id.edtRPass);
        TextInputEditText edtFullname = findViewById(R.id.edtRFullname);
        TextInputEditText edtRConfirmPass = findViewById(R.id.edtRconfirmPass);
        Button btnRegister = findViewById(R.id.btnRegister);

        txtPresented.setTypeface(Typeface.createFromAsset(getAssets(), "font_pesented.ttf"));
        Toolbar toolbar = findViewById(R.id.toolbarRegister);

        setSupportActionBar(toolbar);
        setTitle("Qvuongw");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validate validate = new Validate(RegisterActivity.this,new DBHelper(RegisterActivity.this));
                if (validate.ValidateRegister(edtRUsername.getText().toString(), edtRPass.getText().toString(), edtRConfirmPass.getText().toString(),edtFullname.getText().toString())) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("RUsername", edtRUsername.getText().toString());
                    bundle.putString("RPasswold", edtRPass.getText().toString());
                    intent.putExtras(bundle);
                    setResult(1, intent);
                    dbHelper.register(edtRUsername.getText().toString(),edtRPass.getText().toString(),edtFullname.getText().toString());
                    Toast.makeText(RegisterActivity.this, "Successful Registration !", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // nút back
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}