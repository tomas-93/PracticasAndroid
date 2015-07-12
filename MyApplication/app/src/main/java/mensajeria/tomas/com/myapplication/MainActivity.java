package mensajeria.tomas.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void procesarMensaje(View vista)
    {
        //Instanciado los componentes, creando el objeto y llamndo desde la XML con R
        EditText editorTexto = (EditText) this.findViewById(R.id.textoDeEntradaET);
        TextView mensaje = (TextView) this.findViewById(R.id.mensajeTV);
        //Cambiando valores
        mensaje.setText("Mensaje: "+editorTexto.getText());
    }

}
