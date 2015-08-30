package mensajeria.tomas.com.conexion.view;

import mensajeria.tomas.com.conexion.R;
import mensajeria.tomas.com.conexion.controler.services.ServicesHTTP;
import mensajeria.tomas.com.conexion.models.object.Config;
import mensajeria.tomas.com.conexion.models.sql.ManagerSQL;

import android.app.Activity;
import android.app.ActivityManager;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Menus extends Activity implements View.OnClickListener
{
    private EditText host;
    private TextView textViewMessage;
    private Button buttonProcess, insert;
    private ManagerSQL managerSQL;
    private Config config;
    private final String TAG ="Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.setContentView(R.layout.activity_menus);

        //Base de datos
        this.managerSQL = new ManagerSQL(this.getApplicationContext());
        //Instacia
        this.host = (EditText) this.findViewById(R.id.hostXML);
        this.insert = (Button) this.findViewById(R.id.buttonXML);
        this.textViewMessage = (TextView) this.findViewById(R.id.mesanjeXML);
        this.buttonProcess = (Button) this.findViewById(R.id.buttonProcessXML);
        this.readDate();
        //Event..
        this.buttonProcess.setOnClickListener(this);
        this.insert.setOnClickListener(this);

        //Iniciar servicio
        ServicesHTTP service = new ServicesHTTP();
        service.onService(this.getApplicationContext());
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == this.buttonProcess.getId())
        {
            this.saveDateinDataBase();
            this.readDate();
            this.host.setText("");
        }else if(view.getId() == this.insert.getId())
        {
            for(int cont = 0; cont < 100; cont++)
            {
                this.managerSQL.insertIntoTableMessage(this.managerSQL.getIdMessage(),"Boton","xx/xx/xx","Hola " +this.managerSQL.getIdMessage());
            }
            Toast.makeText(this.getApplicationContext(),"Id: " + this.managerSQL.getIdMessage(),Toast.LENGTH_LONG).show();
        }
    }

    private void saveDateinDataBase()
    {
        int id = 1;
        String host = (String) this.host.getText().toString();
        this.managerSQL.insertIntoTableConfig(id, host);
    }

    private void readDate()
    {
        try
        {
            String args [] = { "1" };
            Cursor cursor = this.managerSQL.readDateIntoTableConfig("idCofig", args);
            this.config = this.managerSQL.readCursorConfig(cursor);
            String message = "Elementos Guardados\n\n" + "Host:\n" + config.getHost();
            this.textViewMessage.setText(message);
        }catch (android.database.CursorIndexOutOfBoundsException ex)
        {
            Log.e(TAG, "Cursor de la base de datos vacio.");
        }
    }





}
