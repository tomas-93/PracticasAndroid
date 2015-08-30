package slqite.tomas.com.sqlite;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
/**
 * Created by Tomas on 22/08/2015.
 */
public class Sqlite extends SQLiteOpenHelper
{
    private static final String NAME = "sqlite.db";
    private static final int VERSION = 1;
    public Sqlite (Context context)
    {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(Schema.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(Schema.DELETE_TABLE);
        this.onCreate(db);
    }



}
