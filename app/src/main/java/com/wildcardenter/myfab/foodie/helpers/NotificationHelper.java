package com.wildcardenter.myfab.foodie.helpers;

/*
    Class On Package com.wildcardenter.myfab.pgecattaindencesystem.helpers
    
    Created by Asif Mondal on 07-07-2019 at 17:27
*/


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.wildcardenter.myfab.foodie.R;


public class NotificationHelper {

    private Context mContext;
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    Bitmap icon;

    public NotificationHelper(Context context) {
        mContext = context;
        getManager();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels();
        }
        icon=BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher_round);
    }

    /**
     * Create and push the notification
     */
    public void createNotification(String title, String message) {
        /**Creates an explicit intent for an Activity in your app**/
            /*Intent resultIntent = new Intent(mContext , SomeOtherActivity.class);
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            PendingIntent resultPendingIntent = PendingIntent.getActivity(mContext,
                    0 *//* Request code *//*, resultIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);*/
        NotificationCompat.BigTextStyle bigTextStyle=new NotificationCompat.BigTextStyle()
                .bigText(message)
                .setBigContentTitle(title)
                .setSummaryText(title.split(":")[1]);
        NotificationCompat.BigPictureStyle bigPictureStyle=new NotificationCompat.BigPictureStyle()
                .bigPicture(BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.cheese))
                .setSummaryText("Juicy Chills")
                .setBigContentTitle(message)
                .bigLargeIcon(BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.ic_place_black_24dp));

        mBuilder = new NotificationCompat.Builder(mContext, NOTIFICATION_CHANNEL_ID);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setAutoCancel(false)
                .setSmallIcon(R.drawable.ic_place_black_24dp)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
                .setColorized(true)
                .setColor(mContext.getResources().getColor(R.color.colorAccent))
                .setLargeIcon(icon)
                .setStyle(bigPictureStyle)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);

        mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);

        /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

            mNotificationManager.createNotificationChannel(notificationChannel);
        }*/
        mNotificationManager.notify(0 , mBuilder.build());
    }

    private void createChannels() {

        NotificationChannel notificationChannel = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "FOOD_APP_NOTIFICATION", NotificationManager.IMPORTANCE_HIGH);


            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            getManager().createNotificationChannel(notificationChannel);
        }

    }

    private NotificationManager getManager() {
        if (mNotificationManager == null) {
            mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mNotificationManager;
    }
}
