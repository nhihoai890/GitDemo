package com.example.android.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.android.Fragment.BookMart_Fragment;
import com.example.android.Fragment.Cart_Fragment;
import com.example.android.Fragment.Home_Fragment;
import com.example.android.Fragment.Setting_Fragment;
import com.example.android.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home_Activity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        frameLayout = findViewById(R.id.frameLayout);

        // Set màn hình Home mặc định khi khởi động ứng dụng
        setFragment(new Home_Fragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_home) {
                    setFragment(new Home_Fragment());
                } else if (itemId == R.id.menu_like) {
                    setFragment(new BookMart_Fragment());
                } else if (itemId == R.id.menu_cart) {
                    setFragment(new Cart_Fragment());
                } else {
                    setFragment(new Setting_Fragment());
                }
                return true;
            }
        });
    }

    // Phương thức thay thế fragment
    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);  // Thay thế fragment hiện tại
        fragmentTransaction.commit();
    }
}
