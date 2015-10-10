package tomas.com.sysdis.controller.view.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.content.Intent;

import tomas.com.sysdis.R;
import tomas.com.sysdis.controller.view.activity.MainActivity;


public class Student extends Fragment implements View.OnClickListener
{
    private Animation animation;
    private ImageButton plus, returnB;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student,container, false);
        this.animation = AnimationUtils.loadAnimation(this.getActivity().getApplicationContext(),
                                                        R.anim.animator_button);
        this.plus = (ImageButton) view.findViewById(R.id.plusXMLfragment);
        this.returnB = (ImageButton) view.findViewById(R.id.returnXMLfragment);
        this.returnB.setOnClickListener(this);
        this.plus.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View view)
    {
        if(view.getId() == this.plus.getId())
        {
            /*
                    Obtiene todos los datos de Edit Text para serutilizados en el controlador.del
                    network
             */
            this.plus.startAnimation(this.animation);
        }else
        {
            this.returnB.startAnimation(this.animation);
            this.startActivity(new Intent(this.getActivity().getApplicationContext(), MainActivity.class));
        }
    }
}
