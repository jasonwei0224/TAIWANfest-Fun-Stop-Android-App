package ca.acsea.funstop;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Vibrator;
import androidx.core.app.NotificationCompat;

class NotificationHelper {
    private Vibrator vibrator;
    static void displayNotification(Context context, String title, String  body){

        Intent intent = new Intent(context, Profile.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                103,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT
        );



        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, MainActivity.CHANNEL_ID);
        notificationBuilder.setSmallIcon(R.drawable.notification_icon);

        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(body);
        notificationBuilder.setContentIntent(pendingIntent);
        notificationBuilder.setLights(Color.RED, 3000, 3000);
        notificationBuilder.setVibrate(new long[]{0, 1000});
        notificationBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);

    }

}