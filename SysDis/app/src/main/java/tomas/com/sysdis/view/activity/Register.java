package tomas.com.sysdis.view.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import tomas.com.sysdis.R;
import tomas.com.sysdis.view.fragments.Student;
import tomas.com.sysdis.view.fragments.Teacher;

public class Register extends AppCompatActivity
{

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private final Student student = new Student();
    private final Teacher teacher = new Teacher();
    private boolean flagTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_register);
        this.manager = this.getFragmentManager();
        this.transaction = this.manager.beginTransaction();
        if(this.flagTeacher)
        {
            this.transaction.replace(R.id.fragment_content_component, this.teacher);
            this.transaction.commit();
        }else
        {
            this.transaction.replace(R.id.fragment_content_component, this.student);
            this.transaction.commit();
        }
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
    public void onClickStudent(View view)
    {
        if(!this.student.isAdded())
        {
            this.flagTeacher = false;
            this.manager = this.getFragmentManager();
            this.transaction = this.manager.beginTransaction();
            this.transaction.replace(R.id.fragment_content_component, this.student);
            this.transaction.commit();
        }
    }

    public void onClickTeacher(View view)
    {
        if(!this.teacher.isAdded())
        {
            this.flagTeacher = true;
            this.manager = this.getFragmentManager();
            this.transaction = this.manager.beginTransaction();
            this.transaction.replace(R.id.fragment_content_component, this.teacher);
            this.transaction.commit();
        }
    }


}
