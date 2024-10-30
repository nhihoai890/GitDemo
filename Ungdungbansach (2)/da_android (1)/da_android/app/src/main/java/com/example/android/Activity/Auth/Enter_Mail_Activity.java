package com.example.android.Activity.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android.MainActivity;
import com.example.android.R;

public class Enter_Mail_Activity extends AppCompatActivity {
    ImageButton btn_out;
    EditText ed_userName;
    Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enter_mail);
        btn_out = findViewById(R.id.id_btn_out_enter_mail);
        btn_send = findViewById(R.id.id_btn_send_enter_mail);
        ed_userName = findViewById(R.id.ed_userName);
        btn_out.setOnClickListener(v -> {
            Intent intent_out = new Intent(Enter_Mail_Activity.this, Login_Activity.class);
            startActivity(intent_out);
        });
        btn_send.setOnClickListener(v -> {
            Intent intent_send = new Intent(Enter_Mail_Activity.this, Enter_Code_Activity.class);
            startActivity(intent_send);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}