package com.tomas.main.controller_service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Toast;

import com.tomas.main.R;
import com.tomas.main.controller_view.MainActivity;

/**
 * Created by Tomas on 31/10/2015.
 */
public class Service_ extends Service
{
    private NotificationManager notificationManager;

    @Override
    public void onCreate()
    {
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext());
        builder.setSmallIcon(R.drawable.ic_bug_report_white_48dp)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Prueba de notificacion")
                .setContentText("Hello, word");
        Intent intent1 = new Intent(this.getApplicationContext(), MainActivity.class);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this.getApplicationContext());
        taskStackBuilder.addParentStack(MainActivity.class);
        taskStackBuilder.addNextIntent(intent1);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        this.notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        //this.notificationManager.notify(1, builder.build());
        this.startForeground(1, builder.build());
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Toast.makeText(this.getApplicationContext(), "Hello, word", Toast.LENGTH_SHORT).show();
        return START_REDELIVER_INTENT;
    }
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onDestroy()
    {
        this.notificationManager.cancel(1);
        this.stopForeground(true);
    }
}
