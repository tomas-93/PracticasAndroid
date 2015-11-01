package com.tomas.main.controller_view.fragments;

import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tomas.main.R;
import com.tomas.main.controller_service.Service_HTTPUrlConnection;

import java.util.Calendar;

/**
 * Created by Tomas on 29/10/2015.
 */
public class TestHTTPUrlConnection extends Fragment implements OnClickListener
{
    /*
            Tabs
     */
    private static final String ARGS_SELECTION_NUMBER = "selection_number";
    public static TestHTTPUrlConnection newInstance(int number)
    {
        TestHTTPUrlConnection testHTTPUrlConnectionm = new TestHTTPUrlConnection();
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_SELECTION_NUMBER, number);
        testHTTPUrlConnectionm.setArguments(bundle);
        return testHTTPUrlConnectionm;

    }
    /*
        End tabs
     */

    private Button button;
    private  TextView textViewTestHTTPUrlConnection;
    public TestHTTPUrlConnection()
    {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle saveInstace)
    {
        View rootView = inflater.inflate(R.layout.fragment_http, viewGroup, false);
        this.button = (Button) rootView.findViewById(R.id.buttonXML);
        this.button.setOnClickListener(this);
        this.textViewTestHTTPUrlConnection = (TextView) rootView.findViewById(R.id.textXML);
        return rootView;
    }
    @Override
    public void onClick(View view)
    {
        Calendar calendar = Calendar.getInstance();
        Intent intent = new Intent(this.getActivity().getApplicationContext(), Service_HTTPUrlConnection.class);
        intent.putExtra("hora", String.valueOf(calendar.get(Calendar.DATE)));
        intent.putExtra("segundos", String.valueOf(calendar.get(Calendar.SECOND)));
        intent.putExtra("message", "HOLA MUNDO");
        this.getActivity().startService(intent);
    }


}
