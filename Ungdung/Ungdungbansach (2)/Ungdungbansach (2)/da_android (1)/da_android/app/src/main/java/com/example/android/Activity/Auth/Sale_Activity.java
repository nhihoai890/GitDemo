package com.example.android.Activity.Auth;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.R;

public class Sale_Activity extends AppCompatActivity {
    // Các biến cho TextView
    private TextView tvTitle, tvRank, tvCoins, tvCoinCount, tvProgressMessage;

    // Biến cho ImageView
    private ImageView imgIcon;

    // Biến cho ProgressBar
    private ProgressBar progressBar;

    // Các biến cho Button
    private Button btnDetails, btnUse, btnVoucher, btnOrder, btnGuide, btnSettings;

    // Biến cho ScrollView và Layouts (nếu cần xử lý thêm)
    private ScrollView scrollView;
    private LinearLayout mainLayout, progressLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);
        // Ánh xạ các TextView
        tvTitle = findViewById(R.id.tvTitle);
        tvRank = findViewById(R.id.tvRank);
        tvCoins = findViewById(R.id.tvCoins);
        tvCoinCount = findViewById(R.id.tvCoinCount);
        tvProgressMessage = findViewById(R.id.tvProgressMessage);

        // Ánh xạ ImageView
        imgIcon = findViewById(R.id.imgIcon);

        // Ánh xạ ProgressBar
        progressBar = findViewById(R.id.progressBar);

        // Ánh xạ các Button
        btnDetails = findViewById(R.id.btnDetails);
        btnUse = findViewById(R.id.btnUse);
        btnVoucher = findViewById(R.id.btnVoucher);
        btnOrder = findViewById(R.id.btnOrder);
        btnGuide = findViewById(R.id.btnGuide);
        btnSettings = findViewById(R.id.btnSettings);

        // Ánh xạ ScrollView và Layout (nếu cần xử lý thêm)
        scrollView = findViewById(R.id.scrollView);
        mainLayout = findViewById(R.id.mainLayout);
        progressLayout = findViewById(R.id.progressLayout);

        // Thiết lập sự kiện cho các Button (nếu cần)
        btnDetails.setOnClickListener(v -> {
            // Xử lý khi nhấn vào nút "Chi tiết"
        });

        btnUse.setOnClickListener(v -> {
            // Xử lý khi nhấn vào nút "Sử dụng"
        });

        btnVoucher.setOnClickListener(v -> {
            // Xử lý khi nhấn vào nút "Voucher và quà tặng"
        });

        btnOrder.setOnClickListener(v -> {
            // Xử lý khi nhấn vào nút "Đơn hàng của tôi"
        });

        btnGuide.setOnClickListener(v -> {
            // Xử lý khi nhấn vào nút "Hướng dẫn sử dụng"
        });

        btnSettings.setOnClickListener(v -> {
            // Xử lý khi nhấn vào nút "Cài đặt tài khoản"
        });
    }
}
