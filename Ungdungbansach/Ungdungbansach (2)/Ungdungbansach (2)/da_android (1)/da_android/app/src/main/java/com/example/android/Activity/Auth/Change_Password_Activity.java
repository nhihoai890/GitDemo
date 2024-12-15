package com.example.android.Activity.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Change_Password_Activity extends AppCompatActivity {
    EditText edt_pasWord_new, edt_pasWord_confirm_1;
    Button btn_change_password;
    ImageButton id_btn_out_change_password;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chage_password);

        // Khởi tạo Firebase Auth instance
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        // Khởi tạo các view
        edt_pasWord_new = findViewById(R.id.ed_pasWord_new);
        edt_pasWord_confirm_1 = findViewById(R.id.ed_pasWord_confirm_1);
        btn_change_password = findViewById(R.id.id_btn_change_password);
        id_btn_out_change_password = findViewById(R.id.id_btn_out_change_password);

        // Quay lại màn hình Login khi nhấn vào nút quay lại
        id_btn_out_change_password.setOnClickListener(v -> {
            Intent intent_out = new Intent(Change_Password_Activity.this, Login_Activity.class);
            startActivity(intent_out);
        });

        // Xử lý thay đổi mật khẩu khi người dùng nhấn nút "Đổi mật khẩu"
        btn_change_password.setOnClickListener(v -> {
            String newPassword = edt_pasWord_new.getText().toString().trim();
            String confirmPassword = edt_pasWord_confirm_1.getText().toString().trim();

            if (TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(confirmPassword)) {
                Toast.makeText(Change_Password_Activity.this, "Vui lòng nhập đầy đủ mật khẩu", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                Toast.makeText(Change_Password_Activity.this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            // Nếu mật khẩu hợp lệ, thực hiện thay đổi mật khẩu
            changePassword(newPassword);
        });

        // Xử lý các hệ thống insets cho giao diện
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Hàm thay đổi mật khẩu trong Firebase
    private void changePassword(String newPassword) {
        if (currentUser != null) {
            currentUser.updatePassword(newPassword)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Thay đổi mật khẩu thành công
                            Toast.makeText(Change_Password_Activity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            // Chuyển người dùng trở lại màn hình đăng nhập
                            Intent intent_out = new Intent(Change_Password_Activity.this, Login_Activity.class);
                            startActivity(intent_out);
                            finish();
                        } else {
                            // Thất bại khi thay đổi mật khẩu
                            Toast.makeText(Change_Password_Activity.this, "Không thể thay đổi mật khẩu", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}