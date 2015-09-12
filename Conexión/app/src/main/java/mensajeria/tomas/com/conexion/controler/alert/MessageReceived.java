package mensajeria.tomas.com.conexion.controler.alert;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.os.Bundle;
import android.telephony.SmsMessage;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import mensajeria.tomas.com.conexion.controler.services.ServicesHTTP;
import mensajeria.tomas.com.conexion.models.object.Config;
import mensajeria.tomas.com.conexion.models.object.Message;
import mensajeria.tomas.com.conexion.controler.connect_server.HttpHandler;
import mensajeria.tomas.com.conexion.models.sql.ManagerSQL;
import mensajeria.tomas.com.conexion.models.sql.SchemaContract;


public class MessageReceived extends BroadcastReceiver
{
    private ManagerSQL dataBase;
    private Message message;
    private Config config;
    private final String TAG = "BroadcastReceiver";

    public MessageReceived(){}
    

    @Override
    public void onReceive(Context context, Intent intent)
    {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //instacia de manejo de la base de datos
        this.dataBase = new ManagerSQL(context);
        //se llama al procedimiento para encapsular el objeto de la tabla config
        this.getDataTableConfig();
        //se crea una constante donde almacenara el objeto mensaje
        final SmsMessage SMS  = this.getMessageReceiver(intent);
        //se verifica que el objeto no sea nulo
        if(SMS != null && this.config.getStatus().equals("true"))
        {
            /*/se verifica que el numero del mesaje que se recibio sea el mismo de que el de la base de datos
            if(SMS.getDisplayOriginatingAddress().equalsIgnoreCase(SMS.getDisplayOriginatingAddress()))
            {

            }*/
            //se encapsula los datos del mensaje.
            this.getDataMessage(SMS);
            //Guardar DataBase
            this.dataBase.insertIntoTableMessage(this.message.getId(),
                    this.message.getPhone(),
                    this.message.getDate(),
                    this.message.getMessage()
            );
            //Iniciar servicio
            ServicesHTTP service = new ServicesHTTP();
            service.onService(context);
        }
    }

    private SmsMessage getMessageReceiver(Intent intent)
    {
        this.message = new Message();
        Bundle bundle = intent.getExtras();
        SmsMessage sms = null;
        try
        {
            if(bundle != null)
            {
                Object object[] = (Object[]) bundle.get("pdus");
                StringBuilder sb = new StringBuilder();
                for(int cont = 0; cont < object.length; cont++ )
                {
                    sms = SmsMessage.createFromPdu((byte[]) object[cont]);
                    sb.append(sms.getMessageBody());
                }
                Log.w(TAG, sb.toString());
                this.message.setMessage(sb.toString());
            }
        }catch(Exception e)
        {
            Log.e("Error", e.getLocalizedMessage());
            e.printStackTrace();
        }finally
        {
            return sms;
        }
    }
    private void getDataTableConfig()
    {
        String args[] = {"1"};
        Cursor cursor = this.dataBase.readDateIntoTableConfig(SchemaContract.COLUMN_NAME_ID_CONFIG, args);
        this.config = this.dataBase.readCursorConfig(cursor);
    }

    private void getDataMessage(SmsMessage sms)
    {
        this.message.setId(this.dataBase.getIdMessage());
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.message.setDate(dateFormat.format(calendar.getTime()));
        this.message.setPhone(sms.getDisplayOriginatingAddress());
    }


}
