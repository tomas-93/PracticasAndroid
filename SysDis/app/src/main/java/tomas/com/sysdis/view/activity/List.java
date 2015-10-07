package tomas.com.sysdis.view.activity;

import android.app.FragmentTransaction;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import tomas.com.sysdis.R;
import tomas.com.sysdis.view.fragments.TeacherList;
import tomas.com.sysdis.view.fragments.StudentList;


public class List extends AppCompatActivity implements View.OnClickListener
{
    private StudentList studentList;
    private TeacherList teacherList;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Button studentButton;
    private Button teacherButton;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_list);
        //instacia
        this.studentList = new StudentList();
        this.teacherList = new TeacherList();
        this.studentButton = (Button) this.findViewById(R.id.studentListXML);
        this.teacherButton = (Button) this.findViewById(R.id.teacherListXML);
        this.studentButton.setOnClickListener(this);
        this.teacherButton.setOnClickListener(this);
        //set fragment
        this.fragmentManager = this.getFragmentManager();
        this.fragmentTransaction = this.fragmentManager.beginTransaction();
        if(this.flag)
        {
            this.fragmentTransaction.replace(R.id.fragment_list, new TeacherList());
            this.fragmentTransaction.commit();
        }else
        {
            this.fragmentTransaction.replace(R.id.fragment_list, new TeacherList());
            this.fragmentTransaction.commit();
        }

    }
    @Override
    public void onClick(View view)
    {

        if(view.getId() == this.studentButton.getId())
        {

            this.flag = false;
            if(!this.studentList.isAdded())
            {
                Log.e("Fragment","Student esta agregado");
                this.fragmentManager = this.getFragmentManager();
                this.fragmentTransaction = this.fragmentManager.beginTransaction();
                this.fragmentTransaction.remove(this.studentList);
                this.fragmentTransaction.addToBackStack(null);
                this.fragmentTransaction.commit();
                if(this.teacherList.isRemoving())
                {
                    Log.e("Fragment","Student se elimino");
                    this.flag = true;
                    this.fragmentManager = this.getFragmentManager();
                    this.fragmentTransaction = this.fragmentManager.beginTransaction();
                    this.fragmentTransaction.add(R.id.fragment_list, this.teacherList);
                    this.fragmentTransaction.addToBackStack(null);
                    this.fragmentTransaction.commit();
                    Log.e("Fragment", "Teacher se agrego");

                }
            }

        }else if(view.getId() == this.teacherButton.getId())
        {
            if(!this.teacherList.isAdded())
            {
                Log.e("Fragment","Teacher esta agregado");

                this.fragmentManager = this.getFragmentManager();
                this.fragmentTransaction = this.fragmentManager.beginTransaction();
                this.fragmentTransaction.remove(this.teacherList);
                this.fragmentTransaction.addToBackStack(null);
                this.fragmentTransaction.commit();
                if(this.studentList.isRemoving())
                {
                    Log.e("Fragment","Teacher se elimino");

                    this.flag = true;
                    this.fragmentManager = this.getFragmentManager();
                    this.fragmentTransaction = this.fragmentManager.beginTransaction();
                    this.fragmentTransaction.add(R.id.fragment_list, this.studentList);
                    this.fragmentTransaction.addToBackStack(null);
                    this.fragmentTransaction.commit();
                    Log.e("Fragment", "Student se agrego");


                }
            }
        }
    }
}
