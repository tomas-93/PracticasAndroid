package mensajeria.tomas.com.practica2.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mensajeria.tomas.com.practica2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Maps extends Fragment {


    public Maps() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }


}
