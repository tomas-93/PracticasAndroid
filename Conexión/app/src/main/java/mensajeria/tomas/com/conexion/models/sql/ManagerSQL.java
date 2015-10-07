package mensajeria.tomas.com.conexion.models.sql;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import android.content.ContentValues;
import android.content.Context;

import android.util.Log;

import java.util.ArrayList;

import javax.xml.validation.Schema;

import mensajeria.tomas.com.conexion.models.object.Config;
import mensajeria.tomas.com.conexion.models.object.Message;
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

    public long insertIntoTableMessage(int idMessage, String phone, String date, String message)
    {
        final ContentValues VALUES = new ContentValues();
        VALUES.put(SchemaContract.COLUMN_NAME_ID_MESSAGE,idMessage);
        VALUES.put(SchemaContract.COLUMN_NAME_PHONE, phone);
        VALUES.put(SchemaContract.COLUMN_NAME_DATE, date);
        VALUES.put(SchemaContract.COLUMN_NAME_MESSAGE, message);
        return this.managerDataBase.insert(SchemaContract.TABLE_NAME_MESSAGE,
                                    SchemaContract.COLUMN_NAME_NULLABLE,
                                    VALUES);

    }

    public long insertIntoTableConfig(int idConfig, String host,String name, String status)
    {
        this.managerDataBase.execSQL(SchemaContract.DELETE_TABLE_CONFIG);
        this.managerDataBase.execSQL(SchemaContract.CREATE_TABLE_CONFIG);
        final ContentValues VALUES = new ContentValues();
        VALUES.put(SchemaContract.COLUMN_NAME_ID_CONFIG, idConfig);
        VALUES.put(SchemaContract.COLUMN_NAME_HOST, host);
        VALUES.put(SchemaContract.COLUMN_NAME_NAME, name);
        VALUES.put(SchemaContract.COLUMN_NAME_STATUS, status);
        return this.managerDataBase.insert(SchemaContract.TABLE_NAME_CONFIG,
                SchemaContract.COLUMN_NAME_NULLABLE,
                VALUES);
    }

    public Cursor readDateIntoTableConfig(String column, String args [])
    {
        final String COLUMNS [] = {
                                    SchemaContract.COLUMN_NAME_ID_CONFIG,
                                    SchemaContract.COLUMN_NAME_HOST,
                                    SchemaContract.COLUMN_NAME_NAME,
                                    SchemaContract.COLUMN_NAME_STATUS
                                 };
        String where = "";
        Cursor cursor = null;
        if(column.equalsIgnoreCase(SchemaContract.COLUMN_NAME_ID_CONFIG))
        {
            where = SchemaContract.COLUMN_NAME_ID_CONFIG + "=?";
            cursor = this.managerDataBase.query(SchemaContract.TABLE_NAME_CONFIG,
                    COLUMNS,
                    where,
                    args,
                    null,
                    null,
                    null);
        }
        return cursor;
    }
    public Cursor readDateIntoTableConfig()
    {
        final String COLUMNS [] = {
                SchemaContract.COLUMN_NAME_ID_CONFIG,
                SchemaContract.COLUMN_NAME_HOST,
                SchemaContract.COLUMN_NAME_NAME,
                SchemaContract.COLUMN_NAME_STATUS
        };
        return this.managerDataBase.query(SchemaContract.TABLE_NAME_CONFIG,
                    COLUMNS,
                    null,
                    null,
                    null,
                    null,
                    null);
    }

    public Cursor readDateIntoTableMessage()
    {
        final String COLUMNS [] ={
                                   SchemaContract.COLUMN_NAME_ID_MESSAGE,
                                   SchemaContract.COLUMN_NAME_PHONE,
                                   SchemaContract.COLUMN_NAME_DATE,
                                   SchemaContract.COLUMN_NAME_MESSAGE,
                                 };
        return this.managerDataBase.query(
                    SchemaContract.TABLE_NAME_MESSAGE,
                    COLUMNS,
                    null,
                    null,
                    null,
                    null,
                    null);

    }


    public void updateTableMessage(String newValue, int on)
    {
        final ContentValues VALUES = new ContentValues();
        VALUES.put(SchemaContract.COLUMN_NAME_ID_MESSAGE, newValue);

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
        VALUES.put(SchemaContract.COLUMN_NAME_ID_CONFIG, newValue);

        final  String SELECT_COLUMN = SchemaContract.COLUMN_NAME_ID_CONFIG + " LIKE ?";
        final  String SELECT_ARGS [] = {String.valueOf(on)};

        this.managerDataBase.update(SchemaContract.TABLE_NAME_CONFIG,
                VALUES,
                SELECT_COLUMN,
                SELECT_ARGS);
    }
    public Config readCursorConfig(Cursor element)
    {
        Config config = new Config();
        element.moveToFirst();
        config.setHost(element.getString(element.getColumnIndexOrThrow(SchemaContract.COLUMN_NAME_HOST)));
        config.setStatus(element.getString(element.getColumnIndexOrThrow(SchemaContract.COLUMN_NAME_STATUS)));
        config.setName(element.getString(element.getColumnIndexOrThrow(SchemaContract.COLUMN_NAME_NAME)));
        element.close();
        return config;
    }
    public Message readCursorMessage(Cursor element)
    {
        Message message = new Message();
        element.moveToFirst();
        message.setId(element.getInt(element.getColumnIndexOrThrow(SchemaContract.COLUMN_NAME_ID_MESSAGE)));
        message.setPhone(element.getString(element.getColumnIndexOrThrow(SchemaContract.COLUMN_NAME_PHONE)));
        message.setDate(element.getString(element.getColumnIndexOrThrow(SchemaContract.COLUMN_NAME_DATE)));
        message.setMessage(element.getString(element.getColumnIndexOrThrow(SchemaContract.COLUMN_NAME_MESSAGE)));
        element.close();
        return message;
    }

    public void removeMessageData(int id)
    {
        final String [] ARGS  = { String.valueOf(id) };
        this.managerDataBase.delete(SchemaContract.TABLE_NAME_MESSAGE, SchemaContract.COLUMN_NAME_ID_MESSAGE + "=?", ARGS);
    }


    public int getIdMessage()
    {
        Cursor cursor = this.managerDataBase.rawQuery(SchemaContract.MAX_ID_TABLE_MESSAGE, null);
        cursor.moveToFirst();
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(SchemaContract.COLUMN_NAME_ID_MESSAGE)) + 1;
        cursor.close();
        return id;
    }

    

}
