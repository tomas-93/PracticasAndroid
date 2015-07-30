package mensajeria.tomas.com.practica2.controler.connect_server;

import android.util.Log;
import android.os.AsyncTask;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class HttpHandler extends AsyncTask <Void, Void, String>
{
    private String url;
    private final String TAG = "HttpHandler";

    public HttpHandler(String url)
    {
        this.url = url;
    }
    @Override
    protected String doInBackground(Void... params)
    {
        String cadena="";
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet get = new HttpGet(this.url);
        try
        {
            Log.i(TAG,"httpcliente execute");
            HttpResponse resp = httpclient.execute(get);
            Log.i(TAG, "end httpclient execute");
            HttpEntity ent = resp.getEntity();
            if(ent != null)
            {
                cadena = EntityUtils.toString(ent);
            }else cadena = "No respuesta de host";
            Log.i(TAG, cadena);
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            return cadena;
        }

    }
    @Override
    protected void onProgressUpdate(Void... _void)
    {

    }
    @Override
    protected void onPostExecute(String response)
    {

    }
}