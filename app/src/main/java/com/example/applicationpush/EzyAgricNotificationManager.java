package com.example.applicationpush;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class EzyAgricNotificationManager {

    private Context mCtx;

    private static EzyAgricNotificationManager mInstance;


    private  EzyAgricNotificationManager(Context context){
        mCtx = context;

    }

    public static synchronized EzyAgricNotificationManager getInstance(Context context){
        if (mInstance == null){

            //context = context.getApplicationContext();
            mInstance = new EzyAgricNotificationManager(context);
        }
        return mInstance;

    }

    public  void displayNotification(String title ,String message){

        Intent resultIntent  =  new Intent(mCtx,PushDetailsActivity.class);

        // Set the Activity to start in a new, empty task
        //resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            stackBuilder = TaskStackBuilder.create(mCtx);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            stackBuilder.addNextIntentWithParentStack(resultIntent);
        }

        // Get the PendingIntent containing the entire back stack
        PendingIntent pendingIntent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        }


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mCtx,Constants.CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.ic_block_black_24dp);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(message);
        // Set the intent that will fire when the user taps the notification
        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        mBuilder.setAutoCancel(true);

        NotificationManager manager = (NotificationManager)mCtx.getSystemService(Context.NOTIFICATION_SERVICE);


        if (manager != null){
            manager.notify(0,mBuilder.build());
        }

    }

}
