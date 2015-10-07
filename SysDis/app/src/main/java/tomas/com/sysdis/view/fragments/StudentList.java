package tomas.com.sysdis.view.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import tomas.com.sysdis.R;
import tomas.com.sysdis.objects.Teacher;
import tomas.com.sysdis.objects.Student;
import tomas.com.sysdis.view.activity.MainActivity;
import tomas.com.sysdis.view.adapter.AdapterList_student;
import tomas.com.sysdis.view.adapter.AdapterList_teacher;

/**
 * Created by Tomas on 07/10/2015.
 */
public class StudentList extends Fragment implements View.OnClickListener
{
    private FloatingActionButton floatingActionButton;
    private ListView listView;
    @Override
    public void onCreate(Bundle saveInstace)
    {
        super.onCreate(saveInstace);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup content, Bundle saveInstace)
    {
        View view = inflater.inflate(R.layout.fragment_list_student, content, false);

        this.floatingActionButton = (FloatingActionButton) view.findViewById(R.id.returnXMLFragmentListStudent);
        this.floatingActionButton.setOnClickListener(this);
        this.listView = (ListView) view.findViewById(R.id.list_view_student);
        ArrayList<Student> list = new ArrayList<>();
        Student teacher1 = new Student(7254, "Tomas Yussef","Galicia Guzman", "Ing. Sistemas Computacionales", "Itesco","A","tomasyussef@gmail.com","9211468521");
        list.add(teacher1);
        Student teacher2 = new Student(7964, "Katya Karina", "Hernandez Juarez", "Ing. Sistemas Computacionales","B", "Mina","soyMongolita@gmail.com","9211468521");
        list.add(teacher2);
        Student teacher3 = new Student(3535, "Luis Roberto", "No me lose", "Ing. Electronica", "C", "Ori","no_se_que_poner@gmail.com","9211468521");
        list.add(teacher3);
        Student teacher4 = new Student(7254, "Tomas Yussef","Galicia Guzman", "Ing. Sistemas Computacionales","A", "Itesco","tomasyussef@gmail.com","9211468521");
        list.add(teacher4);
        Student teacher5 = new Student(7964, "Katya Karina", "Hernandez Juarez", "Ing. Sistemas Computacionales","B", "Mina","soyMongolita@gmail.com","9211468521");
        list.add(teacher5);
        Student teacher6 = new Student(3535, "Luis Roberto", "No me lose", "Ing. Electronica", "C", "Ori","no_se_que_poner@gmail.com","9211468521");
        list.add(teacher6);
        Student teacher7 = new Student(7254, "Tomas Yussef","Galicia Guzman", "Ing. Sistemas Computacionales","A", "Itesco","tomasyussef@gmail.com","9211468521");
        list.add(teacher7);
        Student teacher8 = new Student(7964, "Katya Karina", "Hernandez Juarez", "Ing. Sistemas Computacionales","B", "Mina","soyMongolita@gmail.com","9211468521");
        list.add(teacher8);
        Student teacher9 = new Student(3535, "Luis Roberto", "No me lose", "Ing. Electronica", "C", "Ori","no_se_que_poner@gmail.com","9211468521");
        list.add(teacher9);
        AdapterList_student adapter = new AdapterList_student(this.getActivity(), list);
        this.listView.setAdapter(adapter);
        return view;
    }
    @Override
    public void onClick(View view)
    {
        this.startActivity(new Intent(this.getActivity().getApplicationContext(), MainActivity.class));
    }

}
