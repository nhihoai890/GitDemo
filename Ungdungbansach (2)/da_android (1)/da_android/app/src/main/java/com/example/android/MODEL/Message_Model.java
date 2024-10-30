package com.example.android.MODEL;

public class Message_Model {
        private String message;
        private boolean isSentByMe; // True nếu tin nhắn được gửi bởi người dùng

        public Message_Model(String message, boolean isSentByMe) {
            this.message = message;
            this.isSentByMe = isSentByMe;
        }

        public String getMessage() {
            return message;
        }

        public boolean isSentByMe() {
            return isSentByMe;
        }
    }

