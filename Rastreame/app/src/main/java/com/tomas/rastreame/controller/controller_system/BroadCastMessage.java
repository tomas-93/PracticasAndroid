package com.tomas.rastreame.controller.controller_system;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.tomas.rastreame.controller.controller_services.ServicesSendMessage;
import com.tomas.rastreame.models.manager_database.SQLite_Manager;
import com.tomas.rastreame.models.objects.Config;
import com.tomas.rastreame.models.objects.Message;

import java.util.Calendar;


/**
 * Created by Tomas on 26/10/2015.
 */
public class BroadCastMessage extends BroadcastReceiver
{
    private SQLite_Manager sqlite_manager;
    private Message message;
    private Config config;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        this.sqlite_manager = new SQLite_Manager(context);
        this.getDataConfig();

        final SmsMessage SMS = this.getMessageReceiver(intent);
        if(SMS != null && this.config.getStatusOperation().equals("Activado"))
        {
            this.sqlite_manager.insertDataMessage(this.message);
        }
        ServicesSendMessage servicesSendMessage = new ServicesSendMessage();
        servicesSendMessage.onService(context);
    }
    private void getDataConfig()
    {
        this.config = this.sqlite_manager.readConfign();
    }
    private void generatingMessageDataTable()
    {
        Calendar calendar = Calendar.getInstance();
        message.setDate(String.valueOf(calendar.get(Calendar.DATE)));
        message.setHour(String.valueOf(calendar.get(Calendar.HOUR)));
        message.setSeconds(String.valueOf(calendar.get(Calendar.SECOND)));

    }
    private SmsMessage getMessageReceiver(Intent intent)
    {
        this.message = new Message();
        Bundle bundle = intent.getExtras();
        SmsMessage messagePhone = null;

        try
        {
            if(bundle != null)
            {
                Object objects [] = (Object[]) bundle.get("pdu");
                StringBuilder stringMessage = new StringBuilder();
                for(int cont = 0; cont < objects.length; cont++)
                {
                    messagePhone = SmsMessage.createFromPdu((byte[]) objects[cont]);
                    stringMessage.append(messagePhone.getMessageBody());
                }
                this.message.setId(this.sqlite_manager.getMaxIdFromTableMesssage());
                this.message.setBody(stringMessage.toString());
                this.message.setNumberMessage(messagePhone.getOriginatingAddress());
                this.generatingMessageDataTable();
            }
        }catch (NullPointerException ex)
        {
            ex.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return messagePhone;
        }
    }





}
