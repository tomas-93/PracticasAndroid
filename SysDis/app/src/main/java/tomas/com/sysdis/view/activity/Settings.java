package tomas.com.sysdis.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.EditText;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.View;
import android.content.Intent;
import tomas.com.sysdis.R;

public class Settings extends AppCompatActivity
{
    private EditText octeto1, octeto2, octeto3, octeto4;
    private ImageButton returnButton, plus;
    private Animation anaimation;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_settings);

        this.returnButton = (ImageButton) this.findViewById(R.id.returnXML);
        this.plus = (ImageButton) this.findViewById(R.id.plusXML);
        this.textView = (TextView) this.findViewById(R.id.messageXML);
        this.octeto1 = (EditText) this.findViewById(R.id.octeto1XML);
        this.octeto2 = (EditText) this.findViewById(R.id.octeto2XML);
        this.octeto3 = (EditText) this.findViewById(R.id.octeto3XML);
        this.octeto4 = (EditText) this.findViewById(R.id.octeto4XML);

        this.anaimation = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.animator_button);
        this.message();
    }

    public void onClickReturn(View view)
    {
        this.returnButton.startAnimation(this.anaimation);
        this.startActivity(new Intent(this.getApplicationContext(), MainActivity.class));

    }
    public void onClickPlus(View view)
    {
        this.plus.startAnimation(this.anaimation);
        this.message();
    }

    private void message()
    {
        String message = "IP del Servidor\n" + this.octeto1.getText() + "."+
                this.octeto2.getText()+"."+
                this.octeto3.getText()+"."+
                this.octeto4.getText();///falta metodo status........

        this.textView.setText(message);
    }

}
