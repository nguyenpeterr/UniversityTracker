package com.example.nguyenpeter_c196.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.nguyenpeter_c196.R;

public class ReceiverClass extends BroadcastReceiver {
    final String chanID = "scheduler_channel";
    static int notificationID;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,intent.getStringExtra("key"),Toast.LENGTH_LONG).show();
        createNotification(context, chanID);
        Notification notification = new NotificationCompat.Builder(context, chanID)
                .setSmallIcon(R.drawable.ic_calendar)
                .setContentText(intent.getStringExtra("key"))
                .setContentTitle("Reminder")
                .build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID++, notification);
    }

    private void createNotification(Context context, String chanID) {
        CharSequence name = context.getResources().getString(R.string.channelname);
        String description = context.getString(R.string.channeldesc);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(chanID, name, importance);
        channel.setDescription(description);
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}
