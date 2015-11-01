package com.tomas.main.controller_view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.os.Handler;

import com.tomas.main.R;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Tomas on 01/11/2015.
 */
public class TestTimerTask extends Fragment
{
    /*
        Tabs start
     */
    private static final String ARGS_SELECTION_NUMBER ="selection_number";
    public static Fragment newInstace(int selectNumber)
    {
        TestTimerTask testTimerTask = new TestTimerTask();
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_SELECTION_NUMBER, selectNumber);
        testTimerTask.setArguments(bundle);
        return testTimerTask;
    }
    /*
            tasb end
     */
    private TextView code, timer;
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle saveInstace)
    {
        View rootView = inflater.inflate(R.layout.fragment_timertask, viewGroup, false);
        this.timer = (TextView) rootView.findViewById(R.id.timerTaskXML);
        this.code = (TextView) rootView.findViewById(R.id.taskCodeXML);
        TimerTask timerTask;
        Handler handler = new Handler();
        try
        {
            handler.post(timerTask = new TimerTask() {
                @Override
                public void run() {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final Calendar calendar = Calendar.getInstance();
                            final int hour = calendar.get(Calendar.HOUR);
                            final int minute = calendar.get(Calendar.MINUTE);
                            final int secods = calendar.get(Calendar.SECOND);
                            final String hora = hour + ":" + minute + ":" + secods;
                            timer.setText(hora, TextView.BufferType.NORMAL);
                        }
                    });
                }
            });
            Timer timer1 = new Timer("Tareitas");
            timer1.schedule(timerTask, 0, 1000);
        }catch (NullPointerException e)
        { }
        final String cadena = "TimerTask timerTask;\n" +
                "        Handler handler = new Handler();\n" +
                "        handler.post(timerTask = new TimerTask()\n" +
                "        {\n" +
                "            @Override\n" +
                "            public void run()\n" +
                "            {\n" +
                "                getActivity().runOnUiThread(new Runnable()\n" +
                "                {\n" +
                "                    @Override\n" +
                "                    public void run() {\n" +
                "                        final Calendar calendar = Calendar.getInstance();\n" +
                "                        final int hour = calendar.get(Calendar.HOUR);\n" +
                "                        final int minute = calendar.get(Calendar.MINUTE);\n" +
                "                        final int secods = calendar.get(Calendar.SECOND);\n" +
                "                        final String hora = hour + \":\" + minute +\":\"+secods;\n" +
                "                        timer.setText(hora, TextView.BufferType.NORMAL);\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "        Timer timer1 = new Timer(\"Tareitas\");\n" +
                "        timer1.schedule(timerTask,0,1000);";
        this.code.setText(cadena, TextView.BufferType.NORMAL);

        return rootView;
    }

}
