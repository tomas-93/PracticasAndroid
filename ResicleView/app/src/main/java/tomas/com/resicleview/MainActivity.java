package tomas.com.resicleview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;




public class MainActivity extends AppCompatActivity
{
    private RecyclerView myRecylerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.myRecylerView = (RecyclerView) this.findViewById(R.id.listaXML);

        this.myRecylerView.setHasFixedSize(true);

        this.myLayoutManager = new LinearLayoutManager(this);

        this.myRecylerView.setLayoutManager(this.myLayoutManager);

        this.myAdapter = new AdapterRecleView();

        this.myRecylerView.setAdapter(this.myAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
