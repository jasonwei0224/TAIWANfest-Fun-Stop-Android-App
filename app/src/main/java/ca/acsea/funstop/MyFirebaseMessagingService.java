package ca.acsea.funstop;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage.getNotification() != null){
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent2, 0);

            Notification notification = new Notification.Builder(getApplicationContext())
                    .setContentTitle(title)
                    .setContentText(body)
                    .setContentIntent(pendingIntent)
                    .setStyle(new Notification.BigTextStyle().bigText(body))
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notification_icon))
                    .setSmallIcon(R.drawable.notification_icon)
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            notificationManager.notify(1, notification);

            //Intent intent = new Intent("ca.acsea.myapplication_message");
           // intent.putExtra("title", title);
           //intent.putExtra("body", body);
           // LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
            //localBroadcastManager.sendBroadcast(intent);

            Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

           // NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                VibrationEffect effect = VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE);
                v.vibrate(effect);
                NotificationChannel channel = new NotificationChannel(MainActivity.CHANNEL_ID, "ACSEA", NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(channel);
            }
            v.vibrate(new long[] {0,1000}, -1);
            NotificationHelper.displayNotification(getApplicationContext(), title, body);

        }

    }

}