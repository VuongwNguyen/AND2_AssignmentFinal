package com.example.and2_assignmentfinal.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.and2_assignmentfinal.Database.DBHelper;
import com.example.and2_assignmentfinal.R;
import com.example.and2_assignmentfinal.Validation.Validate;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private String rUsername, rPass;
    private TextInputEditText edtLUsername, edtLPassword;
    private String OTP;
    private TextView tvOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtLogRegister = findViewById(R.id.txtLogRegister);
        TextView txtforgot = findViewById(R.id.txtLogForgot);
        TextView txtPresented = findViewById(R.id.txtPresented);
        Button btnLogin = findViewById(R.id.btnLogin);
        edtLUsername = findViewById(R.id.edtLUsername);
        edtLPassword = findViewById(R.id.edtLPassword);

        txtLogRegister.setText(Html.fromHtml("Don't Have An Account? <b>SignUp</b>"));
        txtforgot.setText(Html.fromHtml("<u>Forgot Password</u>"));
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font_pesented.ttf");
        txtPresented.setTypeface(typeface);


        txtLogRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcher.launch(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
        txtforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = getLayoutInflater();

                View viewDialog = inflater.inflate(R.layout.fragment_forgot_password, null);
//
                Button btnSearchUsername = viewDialog.findViewById(R.id.btnSearchUsername);
                TextInputEditText edtUsernameForgotten = viewDialog.findViewById(R.id.edtUsenameForgotten);
                TextInputEditText edtOTP = viewDialog.findViewById(R.id.edtOtp);
                tvOtp = viewDialog.findViewById(R.id.tvOTP);
                ImageView ivReplay = viewDialog.findViewById(R.id.ivReplay);

                random();
                ivReplay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        random();
                    }
                });

                builder.setView(viewDialog);
                AlertDialog dialog = builder.create();

                DBHelper dbHelper = new DBHelper(MainActivity.this);
                btnSearchUsername.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (edtUsernameForgotten.getText().toString().isEmpty() || edtOTP.getText().toString().isEmpty()) {
                            Toast.makeText(MainActivity.this, "Please Fill All Field!", Toast.LENGTH_SHORT).show();
                        } else if (!edtOTP.getText().toString().equalsIgnoreCase(OTP)) {
                            Toast.makeText(MainActivity.this, "Error OTP", Toast.LENGTH_SHORT).show();
                        } else if (dbHelper.checkUsername(edtUsernameForgotten.getText().toString()) && edtOTP.getText().toString().equalsIgnoreCase(OTP)) {
                            Intent intent = new Intent(MainActivity.this, ResetPasswordActivity.class);
                            intent.putExtra("UsernameForgotten", edtUsernameForgotten.getText().toString());
                            Toast.makeText(MainActivity.this, "Proceed To Change The Password For " + edtUsernameForgotten.getText().toString(), Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(MainActivity.this, "Account Doesn't Exist!", Toast.LENGTH_SHORT).show();
                        }
                        random();
                    }
                });
                dialog.show();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validate validate = new Validate(MainActivity.this, new DBHelper(MainActivity.this));
                if (validate.ValidateLogin(edtLUsername.getText().toString(), edtLPassword.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Successful Login !", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, MainAppActivity.class));
                    finish();
                }
            }
        });

    }

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    private String generateRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }

    private void random() {
        OTP = generateRandomString(6);
        tvOtp.setText(OTP);
    }

    private ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == 1) {
                Intent intent = result.getData();
                Bundle bundle = intent.getExtras();
                rUsername = bundle.getString("RUsername");
                rPass = bundle.getString("RPasswold");
                edtLUsername.setText(rUsername);
                edtLPassword.setText(rPass);
            }
        }
    });
}