package com.tomas.main.controller_network;

import android.util.Log;

import com.tomas.main.controller_view.fragments.TestHTTPUrlConnection;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Tomas on 29/10/2015.
 */
public class TestHttpUrlConnectionNetwork
{
    public void sendMessage(String hours, String seconds, String message)
    {
        HttpURLConnection connection = null;
        try
        {
            connection = (HttpURLConnection) new URL("http://192.168.0.2/test/pruebaHTTPUrlConnection.php").openConnection();

            String data = "hora=" + URLEncoder.encode(hours, "UTF-8") +
                    "&segundos="+URLEncoder.encode(seconds,"UTF-8") +
                    "&message="+URLEncoder.encode(message, "UTF-8");
            //Enviar por post
            connection.setDoOutput(true);
            //Especificar tamaño de los datos que se van a enviar
            connection.setFixedLengthStreamingMode(data.getBytes().length);
            //Establecer esta propiedad debido a la simplicidad de los datos
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //Envio de informacion
            OutputStream outputStream = new BufferedOutputStream(connection.getOutputStream());

            outputStream.write(data.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String aux ="";
            String respuesta ="";
            while((aux = bufferedReader.readLine()) != null)
            {
                respuesta += aux +"\n";
            }
            Log.w("HTTP", respuesta);

            inputStream.close();


        }catch (Exception e)
        {
            //java.net.MalformedURLException: Protocol not found: 192.168.0.2/test/pruebaHTTPUrlConnection.php
            //java.net.ProtocolException: unexpected end of stream
            e.printStackTrace();

        }finally
        {
            if(connection != null) connection.disconnect();
        }
    }
}
