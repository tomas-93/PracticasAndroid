package tomas.com.practicamaps;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;


public class Maps extends Activity
{
    private GoogleMap googleMap;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_maps);
        //llamar id maps y llamar el metodo oncreate de mapview
        this.mapView = (MapView) this.findViewById(R.id.mapaXML);
        this.mapView.onCreate(savedInstanceState);
        //inicializar mapa
        this.googleMap = this.mapView.getMap();
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        this.googleMap.setMyLocationEnabled(true);
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        this.mapView.onResume();

    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        this.mapView.onDestroy();

    }
    @Override
    protected void onPause()
    {
        super.onPause();
        this.mapView.onPause();

    }

}
