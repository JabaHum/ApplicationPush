package com.example.applicationpush;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.content.ContentValues.TAG;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public MyFirebaseMessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData() != null ){

            Log.i("Token","Remote Message"+remoteMessage.getNotification());


            //Log.d(" Remote Message: ",remoteMessage.getNotification().getBody());

            //getting the title and the body
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();


            EzyAgricNotificationManager.getInstance(getApplicationContext()).displayNotification(title, body);
            //Log.d("MyNotification", remoteMessage.getNotification().toString());
            //Log.d("MyNotification", remoteMessage.getNotification().toString());


            Toast.makeText(getApplicationContext(),"Remote Message"+remoteMessage.getNotification().getBody(), Toast.LENGTH_SHORT).show();
        }




    }

    @Override
    public void onNewToken(String token) {
        //super.onNewToken(s);

            Log.d("Token", "Refreshed token: " + token);

            // If you want to send messages to this application instance or
            // manage this apps subscriptions on the server side, send the
            // Instance ID token to your app server.
            sendRegistrationToServer(token);

    }

    private  void sendRegistrationToServer (String s){

    }

}
