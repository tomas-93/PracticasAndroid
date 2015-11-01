package com.tomas.main.controller_view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.tomas.main.R;
import com.tomas.main.controller_service.Service_;

import java.util.Calendar;

/**
 * Created by Tomas on 30/10/2015.
 */
public class TestNotificacionPersisten extends Fragment implements OnClickListener
{
    /*
        Start tabs
     */
    private static final String ARGS_SELECTION_NUMBER = "selection_number";
    public static TestNotificacionPersisten newInstace(int numberSelection)
    {
        TestNotificacionPersisten testTime = new TestNotificacionPersisten();
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_SELECTION_NUMBER,numberSelection);
        testTime.setArguments(bundle);
        return testTime;
    }
    /*
        End tabs
     */
    private TextView code;
    private CheckBox checkBox;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle)
    {
        View rootView = inflater.inflate(R.layout.fragment_notify, viewGroup, false);
        this.code = (TextView) rootView.findViewById(R.id.codeXML);
        this.checkBox = (CheckBox) rootView.findViewById(R.id.processXML);
        this.checkBox.setOnClickListener(this);
        final String  codeS = "Notification.Builder builder = new Notification.Builder(this.getApplicationContext());\n" +
                "        builder.setSmallIcon(R.drawable.ic_bug_report_black_48dp)\n" +
                "                .setWhen(System.currentTimeMillis())\n" +
                "                .setContentTitle(\"Prueba de notificacion\")\n" +
                "                .setContentText(\"Hello, word\");\n" +
                "        Intent intent1 = new Intent(this.getApplicationContext(), MainActivity.class);\n" +
                "        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this.getApplicationContext());\n" +
                "        taskStackBuilder.addParentStack(MainActivity.class);\n" +
                "        taskStackBuilder.addNextIntent(intent1);\n" +
                "        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);\n" +
                "        builder.setContentIntent(pendingIntent);\n" +
                "        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);\n" +
                "        notificationManager.notify(1, builder.build());\n" +
                "        this.startForeground(1, builder.build());\n\n" +
                "        Destroy()\n" +
                "        this.stopForeground(true);";
        this.code.setText(codeS, TextView.BufferType.NORMAL);
        return rootView;
    }
    @Override
    public void onClick(View view)
    {
        if(this.checkBox.isChecked())
        {
            this.getActivity().startService(new Intent(this.getActivity().getApplicationContext(), Service_.class));
        }else this.getActivity().stopService(new Intent(this.getActivity().getApplicationContext(), Service_.class));
    }
}
