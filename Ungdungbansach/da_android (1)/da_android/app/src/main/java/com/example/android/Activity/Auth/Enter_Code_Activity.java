package com.example.android.Activity.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android.R;
import com.google.android.material.textfield.TextInputEditText;

public class Enter_Code_Activity extends AppCompatActivity {
    TextInputEditText edt_1, edt_2, edt_3, edt_4; // Sử dụng TextInputEditText
    TextView txt_send_code;
    Button btn_send;
    ImageButton img_btn_out_enter_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enter_code);

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
        txt_send_code.setOnClickListener(v -> {
            // Xử lý khi người dùng nhấn nút "Gửi lại mã"
        });
        btn_send.setOnClickListener(v -> {
            Intent intent_out = new Intent(Enter_Code_Activity.this, Change_Password_Activity.class);
            startActivity(intent_out);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
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
