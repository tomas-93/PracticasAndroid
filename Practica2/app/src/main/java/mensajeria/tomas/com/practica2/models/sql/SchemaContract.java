package mensajeria.tomas.com.practica2.models.sql;

import android.provider.BaseColumns;

/**
 * Created by Tomas on 19/07/2015.
 */
public final class SchemaContract implements BaseColumns
{
    /*
                 Table config
    */
    protected static final String TABLE_NAME_CONFIG = "cofig";
    protected static final String COLUMN_NAME_ID_CONFIG = "idCofig";
    protected static final String COLUMN_NAME_PHONE = "phone";
    protected static final String COLUMN_NAME_HOST = "host";

    /*
                Table message
    */

    protected static final String TABLE_NAME_MESSAGE = "message";
    protected static final String COLUMN_NAME_ID_MESSAGE = "idMessage";
    protected static final String COLUMN_NAME_DATE = "date";
    protected static final String COLUMN_NAME_LATITUDE = "latitude";
    protected static final String COLUMN_NAME_LONGITUDE = "logitude";
    protected static final String COLUMN_NAME_ALTITUDE = "altitude";

    protected static final String COLUMN_NAME_NULLABLE = null;


    //Create Table Config
    protected static final String CREATE_TABLE_CONFIG ="CREATE TABLE " +
            TABLE_NAME_CONFIG + " ("+ COLUMN_NAME_ID_CONFIG +
            " INTEGER PRIMARY KEY, " + COLUMN_NAME_PHONE + " INTEGER, " +
            COLUMN_NAME_HOST + " TEXT" + ")";

    //Create Table Message
    protected static final String CREATE_TABLE_MESSAGE ="CREATE TABLE " +
            TABLE_NAME_MESSAGE + " (" + COLUMN_NAME_ID_MESSAGE +
            " INTEGER PRIMARY KEY, " + COLUMN_NAME_DATE + " TEXT, " +
            COLUMN_NAME_LATITUDE + " TEXT, " + COLUMN_NAME_LONGITUDE + " TEXT, " +
            COLUMN_NAME_ALTITUDE + " TEXT " + ")";

    //Delete table Confg
    protected static final String DELETE_TABLE_CONFIG = "DROP TABLE IF EXISTS "
            + TABLE_NAME_CONFIG;
    //Delete table Message
    protected static final String DELETE_TABLE_MESSAGE = "DROP TABLE IF EXISTS "
            + TABLE_NAME_MESSAGE;

}
