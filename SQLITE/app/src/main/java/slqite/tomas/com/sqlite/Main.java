package slqite.tomas.com.sqlite;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


public class Main extends Activity implements OnClickListener
{
    private Button newData, deleteData, destroyProcess;
    private TextView textView;
    private ManagerSqlite sqlite;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //instacia
        this.newData = (Button) this.findViewById(R.id.newDataXML);

        this.deleteData = (Button) this.findViewById(R.id.deleteDataXML);

        this.destroyProcess = (Button) this.findViewById(R.id.deleteProcessXML);

        this.textView = (TextView) this.findViewById(R.id.textXML);
        //eventos

        this.newData.setOnClickListener(this);

        this.deleteData.setOnClickListener(this);

        this.destroyProcess.setOnClickListener(this);

        //sqlite

        this.sqlite = new ManagerSqlite(this.getApplicationContext());


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == this.newData.getId())
        {
            this.sqlite.newOnCreate();
            for(int cont = 0; cont < 100; cont++)
            {
                this.sqlite.insertIntoTablePrueba(cont,"hola mundo" +cont);
                Log.w("Activity", String.valueOf("hola mundo" +cont));
            }
        }else if(view.getId() == this.deleteData.getId())
        {
            Cursor cursor = this.sqlite.readDataIntoTablePrueba();
            cursor.moveToFirst();
            String text ="";
            do
            {
                text += cursor.getInt(cursor.getColumnIndexOrThrow(Schema._ID)) +" "+ cursor.getString(cursor.getColumnIndexOrThrow(Schema.COLUMN_NAME_TEXT)) +"\n";
            }while(cursor.moveToNext());
            this.textView.setText(text);

        }else if(view.getId() == this.destroyProcess.getId())
        {
            Cursor cursor = this.sqlite.getNextID();
            cursor.moveToFirst();
            String text = "";
            do
            {
                text += cursor.getInt(cursor.getColumnIndexOrThrow(Schema._ID)) + "\n";
        }while(cursor.moveToNext());
            this.textView.setText(text);
        }
    }

}
