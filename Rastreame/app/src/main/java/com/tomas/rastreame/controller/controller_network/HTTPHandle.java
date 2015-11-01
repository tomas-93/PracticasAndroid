package com.tomas.rastreame.controller.controller_network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.tomas.rastreame.telephony_message.SendMessage;

/**
 * Created by Tomas on 26/10/2015.
 */
public class HTTPHandle
{
    private HttpURLConnection httpURLConnection;
    private URL url;
    private InputStream inputStream;
    private OutputStream outputStream;
    private BufferedReader bufferedReader;
    private SendMessage sendMessageprocess;
    private String urlSendMessage, urlReceive, message, date, time, seconds,number ,data;
    private final String PROTOCOL = "http://";

    public HTTPHandle()
    {
        this.httpURLConnection = null;
        this.data = null;
    }

    public void setData(String urlSendMessage,String number ,String message, String date, String time,  String seconds)
    {
        this.urlSendMessage = urlSendMessage;
        this.message = message;
        this.date = date;
        this.time = time;
        this.seconds = seconds;
        this.number = number;
    }
    public void setData(String urlReceive)
    {
        this.urlReceive = urlReceive;
    }
    public void seenMessage()
    {
        try
        {
            //Preparar HttpUrlConnection
            this.url = new URL(this.PROTOCOL+this.urlSendMessage);
            this.httpURLConnection = (HttpURLConnection) this.url.openConnection();
            this.data = this.prepareData();
            this.httpURLConnection.setDoOutput(true);
            this.httpURLConnection.setFixedLengthStreamingMode(this.data.length());
            this.httpURLConnection.setRequestProperty("Content-Type","application/x-www-from-urlencoded");
            //Enviar datos
            this.outputStream = new BufferedOutputStream(this.httpURLConnection.getOutputStream());
            this.outputStream.write(this.data.getBytes());
            this.outputStream.flush();
            this.outputStream.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            if(this.httpURLConnection != null)
                this.httpURLConnection.disconnect();
        }
    }
    public void receiveMessage()
    {
        try
        {
            //Preparar HTTPURLConnection
            this.sendMessageprocess = new SendMessage();
            this.url = new URL(this.PROTOCOL + this.urlReceive);
            this.httpURLConnection = (HttpURLConnection) this.url.openConnection();
            this.httpURLConnection.setRequestProperty("Content-Type", "application/x-www-from-urlencoded");
            this.httpURLConnection.setDoOutput(true);
            //Recivir datos
            this.inputStream = new BufferedInputStream(this.httpURLConnection.getInputStream());
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));
            String aux, response = "";
            while((aux = this.bufferedReader.readLine()) != null)
            {
                response +=aux;
            }
            this.sendMessageprocess.setJson(response);
            this.sendMessageprocess.procesSendMessage();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private String prepareData()throws Exception
    {
        return "number=" + URLEncoder.encode(this.number, "UTF-8") +
                ",message=" + URLEncoder.encode(this.message,"UTF-8")+
                ",date=" + URLEncoder.encode(this.date,"UTF-8") +
                ",time=" + URLEncoder.encode(this.time,"UTF-8") +
                ",seconds=" + URLEncoder.encode(this.seconds,"UTF-8");
    }
}
