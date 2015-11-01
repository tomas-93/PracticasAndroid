package com.tomas.main.controller_service;

import android.app.IntentService;
import android.content.Intent;

import com.tomas.main.controller_network.TestHttpUrlConnectionNetwork;

import java.util.Calendar;

/**
 * Created by Tomas on 29/10/2015.
 */
public class Service_HTTPUrlConnection extends IntentService
{
    public Service_HTTPUrlConnection()
    {
        super("Service_HTTPUrlConnection");
    }

    @Override
    public void onHandleIntent(Intent intent)
    {
        TestHttpUrlConnectionNetwork testHttpUrlConnection = new TestHttpUrlConnectionNetwork();
        testHttpUrlConnection.sendMessage(intent.getStringExtra("hora"), intent.getStringExtra("segundos"), intent.getStringExtra("message"));
    }

}
