package com.example.android.Activity.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android.Activity.Home_Activity;
import com.example.android.MainActivity;
import com.example.android.R;

public class Login_Activity extends AppCompatActivity {
    Button btn_login;
    ImageButton btn_fb, btn_gg, btn_out;
    EditText ed_userName, ed_pasWord;
    TextView txt_forgot_password, txt_screen_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        btn_fb = findViewById(R.id.id_btn_login_fb);
        btn_gg = findViewById(R.id.id_btn_login_gg);
        btn_out = findViewById(R.id.id_btn_out);
        btn_login = findViewById(R.id.id_btn_login_home);
        ed_userName = findViewById(R.id.ed_userName);
        ed_pasWord = findViewById(R.id.ed_pasWord);
        txt_forgot_password = findViewById(R.id.id_btn_forgot_password);
        txt_screen_register = findViewById(R.id.id_btn_screen_register);

        btn_out.setOnClickListener(v -> {
            Intent intent_out = new Intent(Login_Activity.this, MainActivity.class);
            startActivity(intent_out);
        });
        txt_screen_register.setOnClickListener(v -> {
            Intent intent_register = new Intent(Login_Activity.this, Register_Activity.class);
            startActivity(intent_register);
        });
        txt_forgot_password.setOnClickListener(v -> {
            Intent intent_register = new Intent(Login_Activity.this, Enter_Mail_Activity.class);
            startActivity(intent_register);
        });

        btn_login.setOnClickListener(v -> {
            Intent intent_login = new Intent(Login_Activity.this, Home_Activity.class);
            startActivity(intent_login);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}