package cn.edu.uoh.learnandroid.ui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import cn.edu.uoh.learnandroid.R;

public class NotificationDemoActivity extends AppCompatActivity {
    static final String CHANNEL_ID = "demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_demo);
        setTitle("通知Demo");
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "channel name", importance);
            channel.setDescription("channel description");
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void showNotification(View view) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.sunny)
                .setContentTitle("天气情况")
                .setContentText("晴，20-30°，微风")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        showNotification(1, builder.build());
    }

    public void showNotificationWithTap(View view) {
        Intent intent = new Intent(this, RecyclerViewActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.cloudy5)
                .setContentTitle("天气情况")
                .setContentText("阴，22-35°，无风")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        showNotification(2, builder.build());
    }

    public void showNotificationWithBtn(View view) {
        Intent intent = new Intent(this, RecyclerViewActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.cloudy5)
                .setContentTitle("天气情况")
                .setContentText("阴，22-35°，无风")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(R.drawable.cloudy5, "按钮1", pendingIntent)
                .setAutoCancel(true);
        showNotification(2, builder.build());
    }

    private void showNotification(int notificationId, Notification notification) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId, notification);
    }
}
