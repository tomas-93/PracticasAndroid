package tomas.com.sysdis.controller.network.sockets;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Tomas on 08/10/2015.
 */
public class Connect
{
    private Socket serverSocket;
    private DataOutputStream output;
    private BufferedReader readInput;
    private String ip;
    private final int PORT = 65535;
    private final String TAG = "Connect ";


    public Connect(String ip)
    {
        this.ip = ip;
        this.connectClient();
    }

    public void connectClient()
    {
        try
        {
            Log.e(TAG,"connectClient()");
            this.serverSocket = new Socket(this.ip, PORT);
            this.output = new DataOutputStream(this.serverSocket.getOutputStream());
            this.readInput = new BufferedReader(new InputStreamReader(this.serverSocket.getInputStream()));
        }catch (Exception e)
        {

        }
    }
    public void sendData(String data) throws Exception
    {
        Log.e(TAG, "sendData()");
        this.output.writeUTF(data);
    }

    public void responseServer() throws  Exception
    {
        this.serverSocket = new Socket(this.ip, PORT);
        Log.e(TAG, "responseServer()");
        String llego;
        llego = this.readInput.readLine();
        Log.e(this.TAG,"Server: "+llego);
    }

    public void theServerClose()
    {
        try
        {
            this.serverSocket.close();

        }catch (Exception e)
        {
            Log.e(TAG,"Respose Server");
            e.printStackTrace();
        }
    }

}
