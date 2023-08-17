package com.example.and2_assignmentfinal.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.and2_assignmentfinal.Dao.ProductDAO;
import com.example.and2_assignmentfinal.Fragment.InfoFragment;
import com.example.and2_assignmentfinal.Fragment.ListFragment;
import com.example.and2_assignmentfinal.Model.Product;
import com.example.and2_assignmentfinal.R;
import com.example.and2_assignmentfinal.Fragment.SettingFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainAppActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
//
        NavigationView nav = findViewById(R.id.nav);
        DrawerLayout drawerLayout = findViewById(R.id.drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);

//             hiển thị recyclerView
        ProductDAO productDAO = new ProductDAO(this);
        List<Product> ListPD = productDAO.getListPD();
// hiển thị mặc định
//        ListFragment fragment = new ListFragment(MainAppActivity.this,productDAO,ListPD );
//        FragmentManager fragmentManager=getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frame,fragment).commit();

        //thiết lập toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Wellcome !!");

//        setup DrawerToggle
        ActionBarDrawerToggle Toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open, R.string.close);
//       bật nút hamburger
        Toggle.setDrawerIndicatorEnabled(true);
//      đồng bộ trạng thái toggle và DrawerLayout
        Toggle.syncState();
//        gắn toggle với drawerLayout
        drawerLayout.addDrawerListener(Toggle);
//      bắt sưk kiện click item menu
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mediaPlayer.release();
                if (item.getItemId() == R.id.itLogout) {
                    startActivity(new Intent(MainAppActivity.this, MainActivity.class));
                    finish();
                    Toast.makeText(MainAppActivity.this, "Logout !", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.itSetting) {
                    setTitle("Cài Đặt");
                    fragment = new SettingFragment();
                } else if (item.getItemId() == R.id.itgioithieu) {
                    setTitle("Giới Thiệu");
                    mediaPlayer = MediaPlayer.create(MainAppActivity.this,R.raw.item_track);
                    fragment = new InfoFragment();
                    mediaPlayer.start();
                } else if (item.getItemId() == R.id.itQLSP) {
                    setTitle("Quản Lý Sản Phẩm");
                    mediaPlayer = MediaPlayer.create(MainAppActivity.this,R.raw.info_track);
                    fragment = new ListFragment(MainAppActivity.this, productDAO, ListPD);
                    mediaPlayer.start();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
                drawerLayout.close();

                return true;
            }
        });
        mediaPlayer = MediaPlayer.create(this,R.raw.sound_tracks);
        mediaPlayer.start();
    }
    private MediaPlayer mediaPlayer;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}












