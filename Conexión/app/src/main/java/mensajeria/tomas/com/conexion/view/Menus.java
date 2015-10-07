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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class Menus extends Activity implements View.OnClickListener
{
    private EditText host,name;
    private TextView textViewMessage;
    private ImageButton buttonProcess, editName, editHost;
    private ManagerSQL managerSQL;
    private Config config;
    private CheckBox chek;
    private Animation animationUtils;
    private final String TAG ="Activity";
    private boolean flagHost, flagName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.setContentView(R.layout.activity_menus);

        //Base de datos
        this.managerSQL = new ManagerSQL(this.getApplicationContext());
        //Instacia
        this.host = (EditText) this.findViewById(R.id.hostXML);
        this.textViewMessage = (TextView) this.findViewById(R.id.mesanjeXML);
        this.name = (EditText) this.findViewById(R.id.nameXML);
        this.buttonProcess = (ImageButton) this.findViewById(R.id.buttonProcessXML);
        this.editHost = (ImageButton) this.findViewById(R.id.buttonEditHostXML);
        this.editName = (ImageButton) this.findViewById(R.id.buttonEditNameXML);
        this.chek = (CheckBox) this.findViewById(R.id.enableXML);

        this.animationUtils = AnimationUtils.loadAnimation(this, R.anim.animation_button);

        this.flagHost = false;
        this.flagName = true;

        //Event..
        this.buttonProcess.setOnClickListener(this);
        this.editHost.setOnClickListener(this);
        this.editName.setOnClickListener(this);
        this.chek.setOnClickListener(this);
        //Iniciar servicio
        ServicesHTTP service = new ServicesHTTP();
        service.onService(this.getApplicationContext());
        readDate();
    }

    @Override
    public void onClick(View view)
    {
        try
        {
            if(view.getId() == this.buttonProcess.getId())
            {
                this.flagHost = false;
                this.flagName = false;
                this.buttonProcess.startAnimation(this.animationUtils);
                this.saveDateinDataBase();
                this.readDate();
                this.host.setText("");
                this.name.setText("");
            }else if(view.getId() == this.editName.getId())
            {
                this.flagName = true;
                this.editName.startAnimation(this.animationUtils);
                if(this.config.getName() != null)this.name.setText(this.config.getName(), TextView.BufferType.EDITABLE);
                else Toast.makeText(this.getApplicationContext(), "No hay datos guardados", Toast.LENGTH_SHORT).show();
                this.readDate();

            }else if(view.getId() == this.editHost.getId())
            {
                this.flagHost = true;
                this.editHost.startAnimation(this.animationUtils);
                if(this.config.getHost() != null) this.host.setText(this.config.getHost(), TextView.BufferType.EDITABLE);
                else Toast.makeText(this.getApplicationContext(), "No hay datos guardados", Toast.LENGTH_SHORT).show();
                this.readDate();

            }else if(view.getId() == this.chek.getId())
            {
                final int ID = 1;
                if(this.chek.isChecked())
                {
                    this.managerSQL.insertIntoTableConfig(ID, this.config.getHost(), this.config.getName(), "true");
                }else
                {
                    this.managerSQL.insertIntoTableConfig(ID, this.config.getHost(), this.config.getName(), "false");
                }

                this.readDate();
            }
        }catch (NullPointerException e)
        {
            Toast.makeText(this.getApplicationContext(), "No hay datos guardados", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveDateinDataBase()
    {
        final int ID = 1;
        if(!this.host.getText().toString().equals("") && !this.name.getText().toString().equals(""))
        {
            if(this.chek.isChecked())
            {
                this.managerSQL.insertIntoTableConfig(ID, this.host.getText().toString(), this.name.getText().toString(), "true");
            }else
            {
                this.managerSQL.insertIntoTableConfig(ID, this.host.getText().toString(), this.name.getText().toString(), "false");
            }
        }else if(this.flagHost)
        {
            this.managerSQL.insertIntoTableConfig(ID, this.host.getText().toString(), this.config.getName(), this.config.getStatus());
        }else if(this.flagName)
        {
            this.managerSQL.insertIntoTableConfig(ID, this.config.getHost(), this.name.getText().toString(), this.config.getStatus());
        }else Toast.makeText(this.getApplicationContext(),"Ingrese URL y El nombre del dispositivo", Toast.LENGTH_SHORT).show();
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
                message = "Host:\n" + config.getHost() + "\nNombre:     " + config.getName() + "\nEstado:     " + config.getStatus();
            }
            if(this.config.getStatus().equals("true"))this.chek.setChecked(true);
            else this.chek.setChecked(false);
        }catch (android.database.CursorIndexOutOfBoundsException ex)
        {
            Log.e(TAG, "Cursor de la base de datos vacio.");
            message = "No hay elementos guardados en la base de datos";
        }
        finally {
            this.textViewMessage.setText(message);
        }
    }





}
