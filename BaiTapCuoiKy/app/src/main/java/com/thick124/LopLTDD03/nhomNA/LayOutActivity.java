package com.thick124.LopLTDD03.nhomNA;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LayOutActivity extends AppCompatActivity {
    Button btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_layout);

        btn_home = findViewById(R.id.id_btn_home);

        // Sự kiện khi click vào nút home
        btn_home.setOnClickListener(v -> {
            Intent intent_home = new Intent(LayOutActivity.this, MainActivity.class);
            startActivity(intent_home);
        });

        // Xử lý Insets để bố cục vừa với màn hình
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
