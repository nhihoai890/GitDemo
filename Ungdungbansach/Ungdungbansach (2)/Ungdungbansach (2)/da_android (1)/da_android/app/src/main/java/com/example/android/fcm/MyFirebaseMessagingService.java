package com.example.android.fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.android.MainActivity;
import com.example.android.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Kiểm tra xem tin nhắn có chứa dữ liệu không
        if (remoteMessage.getData().size() > 0) {
            // Lấy dữ liệu từ thông báo
            String someData = remoteMessage.getData().get("key_name");  // Ví dụ key "key_name" là dữ liệu bạn gửi
            // Xử lý dữ liệu ở đây
            handleData(someData);
        }

        // Kiểm tra xem tin nhắn có chứa thông báo không
        if (remoteMessage.getNotification() != null) {
            // Hiển thị thông báo
            showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }
    }

    private void handleData(String data) {
        // Xử lý dữ liệu nhận được từ FCM
        // Ví dụ: Bạn có thể sử dụng dữ liệu này để hiển thị hoặc cập nhật giao diện người dùng.
        if (data != null) {
            // Ví dụ: Gửi dữ liệu vào một Activity
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("data_key", data);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    private void showNotification(String title, String body) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "default")
                .setSmallIcon(R.drawable.ic_notification)  // Icon thông báo
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        // Gửi token lên server của bạn hoặc lưu trữ để gửi thông báo
    }
    public void onCreate(){
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "Default Channel";
            String description = "Channel for general notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("default", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
