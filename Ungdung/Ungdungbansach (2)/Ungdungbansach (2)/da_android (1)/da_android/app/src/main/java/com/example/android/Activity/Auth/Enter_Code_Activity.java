package com.example.android.Activity.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Enter_Code_Activity extends AppCompatActivity {
    TextInputEditText edt_1, edt_2, edt_3, edt_4; // Sử dụng TextInputEditText
    TextView txt_send_code;
    Button btn_send;
    ImageButton img_btn_out_enter_mail;
    String email;  // Email từ màn hình trước
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enter_code);

        mAuth = FirebaseAuth.getInstance();

        // Nhận email từ Intent
        email = getIntent().getStringExtra("email");
        edt_1 = findViewById(R.id.ed_code_1);
        edt_2 = findViewById(R.id.ed_code_2);
        edt_3 = findViewById(R.id.ed_code_3);
        edt_4 = findViewById(R.id.ed_code_4);

        edt_1.addTextChangedListener(new InputWatcher(edt_2));
        edt_2.addTextChangedListener(new InputWatcher(edt_3));
        edt_3.addTextChangedListener(new InputWatcher(edt_4));

        txt_send_code = findViewById(R.id.id_btn_resend_code);
        btn_send = findViewById(R.id.id_btn_send_enter_code);
        img_btn_out_enter_mail = findViewById(R.id.id_btn_out_enter_code);

        img_btn_out_enter_mail.setOnClickListener(v -> {
            Intent intent_out = new Intent(Enter_Code_Activity.this, Login_Activity.class);
            startActivity(intent_out);
        });

        // Xử lý gửi lại mã xác minh
        txt_send_code.setOnClickListener(v -> {
            resendVerificationEmail();
        });

        // Xử lý khi nhấn nút gửi mã xác minh
        btn_send.setOnClickListener(v -> {
            verifyEmail();
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void verifyEmail() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.reload().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    if (user.isEmailVerified()) {
                        Toast.makeText(this, "Xác minh email thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, Change_Password_Activity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Email chưa được xác minh. Vui lòng kiểm tra hộp thư và xác minh email.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Không thể kiểm tra trạng thái xác minh: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Không tìm thấy người dùng!", Toast.LENGTH_SHORT).show();
        }
    }

    private void resendVerificationEmail() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.sendEmailVerification().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Email xác minh đã được gửi lại!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Gửi lại email thất bại: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    // Lớp InputWatcher ngoài onCreate
    private class InputWatcher implements TextWatcher {
        private final TextInputEditText nextEditText;

        public InputWatcher(TextInputEditText nextEditText) {
            this.nextEditText = nextEditText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 1) { // Nếu có một ký tự được nhập
                nextEditText.requestFocus(); // Chuyển đến ô nhập tiếp theo
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }
}