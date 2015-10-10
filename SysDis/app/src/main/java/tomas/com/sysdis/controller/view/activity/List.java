package tomas.com.sysdis.controller.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;

import java.util.ArrayList;

import tomas.com.sysdis.R;
import tomas.com.sysdis.Models.objects.Student;
import tomas.com.sysdis.Models.objects.Teacher;
import tomas.com.sysdis.controller.view.adapter.ListenerAdapter;
import tomas.com.sysdis.controller.view.adapter.AdapterList;


public class List extends AppCompatActivity implements View.OnClickListener
{
    private FloatingActionButton returnButton, setAdapter;
    private Animation anaimation;
    private boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_list);
        //Instacia
        this.returnButton = (FloatingActionButton) this.findViewById(R.id.returnXML_List);
        this.setAdapter = (FloatingActionButton) this.findViewById(R.id.setListXML);
        this.returnButton.setOnClickListener(this);
        this.setAdapter.setOnClickListener(this);
        if(this.flag)
        {
            RecyclerView.Adapter myAdapter = new AdapterList(true, this.loadStudent());
            this.processRecyclerView(myAdapter);
        }else
        {
            this.flag = true;
            RecyclerView.Adapter myAdapter = new AdapterList(this.loadTeacher());
            this.processRecyclerView(myAdapter);
        }
        this.anaimation = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.animator_button);


    }
    @Override
    public void onClick(View view)
    {

        if(this.returnButton.getId() == view.getId())
        {
            this.startActivity(new Intent(this.getApplicationContext(), MainActivity.class));
        }else if(this.setAdapter.getId() == view.getId())
        {
            this.setAdapter.startAnimation(this.anaimation);
            if(this.flag)
            {
                this.flag = false;
                RecyclerView.Adapter myAdapter = new AdapterList(true, this.loadStudent());
                this.processRecyclerView(myAdapter);
            }else
            {
                this.flag = true;
                RecyclerView.Adapter myAdapter = new AdapterList(this.loadTeacher());
                this.processRecyclerView(myAdapter);
            }
        }
    }

    private void processRecyclerView(RecyclerView.Adapter myAdapter)
    {
        RecyclerView myRecycleView = (RecyclerView) this.findViewById(R.id.list_card_view);
        myRecycleView.setHasFixedSize(true);
        myRecycleView.setOnScrollListener(new ListenerAdapter()
        {
            @Override
            public void hide()
            {
                returnButton.animate().translationY(300).setInterpolator(new DecelerateInterpolator(2)).start();
                setAdapter.animate().translationY(300).setInterpolator(new DecelerateInterpolator(2)).start();
            }
            @Override
            public void show()
            {
                returnButton.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
                setAdapter.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();

            }
        });

        RecyclerView.LayoutManager myManager = new LinearLayoutManager(this);

        myRecycleView.setLayoutManager(myManager);
        myRecycleView.setAdapter(myAdapter);
    }
    /*
            Prueba
     */

    private ArrayList<Student> loadStudent()
    {
        ArrayList<Student> list = new ArrayList<>();

        list.add(new Student(11080867, "Tomas Yussef", "Galicia Guzman", "Ing. en Sistemas Computacionales", "Itesco", "9: A", "tomasyussef@gmial.com", "9211468521"));
        list.add(new Student(845215, "Katya Karina", "Hernandez Jimenez", "Ing. en Sistemas Computacionales", "Itesco", "9: A", "soyMongolitaf@gmial.com", "921124552"));
        list.add(new Student(10867, "Rafita", "Filomeno Saenz", "Ing. en Sistemas Computacionales", "Itesco", "9: A", "xxxx@gmial.com", "9218522586"));
        list.add(new Student(11080867, "Tomas Yussef", "Galicia Guzman", "Ing. en Sistemas Computacionales", "Itesco", "9: A", "tomasyussef@gmial.com", "9211468521"));
        list.add(new Student(845215, "Katya Karina", "Hernandez Jimenez", "Ing. en Sistemas Computacionales", "Itesco", "9: A", "soyMongolitaf@gmial.com", "921124552"));
        list.add(new Student(10867, "Rafita", "Filomeno Saenz", "Ing. en Sistemas Computacionales", "Itesco", "9: A", "xxxx@gmial.com", "9218522586"));
        list.add(new Student(11080867, "Tomas Yussef", "Galicia Guzman", "Ing. en Sistemas Computacionales", "Itesco", "9: A", "tomasyussef@gmial.com", "9211468521"));
        list.add(new Student(845215, "Katya Karina", "Hernandez Jimenez", "Ing. en Sistemas Computacionales", "Itesco", "9: A", "soyMongolitaf@gmial.com", "921124552"));
        list.add(new Student(10867, "Rafita", "Filomeno Saenz", "Ing. en Sistemas Computacionales", "Itesco", "9: A", "xxxx@gmial.com", "9218522586"));
        list.add(new Student(11080867, "Tomas Yussef", "Galicia Guzman", "Ing. en Sistemas Computacionales", "Itesco", "9: A", "tomasyussef@gmial.com", "9211468521"));
        list.add(new Student(845215, "Katya Karina", "Hernandez Jimenez", "Ing. en Sistemas Computacionales", "Itesco", "9: A", "soyMongolitaf@gmial.com", "921124552"));
        list.add(new Student(10867, "Rafita", "Filomeno Saenz", "Ing. en Sistemas Computacionales", "Itesco", "9: A", "xxxx@gmial.com", "9218522586"));
        return list;
    }
    private ArrayList<Teacher> loadTeacher()
    {
        ArrayList<Teacher> list = new ArrayList<>();
        list.add(new Teacher(11080867, "Tomas Yussef", "Galicia Guzman", "Ing. en Sistemas Computacionales", "9: A", "tomasyussef@gmial.com", "9211468521"));
        list.add(new Teacher(845215, "Katya Karina", "Hernandez Jimenez", "Ing. en Sistemas Computacionales", "9: A", "soyMongolitaf@gmial.com", "921124552"));
        list.add(new Teacher(10867, "Rafita", "Filomeno Saenz", "Ing. en Sistemas Computacionales", "9: A", "xxxx@gmial.com", "9218522586"));
        list.add(new Teacher(11080867, "Tomas Yussef", "Galicia Guzman", "Ing. en Sistemas Computacionales", "9: A", "tomasyussef@gmial.com", "9211468521"));
        list.add(new Teacher(11080867, "Tomas Yussef", "Galicia Guzman", "Ing. en Sistemas Computacionales", "9: A", "tomasyussef@gmial.com", "9211468521"));
        list.add(new Teacher(845215, "Katya Karina", "Hernandez Jimenez", "Ing. en Sistemas Computacionales", "9: A", "soyMongolitaf@gmial.com", "921124552"));
        list.add(new Teacher(10867, "Rafita", "Filomeno Saenz", "Ing. en Sistemas Computacionales", "9: A", "xxxx@gmial.com", "9218522586"));
        list.add(new Teacher(11080867, "Tomas Yussef", "Galicia Guzman", "Ing. en Sistemas Computacionales", "9: A", "tomasyussef@gmial.com", "9211468521"));
        list.add(new Teacher(11080867, "Tomas Yussef", "Galicia Guzman", "Ing. en Sistemas Computacionales", "9: A", "tomasyussef@gmial.com", "9211468521"));
        list.add(new Teacher(845215, "Katya Karina", "Hernandez Jimenez", "Ing. en Sistemas Computacionales", "9: A", "soyMongolitaf@gmial.com", "921124552"));
        list.add(new Teacher(10867, "Rafita", "Filomeno Saenz", "Ing. en Sistemas Computacionales", "9: A", "xxxx@gmial.com", "9218522586"));
        list.add(new Teacher(11080867, "Tomas Yussef", "Galicia Guzman", "Ing. en Sistemas Computacionales", "9: A", "tomasyussef@gmial.com", "9211468521"));
        return list;
    }
}
