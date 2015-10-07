package tomas.com.sysdis.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tomas.com.sysdis.R;
import tomas.com.sysdis.objects.Teacher;

/**
 * Created by Tomas on 04/10/2015.
 */
public class AdapterList_teacher extends ArrayAdapter
{
    private ImageView image;
    private TextView level1, level2, level3, level4;
    private Activity context;
    private ArrayList<Teacher> list;
    public AdapterList_teacher(Activity context, ArrayList<Teacher> list)
    {
        super(context, R.layout.layout_list_view, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_list_view, null);

        this.image = (ImageView) view.findViewById(R.id.imageXML);
        this.image.setImageResource(R.drawable.ic_android_black_48dp);

        this.level1 = (TextView) view.findViewById(R.id.level1XML);
        this.level1.setText(this.list.get(position).getNameTeacher());

        this.level2 = (TextView) view.findViewById(R.id.level2XML);
        String cos = "Num. "+ String.valueOf(this.list.get(position).getNumber());
        this.level2.setText(cos);

        this.level3 = (TextView) view.findViewById(R.id.level3XML);
        this.level3.setText(this.list.get(position).getRace());

        this.level4 = (TextView) view.findViewById(R.id.level4XML);
        cos = this.list.get(position).getEmail() + " " + this.list.get(position).getPhone();
        this.level4.setText(cos);


        return view;
    }
}
