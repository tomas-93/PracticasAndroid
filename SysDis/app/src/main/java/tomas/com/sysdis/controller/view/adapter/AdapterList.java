package tomas.com.sysdis.controller.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.ArrayList;

import tomas.com.sysdis.R;
import tomas.com.sysdis.Models.objects.Student;
import tomas.com.sysdis.Models.objects.Teacher;


/**
 * Created by Tomas on 04/10/2015.
 */
public class AdapterList extends  RecyclerView.Adapter <AdapterList.AdapterObject>
{
    private ArrayList<Student> listStudent;
    private ArrayList<Teacher> listTeacher;
    private boolean flag;
    public AdapterList(boolean flag, ArrayList<Student> list)
    {
        this.flag = flag;
        this.listStudent = list;

    }
    public AdapterList(ArrayList<Teacher> list)
    {
        this.flag = false;
        this.listTeacher = list;
    }

    @Override
    public AdapterList.AdapterObject onCreateViewHolder(ViewGroup content, int viewType)
    {
        View view = LayoutInflater.from(content.getContext()).inflate(R.layout.layout_list, content, false);
        AdapterObject viewHolderStudent = new AdapterObject(view);
        return viewHolderStudent;
    }
    @Override
    public void onBindViewHolder(AdapterObject adapter, int position)
    {
        if(this.flag) this.loadStudent(adapter, position);
        else this.loadTeacher(adapter, position);
    }
    @Override
    public int getItemCount()
    {
        if(this.flag)return this.listStudent.size();
        else return this.listTeacher.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
    private void loadStudent(AdapterObject adapter, int position)
    {
        String var = this.listStudent.get(position).getNameStudent() +" "+this.listStudent.get(position).getLastName();
        adapter.level1.setText(String.valueOf(this.listStudent.get(position).getNumber()));
        adapter.level2.setText(var);
        var = this.listStudent.get(position).getRace() + " en el " + this.listStudent.get(position).getSchool() + " "+this.listStudent.get(position).getLevel();
        adapter.level3.setText(var);
        var = this.listStudent.get(position).getEmail() + " " + this.listStudent.get(position).getPhone();
        adapter.level4.setText(var);
        adapter.imageView.setImageResource(R.drawable.ic_face_black_48dp);
    }
    private void loadTeacher(AdapterObject adapter, int position)
    {
        String var = this.listTeacher.get(position).getNameTeacher() +" "+this.listTeacher.get(position).getLastName();
        adapter.level1.setText(String.valueOf(this.listTeacher.get(position).getNumber()));
        adapter.level2.setText(var);
        var = this.listTeacher.get(position).getRace();
        adapter.level3.setText(var);
        var = this.listTeacher.get(position).getEmail() + " " + this.listTeacher.get(position).getPhone();
        adapter.level4.setText(var);
        adapter.imageView.setImageResource(R.drawable.ic_android_black_48dp);
    }
    /*
    * Class Adapter.......
    * */
    public static class AdapterObject extends RecyclerView.ViewHolder
    {
        public TextView level1, level2, level3, level4;
        public ImageView imageView;
        public AdapterObject(View view)
        {
            super(view);
            this.level1 = (TextView) view.findViewById(R.id.level1XML);
            this.level2 = (TextView) view.findViewById(R.id.level2XML);
            this.level3 = (TextView) view.findViewById(R.id.level3XML);
            this.level4 = (TextView) view.findViewById(R.id.level4XML);
            this.imageView = (ImageView) view.findViewById(R.id.imageXML);

        }
    }
}