package com.heartsun.informer.firebase_push_notification;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.legacy.content.WakefulBroadcastReceiver;


import com.heartsun.informer.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class FirebaseDataReceiver extends WakefulBroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("BroadcastReceiver::", "BroadcastReceiver");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(intent.getExtras().getString("title"))
                .setContentText(intent.getExtras().getString("message"));
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
//        notificationManager.notify(0, builder.build());

        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        assert manager != null;
        manager.notify(0, builder.build());

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
//        {
//            NotificationChannel channel = new NotificationChannel("Default", "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
//            if (notificationManager != null) {
//                notificationManager.createNotificationChannel(channel);
//            }
//        }
//        if (notificationManager != null) {
//            notificationManager.notify(0,builder.build());
//        }


    }
}
