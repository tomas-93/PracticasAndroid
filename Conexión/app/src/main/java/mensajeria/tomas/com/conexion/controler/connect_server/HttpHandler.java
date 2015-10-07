package mensajeria.tomas.com.conexion.controler.connect_server;
import java.util.List;
import java.util.ArrayList;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import mensajeria.tomas.com.conexion.models.sql.ManagerSQL;

public class HttpHandler
{
    private ManagerSQL sql;
    private String url;
    private String message;
    private String phone;
    private String date;
    private String name;
    private final String TAG = "HttpHandler";

    public HttpHandler(String message,String phone,String date, String url, String name)
    {
        this.message = message;
        this.phone = phone;
        this.date= date;
        this.url = url;
        this.name = name;
    }

    public boolean sendData()
    {
        boolean conect = false;
        HttpClient httpclient = new DefaultHttpClient();
        try
        {
            HttpPost post = new HttpPost(this.url);

            Log.i(TAG, "En proceso de envio de datos.");

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("data", this.message));
            params.add(new BasicNameValuePair("number", this.phone));
            params.add(new BasicNameValuePair("date", this.date));
            params.add(new BasicNameValuePair("name", this.name));

            post.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse resp = httpclient.execute(post);

            Log.i(TAG, "Se envio la informacion");

            HttpEntity ent = resp.getEntity();

            if(ent != null)
            {
                Log.i(this.TAG, EntityUtils.toString(ent));
                conect = true;
            } else Log.e(this.TAG, "No se recibio respuesta del server");
        } catch (IllegalArgumentException e)
        {
            Log.e(this.TAG, "Verificar URL");
            e.printStackTrace();
        }catch (HttpHostConnectException e)
        {
            Log.e(this.TAG, "El servior no responde");
            e.printStackTrace();
        }catch (Exception e)
        {
            Log.e(this.TAG, "Error general");
            e.printStackTrace();
        } finally
        {
            return conect;
        }

    }
}