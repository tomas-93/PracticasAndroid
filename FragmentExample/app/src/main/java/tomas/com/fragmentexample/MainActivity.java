package tomas.com.fragmentexample;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void b1(View view)
    {
        Fragment_Uno uno = new Fragment_Uno();
        if(!uno.isAdded())
        {
            FragmentManager manager = this.getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_conteiner_dinamic,uno);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
    public void b2(View view)
    {
        Fragment_Dos dos = new Fragment_Dos();
        if(!dos.isAdded())
        {
            FragmentManager manager = this.getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_conteiner_dinamic, dos);
            transaction.addToBackStack(null);
            transaction.commit();
        }

    }
}
