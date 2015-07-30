package mensajeria.tomas.com.practica2.models.sql;

import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.ContentValues;
import android.content.Context;

import android.database.sqlite.SQLiteConstraintException;
import android.widget.Toast;

/**
 * Created by Tomas on 25/07/2015.
 */
public class ManagerSQL
{
    private DataBase database;
    private SQLiteDatabase managerDataBase;

    public ManagerSQL (Context context)
    {
        this.database = new DataBase(context);
        this.managerDataBase = this.database.getWritableDatabase();
    }

    public long insertIntoTableMessage(int idMessage, String date, String latitude, String longitude,
                                       String altitude)
    {
        final ContentValues VALUES = new ContentValues();
        VALUES.put(SchemaContract.COLUMN_NAME_ID_MESSAGE,idMessage);
        VALUES.put(SchemaContract.COLUMN_NAME_DATE,date);
        VALUES.put(SchemaContract.COLUMN_NAME_LATITUDE,latitude);
        VALUES.put(SchemaContract.COLUMN_NAME_LONGITUDE, longitude);
        VALUES.put(SchemaContract.COLUMN_NAME_ALTITUDE,altitude);
        return this.managerDataBase.insert(SchemaContract.TABLE_NAME_MESSAGE,
                                    SchemaContract.COLUMN_NAME_NULLABLE,
                                    VALUES);

    }

    public long insertIntoTableConfig(int idConfig, String phone, String host)
    {
        this.managerDataBase.execSQL(SchemaContract.DELETE_TABLE_CONFIG);
        this.managerDataBase.execSQL(SchemaContract.CREATE_TABLE_CONFIG);
        final ContentValues VALUES = new ContentValues();
        VALUES.put(SchemaContract.COLUMN_NAME_ID_CONFIG, idConfig);
        VALUES.put(SchemaContract.COLUMN_NAME_PHONE, phone);
        VALUES.put(SchemaContract.COLUMN_NAME_HOST, host);
        return this.managerDataBase.insert(SchemaContract.TABLE_NAME_CONFIG,
                SchemaContract.COLUMN_NAME_NULLABLE,
                VALUES);
    }

    public Cursor readDateIntoTableConfig(String column, String args [])
    {
        final String COLUMNS [] = {
                                    SchemaContract.COLUMN_NAME_ID_CONFIG,
                                    SchemaContract.COLUMN_NAME_PHONE,
                                    SchemaContract.COLUMN_NAME_HOST
                                 };
        String where = "";
        if(column.equalsIgnoreCase(SchemaContract.COLUMN_NAME_ID_CONFIG))
        {
            where = SchemaContract.COLUMN_NAME_ID_CONFIG + "=?";
        }else if(column.equalsIgnoreCase(SchemaContract.COLUMN_NAME_HOST))
        {
            where = SchemaContract.COLUMN_NAME_HOST + "=?";
        }else if(column.equalsIgnoreCase(SchemaContract.COLUMN_NAME_PHONE))
        {
            where = SchemaContract.COLUMN_NAME_PHONE + "=?";
        }
        return this.managerDataBase.query(SchemaContract.TABLE_NAME_CONFIG,
                COLUMNS,
                where,
                args,
                null,
                null,
                null);

    }

    public Cursor readDateIntoTableMessage(String column, String args[])
    {
        final String COLUMNS [] ={
                                   SchemaContract.COLUMN_NAME_ID_MESSAGE,
                                   SchemaContract.COLUMN_NAME_DATE,
                                   SchemaContract.COLUMN_NAME_LATITUDE,
                                   SchemaContract.COLUMN_NAME_LONGITUDE,
                                   SchemaContract.COLUMN_NAME_ALTITUDE,
                                 };
        String where = "";
        if(column.equalsIgnoreCase(SchemaContract.COLUMN_NAME_ID_MESSAGE))
        {
            where = SchemaContract.COLUMN_NAME_ID_MESSAGE + " =?";
        }else if(column.equalsIgnoreCase(SchemaContract.COLUMN_NAME_DATE))
        {
            where = SchemaContract.COLUMN_NAME_DATE + " =?";
        }else if(column.equalsIgnoreCase(SchemaContract.COLUMN_NAME_LATITUDE))
        {
            where = SchemaContract.COLUMN_NAME_LATITUDE + " =?";
        }else if(column.equalsIgnoreCase(SchemaContract.COLUMN_NAME_LONGITUDE))
        {
            where = SchemaContract.COLUMN_NAME_ALTITUDE + " =?";
        }
        return this.managerDataBase.query(SchemaContract.TABLE_NAME_MESSAGE,
                                          COLUMNS,
                                          where,
                                          args,
                                          null,
                                          null,
                                          null);
    }


    public void updateTableMessage(String newValue, int on)
    {
        final ContentValues VALUES = new ContentValues();
        VALUES.put(SchemaContract.TABLE_NAME_MESSAGE, newValue);

        final String SELECT_COLUMN = SchemaContract.COLUMN_NAME_ID_MESSAGE  + "LIKE ?";
        final String SELECT_ARGS [] = {String.valueOf(on)};
        this.managerDataBase.update(SchemaContract.TABLE_NAME_MESSAGE,
                                    VALUES,
                                    SELECT_COLUMN,
                                    SELECT_ARGS);
    }

    public void updateTableConfig(String newValue, int on)
    {
        final ContentValues VALUES = new ContentValues();
        VALUES.put(SchemaContract.TABLE_NAME_CONFIG, newValue);

        final  String SELECT_COLUMN = SchemaContract.COLUMN_NAME_ID_CONFIG + " LIKE ?";
        final  String SELECT_ARGS [] = {String.valueOf(on)};

        this.managerDataBase.update(SchemaContract.TABLE_NAME_CONFIG,
                VALUES,
                SELECT_COLUMN,
                SELECT_ARGS);
    }
    public String [] readCursor(Cursor elements)
    {
        elements.moveToFirst();
        String phone = elements.getString(elements.getColumnIndexOrThrow(SchemaContract.COLUMN_NAME_PHONE));
        String host = elements.getString(elements.getColumnIndexOrThrow(SchemaContract.COLUMN_NAME_HOST));
        String args [] = {phone, host};
        elements.close();
        return args;
    }


}
