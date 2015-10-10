package tomas.com.resicleview;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tomas on 07/10/2015.
 */
public class AdapterRecleView extends RecyclerView.Adapter<AdapterRecleView.ViewHolder>
{
    private ArrayList<String> list;

    public AdapterRecleView()
    {
        this.list = new ArrayList<>();
        list.add("Tomas Yussef Galicia Guzman");
        list.add("Tomas Yussef Galicia Guzman");

        list.add("Tomas Yussef Galicia Guzman");

        list.add("Tomas Yussef Galicia Guzman");

        list.add("Tomas Yussef Galicia Guzman");

        list.add("Tomas Yussef Galicia Guzman");

        list.add("Tomas Yussef Galicia Guzman");

        list.add("Tomas Yussef Galicia Guzman");

        list.add("Tomas Yussef Galicia Guzman");

        list.add("Tomas Yussef Galicia Guzman");

        list.add("Tomas Yussef Galicia Guzman");
        list.add("Tomas Yussef Galicia Guzman");

        list.add("Tomas Yussef Galicia Guzman");

        list.add("Tomas Yussef Galicia Guzman");


    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView myText;
        public ViewHolder(View v)
        {
            super(v);
            this.myText = (TextView) itemView.findViewById(R.id.textXML);
        }
    }

    @Override
    public AdapterRecleView.ViewHolder onCreateViewHolder(ViewGroup content, int viewType)
    {
        View view = LayoutInflater.from(content.getContext()).inflate(R.layout.card_view, content, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.myText.setText(this.list.get(position));
    }

    @Override
    public int getItemCount()
    {
        return this.list.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
