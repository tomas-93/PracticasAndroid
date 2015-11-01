package com.tomas.rastreame.telephony_message;

import android.telephony.SmsManager;

import com.tomas.rastreame.utils.json.JSON_Process;

/**
 * Created by Tomas on 30/10/2015.
 */
public class SendMessage
{
    private String json, number, message;
    private SmsManager smsManager;
    private JSON_Process json_process;

    public SendMessage()
    {
        this.smsManager = SmsManager.getDefault();
        this.json_process = new JSON_Process();
    }

    public void procesSendMessage()
    {
        this.json_process.defragmentJSON(json);
        this.number = this.json_process.getNumber();
        this.message = this.json_process.getMessage();
        this.smsManager.sendTextMessage(this.number, null, this.message, null, null);
    }

    public void setJson(String json)
    {
        this.json = json;
    }
}
