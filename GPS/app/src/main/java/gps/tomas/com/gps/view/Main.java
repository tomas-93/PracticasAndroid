package gps.tomas.com.gps.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import gps.tomas.com.gps.R;

public class Main extends Activity implements View.OnClickListener, OnItemSelectedListener
{
    private Button saveButtom, editButtom;
    private EditText host, time;
    private CheckBox check;
    private Spinner listSpinner;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        //instancia
        this.saveButtom = (Button) this.findViewById(R.id.saveXML);
        this.editButtom = (Button) this.findViewById(R.id.editXML);
        this.host = (EditText) this.findViewById(R.id.hostXML);
        this.time = (EditText) this.findViewById(R.id.timeXML);
        this.check = (CheckBox) this.findViewById(R.id.processXML);
        this.listSpinner = (Spinner) this.findViewById(R.id.sppinerXML);
        this.message = (TextView) this.findViewById(R.id.messageXML);
        //Eventos
        this.saveButtom.setOnClickListener(this);
        this.editButtom.setOnClickListener(this);
        this.check.setOnClickListener(this);
        this.listSpinner.setOnItemSelectedListener(this);
    }
    @Override
    public void onClick(View view)
    {

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        Log.w("Spinner",String.valueOf(position));
        Log.w("Spinner",String.valueOf(id));
        Log.w("Spinner", listSpinner.getSelectedItem().toString());
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
