package ca.acsea.funstop;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        Intent intent2 = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent2, 0);

        Notification notification = new Notification.Builder(context)

                .setContentIntent(pendingIntent)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.notification_icon))
                .setSmallIcon(R.drawable.notification_icon)
                .build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1, notification);

        //Intent intent = new Intent("ca.acsea.myapplication_message");
        // intent.putExtra("title", title);
        //intent.putExtra("body", body);
        // LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        //localBroadcastManager.sendBroadcast(intent);

        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        // NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            VibrationEffect effect = VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE);
            v.vibrate(effect);
            NotificationChannel channel = new NotificationChannel(MainActivity.CHANNEL_ID, "ACSEA", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        v.vibrate(new long[] {0,1000}, -1);
        NotificationHelper.displayNotification(context);

    }

    }

