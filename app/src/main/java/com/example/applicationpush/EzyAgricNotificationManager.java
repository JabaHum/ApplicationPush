package com.example.applicationpush;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class EzyAgricNotificationManager {

    private Context mCtx;

    private static EzyAgricNotificationManager mInstance;


    private  EzyAgricNotificationManager(Context context){
        mCtx = context;

    }

    public static synchronized EzyAgricNotificationManager getInstance(Context context){
        if (mInstance == null){
            mInstance = new EzyAgricNotificationManager(context);

        }
        return mInstance;

    }

    public  void displayNotification(String title ,String message){


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mCtx,Constants.CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.ic_block_black_24dp);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(message);

        Intent resultIntent  =  new Intent(mCtx,PushDetailsActivity.class);
        // Set the Activity to start in a new, empty task
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(mCtx,0,resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager)mCtx.getSystemService(Context.NOTIFICATION_SERVICE);


        if (manager != null){
            manager.notify(0,mBuilder.build());
        }



    }

}
