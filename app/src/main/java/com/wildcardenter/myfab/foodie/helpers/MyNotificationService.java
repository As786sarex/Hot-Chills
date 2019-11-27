package com.wildcardenter.myfab.foodie.helpers;

/*
                                #  #           #  #     
    Created by Asif Mondal on 25-11-2019 at 16:37
*/


import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyNotificationService extends FirebaseMessagingService {

    NotificationHelper helper;

    @Override
    public void onCreate() {
        super.onCreate();
        helper=new NotificationHelper(this);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        String title=remoteMessage.getData().get("title");
        String body=remoteMessage.getData().get("body");
        helper.createNotification(title,body);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        helper=null;
    }
}
