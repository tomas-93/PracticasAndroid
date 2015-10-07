package tomas.com.fragmentexample;

import android.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;

/**
 * Created by Tomas on 04/10/2015.
 */
public class Fragment_Uno extends Fragment
{
    public View onCreateView(LayoutInflater inflater, ViewGroup conteiner, Bundle bundle)
    {
        return inflater.inflate(R.layout.fragment_uno, conteiner, false);
    }
}
