package tomas.com.sysdis.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.content.Intent;
import tomas.com.sysdis.R;


public class MainActivity extends AppCompatActivity
{
    ImageButton list, settings, register;
    Animation animationUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        this.settings = (ImageButton) this.findViewById(R.id.settingsXML);
        this.list = (ImageButton) this.findViewById(R.id.listXML);
        this.register = (ImageButton) this.findViewById(R.id.registerXML);

        this.animationUtils = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.animator_button);
    }

    /*
            Eventos
     */

    public void onClickSettings(View view)
    {
        this.settings.startAnimation(this.animationUtils);
        this.startActivity(new Intent(this.getApplicationContext(), Settings.class));
    }

    public void onClickList(View view)
    {
        this.list.startAnimation(this.animationUtils);
        this.startActivity(new Intent(this.getApplicationContext(), List.class));
    }

    public void onClickRegister(View view)
    {
        this.register.startAnimation(this.animationUtils);
        this.startActivity(new Intent(this.getApplicationContext(), Register.class));
    }


}
