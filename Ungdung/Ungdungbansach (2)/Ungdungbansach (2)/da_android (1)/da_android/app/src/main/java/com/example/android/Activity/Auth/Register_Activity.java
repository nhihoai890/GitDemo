package com.example.android.Activity.Auth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android.Activity.Home_Activity;
import com.example.android.MainActivity;
import com.example.android.R;
import com.google.firebase.auth.FirebaseAuth;

public class Register_Activity extends AppCompatActivity {

    Button btn_register;
    EditText ed_userName, ed_email, ed_pasWord, ed_pasWord_confirm;
    ImageButton id_btn_out_register;
    TextView id_btn_screen_login;
    private FirebaseAuth mAuth;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_regiter);
        mAuth = FirebaseAuth.getInstance();
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
        btn_register.setOnClickListener(v -> registerUser());
        // Xử lý insets để giao diện không bị đè lên các thanh hệ thống
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void registerUser(){
        String userName = ed_userName.getText().toString().trim();
        String email = ed_email.getText().toString().trim();
        String password = ed_pasWord.getText().toString().trim();
        String confirmPassword = ed_pasWord_confirm.getText().toString().trim();
        //Kiểm tra tính hợp lệ của dữ liệu
        if(TextUtils.isEmpty(userName)){
            ed_userName.setError("Vui lòng đăng nhập không được bỏ trống");
            return;
        }

        if(TextUtils.isEmpty(email)){
            ed_email.setError("Vui lòng nhập email");
            return;
        }
        if(TextUtils.isEmpty(password)){
            ed_pasWord.setError("Vui lòng nhập mật khẩu");
            return;
        }
        if(password.length() < 8){
            ed_pasWord.setError("Mật khẩu phải có ít nhất 8 kí tự");
            return;
        }
        if (!password.equals(confirmPassword)) {
            ed_pasWord_confirm.setError("Mật khẩu xác nhận không khớp");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Đăng ký thành công
                        Toast.makeText(Register_Activity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

                        // Chuyển sang màn hình chính
                        Intent intent = new Intent(Register_Activity.this, Home_Activity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Đăng ký thất bại
                        Toast.makeText(Register_Activity.this, "Đăng ký thất bại: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}