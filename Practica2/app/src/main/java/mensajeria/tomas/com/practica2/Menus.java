package mensajeria.tomas.com.practica2;

import mensajeria.tomas.com.practica2.fragment.Configuracion;
import mensajeria.tomas.com.practica2.fragment.Maps;

import android.app.Activity;
import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.app.FragmentManager;
import android.app.Fragment;
import android.os.Bundle;



public class Menus extends Activity implements TabListener
{
    private final Fragment arrayFragment[]={new Configuracion(),new Maps()};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_menus);
        //Configuracion de tabs
        this.configTabs();

        //Cofiguracion de vista
        this.configView();

    }
    private void configTabs()
    {
        //navegacion pestañas
        ActionBar actionBar = this.getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        //agregar elementos a la barra
        actionBar.addTab(actionBar.newTab().setText(R.string.configuracion).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.mapas).setTabListener(this));
    }
    private void configView()
    {
        FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

        for(Fragment fragment: this.arrayFragment)
        {
            fragmentTransaction.add(R.id.menus, fragment).hide(fragment);
        }
        fragmentTransaction.show(this.arrayFragment[0]).commit();

    }
    /*
            Eventos........
     */

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
    {
        for(Fragment fragment: this.arrayFragment)
        {
            fragmentTransaction.hide(fragment);
        }
        fragmentTransaction.show(this.arrayFragment[tab.getPosition()]);
    }
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
    {

    }
    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
    {

    }

}
