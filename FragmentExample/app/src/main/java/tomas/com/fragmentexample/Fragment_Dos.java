package tomas.com.fragmentexample;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;


/**
 * Created by Tomas on 04/10/2015.
 */
public class Fragment_Dos extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle)
    {
        return inflater.inflate(R.layout.fragment_dos, container, false);
    }
}
