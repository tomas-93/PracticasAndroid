package tomas.com.sysdis.controller.network.handle_sockets;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.net.SocketException;

import tomas.com.sysdis.controller.network.sockets.Connect;

/**
 * Created by Tomas on 08/10/2015.
 */
public class HandleServices extends IntentService implements Runnable
{
    private final String TAG = "HandleServices";
    private Connect connect;
    private Thread thread;

    public HandleServices()
    {
        super("");
    }

    @Override
    public void onHandleIntent(Intent intent)
    {
        this.thread = new Thread(this);
        Log.w(this.TAG,"Instacia de connect");
        this.connect = new Connect("192.168.0.4");
        Log.w(this.TAG, "Iniciar hilo");
        this.thread.start();
    }

    @Override
    public void run()
    {
        try
        {
            synchronized (this)
            {
                while(true)
                {
                    //hay red?
                    if(isConnected())
                    {
                        Log.w(this.TAG,"...............................................");
                        this.connect.responseServer();
                    }else
                    {
                        Log.w(this.TAG,"Red inabilitada");
                    }
                    this.thread.sleep(500);
                }
            }
        }
        catch (SocketException e)
        {
            Log.e(this.TAG,"Se perdio la conexion del servidor");
            e.printStackTrace();
        }catch(Exception e)
        {
            Log.e(this.TAG,"Error");
            e.printStackTrace();
        }finally
        {
            this.connect.theServerClose();
        }
    }

    public void startIntentService(Context context)
    {
        context.startService(new Intent(context, HandleServices.class));
    }

    private boolean isConnected()
    {
        boolean flag = true;
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if(info == null || !info.isConnected() || !info.isAvailable())
        {
            flag = false;
        }
        return flag;
    }

}
