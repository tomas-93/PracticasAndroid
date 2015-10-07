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
public class TeacherList extends Fragment implements View.OnClickListener
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
        View view = inflater.inflate(R.layout.fragment_list_teacher, content, false);

        this.floatingActionButton = (FloatingActionButton) view.findViewById(R.id.returnXMLFragmentListStudent);
        this.floatingActionButton.setOnClickListener(this);
        this.listView = (ListView) view.findViewById(R.id.list_view_student);
        ArrayList<Teacher> list = new ArrayList<>();
        Teacher teacher1 = new Teacher(7254, "Tomas Yussef","Galicia Guzman", "Ing. Sistemas Computacionales","A","tomasyussef@gmail.com","9211468521");
        list.add(teacher1);
        Teacher teacher2 = new Teacher(7964, "Katya Karina", "Hernandez Juarez", "Ing. Sistemas Computacionales","B","soyMongolita@gmail.com","9211468521");
        list.add(teacher2);
        Teacher teacher3 = new Teacher(3535, "Luis Roberto", "No me lose", "Ing. Electronica", "C","no_se_que_poner@gmail.com","9211468521");
        list.add(teacher3);
        Teacher teacher4 = new Teacher(7254, "Tomas Yussef","Galicia Guzman", "Ing. Sistemas Computacionales","A","tomasyussef@gmail.com","9211468521");
        list.add(teacher4);
        Teacher teacher5 = new Teacher(7964, "Katya Karina", "Hernandez Juarez", "Ing. Sistemas Computacionales","B","soyMongolita@gmail.com","9211468521");
        list.add(teacher5);
        Teacher teacher6 = new Teacher(3535, "Luis Roberto", "No me lose", "Ing. Electronica", "C","no_se_que_poner@gmail.com","9211468521");
        list.add(teacher6);
        Teacher teacher7 = new Teacher(7254, "Tomas Yussef","Galicia Guzman", "Ing. Sistemas Computacionales","A","tomasyussef@gmail.com","9211468521");
        list.add(teacher7);
        Teacher teacher8 = new Teacher(7964, "Katya Karina", "Hernandez Juarez", "Ing. Sistemas Computacionales","B","soyMongolita@gmail.com","9211468521");
        list.add(teacher8);
        Teacher teacher9 = new Teacher(3535, "Luis Roberto", "No me lose", "Ing. Electronica", "C","no_se_que_poner@gmail.com","9211468521");
        list.add(teacher9);
        AdapterList_teacher adapter = new AdapterList_teacher(this.getActivity(), list);
        this.listView.setAdapter(adapter);
        return view;
    }
    @Override
    public void onClick(View view)
    {
        this.startActivity(new Intent(this.getActivity().getApplicationContext(), MainActivity.class));
    }

}
