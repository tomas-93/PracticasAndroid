package mensajeria.tomas.com.practica2.controler.connect_server;

import android.database.Cursor;
import android.util.Log;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mensajeria.tomas.com.practica2.models.object.Config;
import mensajeria.tomas.com.practica2.models.sql.ManagerSQL;

public class HttpHandler extends AsyncTask <Void, Void, String>
{
    private TextView textViewMessage;
    private String url;
    private Config config;
    private final String TAG = "HttpHandler";


    public HttpHandler(String url, TextView textView, Config config)
    {
        this.url = url;
        this.textViewMessage = textView;
        this.config = config;
    }
    @Override
    protected String doInBackground(Void... _void)
    {
        String cadena="";
        HttpClient httpclient = new DefaultHttpClient();
        try
        {
            HttpGet get = new HttpGet(this.url);
            Log.i(TAG,"httpcliente execute");
            HttpResponse resp = httpclient.execute(get);
            Log.i(TAG, "end httpclient execute");
            HttpEntity ent = resp.getEntity();
            if(ent != null)
            {
                cadena = EntityUtils.toString(ent);
            }else cadena = "No respuesta de host";
            Log.i(TAG, cadena);
        }catch(IllegalArgumentException e)
        {
            cadena = "error2";
        }catch(Exception e)
        {
            cadena = "error1";
        }finally
        {
            return cadena;
        }

    }
    @Override
    protected void onPostExecute(String response)
    {
        String message = "Elementos Guardados\n\n" +
                         "Telefono: \n" +
                         this.config.getPhone() +
                         "\n\nHost:\n" +
                         this.config.getHost() +
                         "\n\nHostStatus:\n" +
                         this.config.getHostStatus();
        if(response.equals("error1"))
        {
            message ="No se recibio respuesta del servidor";
            response ="";
        }else if(response.equals("error2"))
        {
            response ="";
            message ="Verifique el URL";
        }
        this.textViewMessage.setText(message + "\n" +response);
    }


}