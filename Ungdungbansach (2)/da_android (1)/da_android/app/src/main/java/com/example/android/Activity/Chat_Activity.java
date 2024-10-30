package com.example.android.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.Adapter.Chat_Adapter;
import com.example.android.MODEL.Message_Model;
import com.example.android.R;

import java.util.ArrayList;

public class Chat_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Chat_Adapter chatAdapter;
    private ArrayList<Message_Model> messageList;
    private EditText editTextMessage;
    private ImageButton buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = findViewById(R.id.recyclerViewChat);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);

        // Dữ liệu tin nhắn
        messageList = new ArrayList<>();
        chatAdapter = new Chat_Adapter(messageList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chatAdapter);

        // Nút gửi tin nhắn
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = editTextMessage.getText().toString().trim();
                if (!TextUtils.isEmpty(messageText)) {
                    // Thêm tin nhắn vào danh sách và làm mới RecyclerView
                    messageList.add(new Message_Model(messageText, true));
                    chatAdapter.notifyDataSetChanged();
                    recyclerView.smoothScrollToPosition(messageList.size() - 1); // Cuộn xuống cuối

                    // Xóa nội dung nhập tin nhắn
                    editTextMessage.setText("");

                    // Thêm tin nhắn từ người khác (Giả lập)
                    messageList.add(new Message_Model("Tin nhắn trả lời từ người khác!", false));
                    chatAdapter.notifyDataSetChanged();
                    recyclerView.smoothScrollToPosition(messageList.size() - 1);
                }
            }
        });
    }
}
