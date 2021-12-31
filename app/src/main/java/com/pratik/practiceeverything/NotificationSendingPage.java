package com.pratik.practiceeverything;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NotificationSendingPage extends AppCompatActivity {
    EditText title, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_sending_page);

        title = findViewById(R.id.title);
        message = findViewById(R.id.message);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("Channel 1", "This is channel 1", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getSystemService(NotificationManager.class);
            notificationChannel.enableVibration(true);
            notificationChannel.enableLights(true);
            notificationChannel.setDescription("This is description for channel 1");
            manager.createNotificationChannel(notificationChannel);
        }
    }

    public void sendNotification(View view) {

        Intent intent = new Intent(this, SnapChatUI.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Channel 1");
        builder.setContentTitle(title.getText().toString())
                .setContentText(message.getText().toString())
                .setSmallIcon(R.drawable.sclogo)
                .setPriority(Notification.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("This is the extra text from this notification......."))
                .setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1, builder.build());

    }
}