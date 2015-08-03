package mensajeria.tomas.com.practica2.view.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.database.Cursor;

import mensajeria.tomas.com.practica2.models.object.Config;
import mensajeria.tomas.com.practica2.models.sql.ManagerSQL;
import mensajeria.tomas.com.practica2.controler.connect_server.HttpHandler;
import mensajeria.tomas.com.practica2.R;



/**
 * A simple {@link Fragment} subclass.
 */
public class Configuracion extends Fragment implements OnClickListener
{
    private EditText numberPhone, host, hostStatus;
    private TextView textViewMessage;
    private Button buttonProcess, connect;
    private ManagerSQL managerSQL;
    private Config config;

    public Configuracion()
    {
        // Required empty public constructor
        this.config = new Config();
    }

    @Override
    public void onCreate(Bundle saveInstace)
    {
        super.onCreate(saveInstace);
        this.managerSQL = new ManagerSQL(this.getActivity().getApplicationContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View viewConteiner = inflater.inflate(R.layout.fragment_configuracion, container, false);

        this.numberPhone = (EditText) viewConteiner.findViewById(R.id.numberPhoneXML);
        this.host = (EditText) viewConteiner.findViewById(R.id.hostXML);
        this.hostStatus = (EditText) viewConteiner.findViewById(R.id.hostStatusXML);
        this.textViewMessage = (TextView) viewConteiner.findViewById(R.id.mesanjeXML);
        this.buttonProcess = (Button) viewConteiner.findViewById(R.id.buttonProcessXML);
        this.connect = (Button)  viewConteiner.findViewById(R.id.connecXML);
        this.readDate();
        //Event..
        this.buttonProcess.setOnClickListener(this);
        this.connect.setOnClickListener(this);
        // Inflate the layout for this fragment
        return viewConteiner;
    }


    /*
            Event...
     */

    @Override
    public void onClick(View view)
    {
        if(view.getId() == this.buttonProcess.getId())
        {
            this.saveDateinDataBase();
            this.readDate();
        }else if(view.getId() == this.connect.getId())
        {
            if(this.config.getHostStatus() != null || this.config.getHostStatus() != "")
            {
                new HttpHandler(this.config.getHostStatus(), this.textViewMessage, this.config).execute();
            }else
            {
                String message = "Elementos Guardados\n\n"
                               + "Telefono: \n" + config.getPhone()
                               + "\n\nHost:\n" + config.getHost()
                               + "\n\nHostStatus:\n"
                               + "No existe Dominio de Status, no se puede hacer pruebas de conexion.";
                this.textViewMessage.setText(message);
            }
        }

    }


    /*
            Method private......
     */



    private void saveDateinDataBase()
    {
        int id = 1;
        String phone = (String) this.numberPhone.getText().toString();
        String host = (String) this.host.getText().toString();
        String hostStatus = (String) this.hostStatus.getText().toString();
        this.managerSQL.insertIntoTableConfig(id, phone ,host, hostStatus);
    }

    private void readDate()
    {
        try
        {
            String args [] = { "1" };
            Cursor cursor = this.managerSQL.readDateIntoTableConfig("idCofig", args);
            this.config = this.managerSQL.readCursorConfig(cursor);
            String message = "Elementos Guardados\n\n" + "Telefono: \n" + config.getPhone() + "\n\nHost:\n" + config.getHost() + "\n\nHostStatus:\n" + config.getHostStatus();
            this.textViewMessage.setText(message);
        }catch (android.database.CursorIndexOutOfBoundsException ex)
        {}
    }


}
