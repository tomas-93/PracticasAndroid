package gps.tomas.com.gps.models.sql;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
/**
 * Created by Tomas on 17/09/2015.
 */
public class SQLite extends SQLiteOpenHelper
{
    private static final String NAME_DATA_BASE ="GPS.db";
    private static final int VERSION = 1;

    public SQLite(Context context)
    {
        super(context, NAME_DATA_BASE, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase database)
    {
        database.execSQL(Schema.CREATE_TABLE_CONFIG);
        database.execSQL(Schema.CREATE_TABLE_LOCATION);

    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldV, int newV)
    {
        database.execSQL(Schema.DELETE_TABLE_LOCATION);
        database.execSQL(Schema.DELTE_TABLE_CONFIG);
        this.onCreate(database);

    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldV, int newV)
    {
        onUpgrade(db, oldV, newV);
    }
}
