package com.example.android.Activity.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android.R;

public class Change_Password_Activity extends AppCompatActivity {
    EditText edt_pasWord_new, edt_pasWord_confirm_1;
    Button btn_change_password;
    ImageButton id_btn_out_change_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chage_password);
        edt_pasWord_new = findViewById(R.id.ed_pasWord_new);
        edt_pasWord_confirm_1 = findViewById(R.id.ed_pasWord_confirm_1);
        btn_change_password = findViewById(R.id.id_btn_change_password);
        id_btn_out_change_password = findViewById(R.id.id_btn_out_change_password);

        id_btn_out_change_password.setOnClickListener(v -> {
            Intent intent_out = new Intent(Change_Password_Activity.this, Login_Activity.class);
            startActivity(intent_out);
        });

        btn_change_password.setOnClickListener(v -> {
            Intent intent_out = new Intent(Change_Password_Activity.this, Login_Activity.class);
            startActivity(intent_out);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}