package mensajeria.tomas.com.practica2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Login extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_practica2);
    }

    public void onClick(View event)
    {
        //Mensaje Toast para iniciar sesion
        Toast.makeText(this, "Iniciando Sesion", Toast.LENGTH_LONG).show();
        //llamar segunda actividad
        this.startActivity(new Intent(this, Menus.class));
    }

}
