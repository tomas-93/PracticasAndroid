package mensajeria.tomas.com.practica2.controler.alert;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.database.Cursor;

import mensajeria.tomas.com.practica2.controler.connect_server.HttpHandler;
import mensajeria.tomas.com.practica2.models.sql.ManagerSQL;


public class MessageReceived extends BroadcastReceiver
{
    private ManagerSQL dataBase;

    public MessageReceived()
    {    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        this.dataBase = new ManagerSQL(context);
        final SmsMessage SMS [] = this.getMessageReceiver(intent);
        final String DATA_CONFIG [] = this.getDataTableConfig();
        if(DATA_CONFIG[0].equalsIgnoreCase(SMS[0].getOriginatingAddress()))
        {
            String DATA_MESSAGE [] = this.getDataMessage(SMS);
            String url = this.encodeURL(DATA_CONFIG, DATA_MESSAGE);
            new HttpHandler(url).execute();
        }
    }

    private SmsMessage[] getMessageReceiver(Intent intent)
    {
       return Telephony.Sms.Intents.getMessagesFromIntent(intent);
    }
    private String[] getDataTableConfig()
    {
        String args[] = {"1"};
        Cursor cursor = this.dataBase.readDateIntoTableConfig("idCofig", args);
        return this.dataBase.readCursor(cursor);
    }

    private String[] getDataMessage(SmsMessage[] sms)
    {
        final String LATITUDE = "Latitud", LONGITUDE ="Longitud",
                     ALTITUDE ="Altitud", SPEED = "Speed";
        final String [] SPLIT = sms[0].getDisplayMessageBody().split(" ")
                ,ELEMENT = new String[4];

        for(int contSecond = 0, contFirst = 0; contSecond < SPLIT.length; contSecond++)
        {
            SPLIT[contSecond] = SPLIT[contSecond].replace(":", "");
            if(SPLIT[contSecond].equalsIgnoreCase(LATITUDE))
            {
                System.out.println(SPLIT[contSecond]);
                ELEMENT[contFirst] = SPLIT[++contSecond].replace(",", "");
                System.out.println(ELEMENT[contFirst]);
                contFirst++;
            }else if(SPLIT[contSecond].equalsIgnoreCase(LONGITUDE))
            {
                System.out.println(SPLIT[contSecond]);
                ELEMENT[contFirst] = SPLIT[++contSecond].replace(",", "");
                System.out.println(ELEMENT[contFirst]);
                contFirst++;
            } else if (SPLIT[contSecond].equalsIgnoreCase(ALTITUDE))
            {
                System.out.println(SPLIT[contSecond]);
                ELEMENT[contFirst] = SPLIT[++contSecond].replace(",", "");
                System.out.println(ELEMENT[contFirst]);
                contFirst++;
            }else if(SPLIT[contSecond].equalsIgnoreCase(SPEED))
            {
                System.out.println(SPLIT[contSecond]);
                ELEMENT[contFirst] = SPLIT[++contSecond].replace(",", "");
                System.out.println(ELEMENT[contFirst]);
            }
        }
        return ELEMENT;
    }

    private String encodeURL(String [] dataCof, String [] dataMessage)
    {
        String url = dataCof+"?latitud=" + dataMessage[0] +
                              "&longitud=" + dataMessage[1] +
                              "&altitud=" + dataMessage[2] +
                              "&speed=" + dataMessage[3];
        return url;
    }
}
