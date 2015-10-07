package gps.tomas.com.gps.models.sql;

import android.content.Context;
import android.content.ContentValues;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Tomas on 12/09/2015.
 */
public class ManagerSQL
{
    private SQLite sqlite;
    private SQLiteDatabase manager;


    ManagerSQL(Context context)
    {
        this.sqlite = new SQLite(context);
        this.manager = this.sqlite.getReadableDatabase();
    }
    public void insertIntoToTableConfig(int id, String host, String timer, String process)
    {
        this.manager.execSQL(Schema.DELTE_TABLE_CONFIG);
        this.manager.execSQL(Schema.CREATE_TABLE_CONFIG);
        final ContentValues VALUES = new ContentValues();
        VALUES.put(Schema._ID, id);
        VALUES.put(Schema.COLUMN_HOST, host);
        VALUES.put(Schema.COLUMN_TIME, timer);
        VALUES.put(Schema.COLUMN_PROCESS, process);
        this.manager.insert(Schema.TABLE_NAME_CONFIG, Schema.COLUMN_NULLABLE, VALUES);
    }
    public void insertIntoToTableLocation(int id, String latitude, String longitude, String altitude, String speed, String acc)
    {
        final ContentValues VALUES = new ContentValues();
        VALUES.put(Schema.COLUMN_LATITUDE, latitude);
        VALUES.put(Schema.COLUMN_LONGITUDE, longitude);
        VALUES.put(Schema.COLUMN_ALTITUDE, altitude);
        VALUES.put(Schema.COLUMN_SPEED, speed);
        VALUES.put(Schema.COLUMN_ACC, acc);
        this.manager.insert(Schema.TABLE_NAME_LOCATION, Schema.COLUMN_NULLABLE, VALUES);
    }
    public void readDateIntoTableConfig()
    {
        final String COLUMNS [] = {
                                    Schema._ID,
                                    Schema.COLUMN_HOST,
                                    Schema.COLUMN_TIME,
                                    Schema.COLUMN_PROCESS
                                 };
        this.manager.query(Schema.TABLE_NAME_CONFIG,
                           COLUMNS, null, null, null, null, null);
    }
    public void readDateIntoTableLocation()
    {
        final String COLUMNS [] = {
                                    Schema._ID,
                                    Schema.COLUMN_LATITUDE,
                                    Schema.COLUMN_LONGITUDE,
                                    Schema.COLUMN_ALTITUDE,
                                    Schema.COLUMN_SPEED,
                                    Schema.COLUMN_ACC
                                  };
        this.manager.query(Schema.TABLE_NAME_LOCATION,
                           COLUMNS, null, null, null, null, null);

    }
    public void updateDateTableConfig(String newValue, int id, String column)
    {
        final ContentValues VALUES = new  ContentValues();
        final String SELECT_COLUMN = Schema._ID + " LIKE ?";
        final String SELECT_ARGS [] = {String.valueOf(id)};
        VALUES.put(column, newValue);
        this.manager.update(Schema.TABLE_NAME_CONFIG, VALUES, SELECT_COLUMN, SELECT_ARGS);
    }
    public void updateDateTableLocation(String newValue, int id, String column)
    {
        final ContentValues VALUES = new ContentValues();
        final String SELECT_COLUMN = Schema._ID + " LIKE ?";
        final String SELECT_ARGS [] = {String.valueOf(id)};
        VALUES.put(column, newValue);
        this.manager.update(Schema.TABLE_NAME_LOCATION, VALUES, SELECT_COLUMN, SELECT_ARGS);
    }
    public void removeDate(int id)
    {
        final String [] ARGS = {String.valueOf(id)};
        this.manager.delete(Schema.TABLE_NAME_LOCATION, Schema._ID + "=?", ARGS);
    }
    public int getId()
    {
        Cursor cursor = this.manager.rawQuery(Schema.MAX_ID_TABLE_LOCATION, null);
        cursor.moveToFirst();
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(Schema._ID)) + 1;
        cursor.close();
        return id;
    }
}
