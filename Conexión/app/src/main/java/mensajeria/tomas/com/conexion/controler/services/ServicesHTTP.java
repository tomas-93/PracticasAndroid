package mensajeria.tomas.com.conexion.controler.services;
;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.os.IBinder;
import android.util.Log;
import android.database.Cursor;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;

import mensajeria.tomas.com.conexion.controler.connect_server.HttpHandler;
import mensajeria.tomas.com.conexion.models.object.Message;
import mensajeria.tomas.com.conexion.models.object.Config;
import mensajeria.tomas.com.conexion.models.sql.ManagerSQL;
import mensajeria.tomas.com.conexion.models.sql.SchemaContract;

/**
 * Created by Tomas on 17/08/2015.
 */
public class ServicesHTTP extends Service implements Runnable
{
    private final String TAG = "ServicesHTTP";
    private Message message;
    private Config config;
    private Thread thread;

    public ServicesHTTP()
    {
        this.message = null;
        this.config = null;
    }

    @Override
    public void onCreate()
    {
        Log.i(this.TAG,"method oncreate");
        this.thread = new Thread(this);
    }

    @Override
    public void onDestroy()
    {
        Log.i(this.TAG, "method ondestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public  int onStartCommand(Intent intent, int flags, int startId)
    {
        this.thread.start();
        return this.START_NOT_STICKY;
    }

    @Override
    public void run()
    {
        try
        {
            synchronized(this)
            {
                ManagerSQL sqlite = new ManagerSQL(this.getApplicationContext());
                while(true)
                {
                    if(isConnected())
                    {
                        Log.i(this.TAG,"Hay conexion a la red...");
                        Cursor tableMessage = sqlite.readDateIntoTableMessage();
                        Cursor tableConfig = sqlite.readDateIntoTableConfig();
                        Log.i(this.TAG, "Proceso en etapa de envio de datos");
                        if(tableMessage.moveToFirst())
                        {
                            tableConfig.moveToFirst();
                            do{
                                Log.i(this.TAG, "Si hay datos, preparando para el envio, Numero de Datos: "+tableMessage.getCount());
                                if(this.message == null || this.config == null)
                                    this.encapsulateObjects(tableConfig, tableMessage, sqlite);
                                Log.i(this.TAG,"ID: "+this.message.getId());
                                HttpHandler http = new HttpHandler(this.message.getMessage(), this.message.getPhone(), this.message.getDate(),this.config.getHost(), this.config.getName());
                                if(http.sendData())
                                {
                                    Log.i(this.TAG, "Se envio datos....");
                                    Log.i(this.TAG, "Eliminando datos......");
                                    sqlite.removeMessageData(this.message.getId());
                                    this.message = null;
                                    if(tableMessage.isClosed())
                                    {
                                        tableMessage = sqlite.readDateIntoTableMessage();
                                        tableConfig = sqlite.readDateIntoTableConfig();
                                        tableConfig.moveToFirst();
                                    }
                                }else Log.e(this.TAG, "No se pudo enviar datos se intentara de nuevo");
                                this.thread.sleep(100);
                            } while(tableMessage.moveToFirst());
                        }
                        Log.i(this.TAG, "Proceso en espera de datos");
                    }else Log.i(this.TAG,"No hay conexion a la red");
                    this.thread.sleep(1000);
                }
            }
        }catch (InterruptedException argumen)
        {
            Log.e(this.TAG,"Se ha interrumpido el servicio");
            argumen.printStackTrace();
        }catch(IllegalMonitorStateException monitor)
        {
            Log.e(this.TAG,"Error en subproceso");
            monitor.printStackTrace();
        }catch(Exception e)
        {
            Log.e(this.TAG,"Error general");
            e.printStackTrace();
        }
    }



    private void encapsulateObjects(Cursor cursorConfig, Cursor cursorMessage, ManagerSQL sqlite)
    {
        //message
        this.message = sqlite.readCursorMessage(cursorMessage);
        //confing
        this.config = sqlite.readCursorConfig(cursorConfig);
    }


    public void starServicesHTTP(Context context)
    {
        Intent intent = new Intent(context, ServicesHTTP.class);
        context.startService(intent);
    }
    private boolean isConnected()
    {
        boolean connect = true;
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if(info == null || !info.isConnected() || !info.isAvailable())
        {
            connect = false;
        }
        return  connect;
    }

    public void onService(Context context)
    {
        if(!this.onStart(ServicesHTTP.class, context))
        {
            this.starServicesHTTP(context);
            Log.w(TAG, "Se le vanto el servicio, estaba muerto");
        }else Log.w(TAG, "No se le vanto, esta corriendo el servicio");
    }

    private boolean onStart(Class<?> serviceClass, Context context)
    {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo service: manager.getRunningServices(Integer.MAX_VALUE))
        {
            if(serviceClass.getName().equals(service.service.getClassName()))
            {
                return true;
            }
        }
        return false;
    }

}
