package com.example.applicationpush;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {


    public static final  String TAG = MainActivity.class.getSimpleName();

    //declare textview
    Button btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initialize the textview and give it an id reference from xml
        btn = findViewById(R.id.pushbtn);

        //function Calling
        enablePushNotification();
    }


    private void enablePushNotification(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    AlertDialog.Builder mAlertDialogBuilder  = new AlertDialog.Builder(MainActivity.this);
                    mAlertDialogBuilder.setTitle("Subscribe EzyAgric Notifications");
                    mAlertDialogBuilder.setMessage("Are you  Sure you Want to Subscribe ?");
                    mAlertDialogBuilder.setCancelable(true);

                    mAlertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            //Toast.makeText(getApplicationContext(),"You clicked Yes",Toast.LENGTH_SHORT).show();


  //String topic = "yes";
                            FirebaseMessaging.getInstance().subscribeToTopic("news")
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            String msg = getString(R.string.msg_subscribed);
                                            if (!task.isSuccessful()) {
                                                msg = getString(R.string.msg_subscribe_failed);
                                            }
                                            Log.d(TAG, msg);

                                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                                NotificationManager mNotificationManager =
                                                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                                int importance = NotificationManager.IMPORTANCE_HIGH;
                                                NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, importance);
                                                mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
                                                mChannel.enableLights(true);
                                                mChannel.setLightColor(Color.RED);

                                                //watch this line very well could cause abug.
                                                mNotificationManager.createNotificationChannel(mChannel);
                                            }

                                            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                                        }
                                    });

                            //Log.d("Topic News", "Subscribed to news topic");

                        }
                    });


                mAlertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        Toast.makeText(getApplicationContext(),"You Clicked on No", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = mAlertDialogBuilder.create();
                alertDialog.show();

            }
        });
    }
}
