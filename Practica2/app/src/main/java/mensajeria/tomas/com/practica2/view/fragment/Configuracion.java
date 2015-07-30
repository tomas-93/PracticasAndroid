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

import mensajeria.tomas.com.practica2.controler.connect_server.HttpHandler;
import mensajeria.tomas.com.practica2.R;
import mensajeria.tomas.com.practica2.models.sql.ManagerSQL;


/**
 * A simple {@link Fragment} subclass.
 */
public class Configuracion extends Fragment implements OnClickListener
{
    private EditText numberPhone, host;
    private TextView textViewMessage;
    private Button buttonProcess, connect;
    private ManagerSQL managerSQL;

    public Configuracion()
    {
        // Required empty public constructor
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
            new HttpHandler("http://192.168.0.2/tomasyussef/models/android/status.php").execute();
        }

    }


    /*
            Method private......
     */



    private void saveDateinDataBase()
    {
        String phone = (String) this.numberPhone.getText().toString();
        String host = (String) this.host.getText().toString();
        this.managerSQL.insertIntoTableConfig(1,phone,host);
    }

    private void readDate()
    {
        try
        {
            String args [] = { "1" };
            Cursor cursor = this.managerSQL.readDateIntoTableConfig("idCofig", args);
            String elements [] = this.managerSQL.readCursor(cursor);
            String message = "Elementos Guardados\n" + "Telefono: " + elements[0]+ "\nHost:" + elements[1];
            this.textViewMessage.setText(message);
        }catch (android.database.CursorIndexOutOfBoundsException ex)
        {}
    }


}
