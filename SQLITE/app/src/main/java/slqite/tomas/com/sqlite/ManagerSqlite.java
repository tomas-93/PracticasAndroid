package slqite.tomas.com.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

/**
 * Created by Tomas on 22/08/2015.
 */
public class ManagerSqlite
{
    private Sqlite sql;
    private SQLiteDatabase managerDatabase;

    public ManagerSqlite(Context context)
    {
        this.sql = new Sqlite(context);
        this.managerDatabase = this.sql.getWritableDatabase();
    }
    public long insertIntoTablePrueba(int id, String text)
    {
        ContentValues value = new ContentValues();
        value.put(Schema._ID, id);
        value.put(Schema.COLUMN_NAME_TEXT, text);
        return this.managerDatabase.insert(Schema.TABLE_PRUBEA, null, value);
    }
    public Cursor readDataIntoTablePrueba()
    {
        final String COLUMNS [] ={
                                    Schema._ID,
                                    Schema.COLUMN_NAME_TEXT
                                 };
        return this.managerDatabase.query(
                Schema.TABLE_PRUBEA,
                COLUMNS,
                null,
                null,
                null,
                null,
                null);
    }
    public void removeDataForTablePrueba(String arg)
    {
        final String [] ARGS  = { arg };
        this.managerDatabase.delete(Schema.TABLE_PRUBEA, Schema._ID + "=?", ARGS);
    }

    public Cursor getNextID()
    {
        return this.managerDatabase.rawQuery(Schema.MAX_ID,null);
    }
    public void newOnCreate()
    {
        this.sql.onUpgrade(this.managerDatabase, 1, 2);
    }

}
