package com.example.android.Activity.Auth;

import android.annotation.SuppressLint;
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

import com.example.android.MainActivity;
import com.example.android.R;

public class Register_Activity extends AppCompatActivity {

    Button btn_register;
    EditText ed_userName, ed_email, ed_pasWord, ed_pasWord_confirm;
    ImageButton id_btn_out_register;
    TextView id_btn_screen_login;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_regiter);
        btn_register = findViewById(R.id.id_btn_register_home);
        ed_userName = findViewById(R.id.ed_userName);
        ed_email = findViewById(R.id.ed_email);
        ed_pasWord = findViewById(R.id.ed_pasWord);
        ed_pasWord_confirm = findViewById(R.id.ed_pasWord_confirm);
        id_btn_out_register = findViewById(R.id.id_btn_out_register);
        id_btn_screen_login = findViewById(R.id.id_btn_screen_login);
        id_btn_out_register.setOnClickListener(v -> {
            Intent intent_out = new Intent(Register_Activity.this, MainActivity.class);
            startActivity(intent_out);
        });
        id_btn_screen_login.setOnClickListener(v -> {
            Intent intent_out = new Intent(Register_Activity.this, Login_Activity.class);
            startActivity(intent_out);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}