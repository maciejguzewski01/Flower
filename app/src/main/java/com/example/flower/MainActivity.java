package com.example.flower;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.app.Notification;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import android.app.NotificationChannel;
import androidx.core.app.NotificationManagerCompat;
import android.content.Intent;
import android.app.PendingIntent;


public class MainActivity extends AppCompatActivity {
    /*
    łączenie z bluetoothem
    wysyłanie powiadmienia
     */
    boolean needs_water;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imageView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           // NotificationChannel channel = new NotificationChannel("channel", "name", NotificationManager.IMPORTANCE_MIN);
        }

        //NotificationChannel channel = null;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel("not_chan_1", "channel", importance);
            NotificationManager notificationManager1= getSystemService(NotificationManager.class);
            notificationManager1.createNotificationChannel(channel);
        }




        Intent notificationIntent = new Intent(Intent.ACTION_VIEW);
        notificationIntent.setData(Uri.parse("https://www.youtube.com/watch?v=OTHG8RqPSKE"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,PendingIntent.FLAG_MUTABLE );


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "not_chan_1")
                .setSmallIcon(R.drawable.img)
                .setContentTitle("Podlej mnie!")
                .setContentText("https://www.youtube.com/watch?v=OTHG8RqPSKE")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);;




        /* //działa przełączanie kolorów
            imageView.setImageResource(R.drawable.greensquare);

            imageView.setImageResource(R.drawable.redsquare);
        */

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions

            return;
        }
        /*
        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel("1", "channel", NotificationManager.IMPORTANCE_MIN);
        }

        NotificationManager notificationManager1= getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager1.createNotificationChannel(channel);
        }
*/

        notificationManager.notify(1, builder.build());






    }




}