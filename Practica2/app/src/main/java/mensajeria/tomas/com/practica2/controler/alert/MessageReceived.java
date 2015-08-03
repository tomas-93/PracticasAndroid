package mensajeria.tomas.com.practica2.controler.alert;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.database.Cursor;
import android.widget.TextView;


import mensajeria.tomas.com.practica2.models.object.Config;
import mensajeria.tomas.com.practica2.models.object.Message;
import mensajeria.tomas.com.practica2.controler.connect_server.HttpHandler;
import mensajeria.tomas.com.practica2.models.sql.ManagerSQL;
import mensajeria.tomas.com.practica2.models.sql.SchemaContract;
import mensajeria.tomas.com.practica2.R;


public class MessageReceived extends BroadcastReceiver
{
    private TextView textViewMessage;
    private ManagerSQL dataBase;
    private Message message;
    private Config config;

    public MessageReceived(){}
    

    @Override
    public void onReceive(Context context, Intent intent)
    {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        this.dataBase = new ManagerSQL(context);
        final SmsMessage SMS [] = this.getMessageReceiver(intent);
        this.textViewMessage = (TextView) (((Activity)context).getWindow().getDecorView().findViewById(R.id.mesanjeXML));
        if(this.config.getPhone().equalsIgnoreCase(SMS[0].getOriginatingAddress()))
        {
            this.getDataTableConfig();
            this.getDataMessage(SMS);
            new HttpHandler(this.encodeURL(), this.textViewMessage, this.config).execute();
        }
    }

    private SmsMessage[] getMessageReceiver(Intent intent)
    {
       return Telephony.Sms.Intents.getMessagesFromIntent(intent);
    }
    private void getDataTableConfig()
    {
        String args[] = {"1"};
        Cursor cursor = this.dataBase.readDateIntoTableConfig(SchemaContract.COLUMN_NAME_ID_CONFIG, args);
        this.config = this.dataBase.readCursorConfig(cursor);
    }

    private void getDataMessage(SmsMessage[] sms)
    {
        final String LATITUDE = "Latitud", LONGITUDE ="Longitud",
                     ALTITUDE ="Altitud", SPEED = "Speed";
        final String [] SPLIT = sms[0].getDisplayMessageBody().split(" ");
        this.message = new Message();

        for(int contSecond = 0, contFirst = 0; contSecond < SPLIT.length; contSecond++)
        {
            SPLIT[contSecond] = SPLIT[contSecond].replace(":", "");
            if(SPLIT[contSecond].equalsIgnoreCase(LATITUDE))
            {
                this.message.setLatitude(SPLIT[++contSecond].replace(",", ""));
                contFirst++;
            }else if(SPLIT[contSecond].equalsIgnoreCase(LONGITUDE))
            {
                this.message.setLongitude(SPLIT[++contSecond].replace(",", ""));
                contFirst++;
            } else if (SPLIT[contSecond].equalsIgnoreCase(ALTITUDE))
            {
                this.message.setAltitude(SPLIT[++contSecond].replace(",", ""));
                contFirst++;
            }else if(SPLIT[contSecond].equalsIgnoreCase(SPEED))
            {
                this.message.setSpeed(SPLIT[++contSecond].replace(",", ""));
            }
        }
    }

    private String encodeURL()
    {
        String url = this.config.getHost() + "?latitud=" + this.message.getLatitude() +
                              "&longitud=" + this.message.getLongitude() +
                              "&altitud=" + this.message.getAltitude() +
                              "&speed=" + this.message.getSpeed();
        return url;
    }
}
