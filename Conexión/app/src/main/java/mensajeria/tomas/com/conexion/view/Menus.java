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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Menus extends Activity implements View.OnClickListener
{
    private EditText host;
    private TextView textViewMessage;
    private Button buttonProcess;
    private ManagerSQL managerSQL;
    private Config config;
    private CheckBox chek;
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
        this.textViewMessage = (TextView) this.findViewById(R.id.mesanjeXML);
        this.buttonProcess = (Button) this.findViewById(R.id.buttonProcessXML);
        this.chek =(CheckBox) this.findViewById(R.id.enableXML);
        //Event..
        this.buttonProcess.setOnClickListener(this);
        this.chek.setOnClickListener(this);
        //Iniciar servicio
        ServicesHTTP service = new ServicesHTTP();
        service.onService(this.getApplicationContext());
        readDate();
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == this.buttonProcess.getId())
        {
            this.saveDateinDataBase();
            this.readDate();
            this.host.setText("");
        }else if(view.getId() == this.chek.getId())
        {
            Toast.makeText(this.getApplicationContext(), "Habilitando el envio de datos", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveDateinDataBase()
    {
        int id = 1;
        String host = (String) this.host.getText().toString();
        if(!this.host.getText().toString().equals(""))
        {
            if(this.chek.isChecked())
            {
                this.managerSQL.insertIntoTableConfig(id, host, "true");
            }else
            {
                Toast.makeText(this.getApplicationContext(), "El envio de datos desabilitado", Toast.LENGTH_SHORT).show();
                this.managerSQL.insertIntoTableConfig(id, host, "false");
            }
        }else Toast.makeText(this.getApplicationContext(),"Ingrese URL", Toast.LENGTH_SHORT).show();
    }

    private void readDate()
    {
        String message = "";
        try
        {
            String args [] = { "1" };
            Cursor cursor = this.managerSQL.readDateIntoTableConfig("idCofig", args);
            this.config = this.managerSQL.readCursorConfig(cursor);

            if(this.config.getHost() != null)
            {
                message = "Elementos Guardados\n\n" + "Host:\n" + config.getHost();
            }
            this.textViewMessage.setText(message);
        }catch (android.database.CursorIndexOutOfBoundsException ex)
        {
            Log.e(TAG, "Cursor de la base de datos vacio.");
            message = "Elementos Guardados\n\n" + "Host:\n No hay registro del URL";
        }
        finally {
            this.textViewMessage.setText(message);
        }
    }





}
