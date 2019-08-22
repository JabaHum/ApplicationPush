package com.example.applicationpush;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.pushTxtView);

        //function Calling
        enablePushNotification();
    }


    private void enablePushNotification(){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    AlertDialog.Builder mAlertDialogBuilder  = new AlertDialog.Builder(MainActivity.this);
                    mAlertDialogBuilder.setTitle("Subscribe EzyAgric Notifications");
                    mAlertDialogBuilder.setMessage("Are you  Sure you Want to Subscribe ?");
                    mAlertDialogBuilder.setCancelable(true);

                    mAlertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

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


                            //String topic = "yes";
                            FirebaseMessaging.getInstance().subscribeToTopic("topic");


                        }
                    });


                mAlertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        Toast.makeText(getApplicationContext(),"You Clicked on No", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
