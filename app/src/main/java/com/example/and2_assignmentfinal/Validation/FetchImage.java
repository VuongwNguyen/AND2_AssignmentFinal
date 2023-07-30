package com.example.and2_assignmentfinal.Validation;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FetchImage extends Thread{
    private ProgressDialog progressDialog;
    private Handler handler = new Handler();
    private ImageView imageViewHint;
    private Context context;


    String URL;
    Bitmap bitmap;


    public FetchImage(ImageView imageViewHint, Context context, String URL) {
        this.imageViewHint = imageViewHint;
        this.context = context;
        this.URL = URL;
    }

    @Override
    public void run() {
        super.run();
//            hiển thị đilog
        handler.post(new Runnable() {
            @Override
            public void run() {
                progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("Downloadingg...");
                progressDialog.setCancelable(false);
                progressDialog.show();
            }
        });
//            lấy ảnh
        InputStream inputStream = null;
        try {
            inputStream = new URL(URL).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
//            đổ ảnh lên widget
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    imageViewHint.setImageBitmap(bitmap);
                }
            }
        });
    }
}
