package mensajeria.tomas.com.practica2.models.sql;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;


/**
 * Created by Tomas on 19/07/2015.
 */
public class DataBase extends SQLiteOpenHelper
{
    //Database
    private static final String NAME_DATA_BASE = "CONFIG_AND_MESSAGE.db";
    private static final int VERSION = 1;

    public DataBase(Context context)
    {
        super(context, NAME_DATA_BASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        database.execSQL(SchemaContract.CREATE_TABLE_CONFIG);
        database.execSQL(SchemaContract.CREATE_TABLE_MESSAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int n, int m)
    {
        database.execSQL(SchemaContract.DELETE_TABLE_CONFIG);
        database.execSQL(SchemaContract.DELETE_TABLE_MESSAGE);
        this.onCreate(database);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }



}
