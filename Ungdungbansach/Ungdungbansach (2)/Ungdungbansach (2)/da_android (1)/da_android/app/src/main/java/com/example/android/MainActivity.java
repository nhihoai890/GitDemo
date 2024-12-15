package com.example.android;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android.Activity.Auth.Login_Activity;
import com.example.android.Activity.Auth.Register_Activity;

public class MainActivity extends AppCompatActivity {
    Button btn_login, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btn_login = findViewById(R.id.id_btn_login);
        btn_register = findViewById(R.id.id_btn_register);
        btn_login.setOnClickListener(v -> {
            Intent intent_login = new Intent(MainActivity.this, Login_Activity.class);
            startActivity(intent_login);
        });
        btn_register.setOnClickListener(v -> {
            Intent intent_register = new Intent(MainActivity.this, Register_Activity.class);
            startActivity(intent_register);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }


}