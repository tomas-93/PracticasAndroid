package mensajeria.tomas.com.conexion.models.sql;

import android.provider.BaseColumns;

/**
 * Created by Tomas on 19/07/2015.
 */
public final class SchemaContract implements BaseColumns
{
    /*
                 Table config
    */
    public static final String TABLE_NAME_CONFIG = "cofig";
    public static final String COLUMN_NAME_ID_CONFIG = "idCofig";
    public static final String COLUMN_NAME_HOST = "host";
    public static final String COLUMN_NAME_STATUS = "status";

    /*
                Table message
    */

    public static final String TABLE_NAME_MESSAGE = "message";
    public static final String COLUMN_NAME_ID_MESSAGE = "idMessage";
    public static final String COLUMN_NAME_PHONE = "phone";
    public static final String COLUMN_NAME_DATE = "date";
    public static final String COLUMN_NAME_MESSAGE = "messageBody";

    public static final String COLUMN_NAME_NULLABLE = null;


    //Create Table Config
    protected static final String CREATE_TABLE_CONFIG = "CREATE TABLE " +
            TABLE_NAME_CONFIG + " ("+ COLUMN_NAME_ID_CONFIG +
            " INTEGER PRIMARY KEY, "  +
            COLUMN_NAME_STATUS + " TEXT, " +
            COLUMN_NAME_HOST + " TEXT " +")";

    //Create Table Message
    protected static final String CREATE_TABLE_MESSAGE = "CREATE TABLE " +
            TABLE_NAME_MESSAGE + " (" + COLUMN_NAME_ID_MESSAGE +
            " INTEGER PRIMARY KEY, " + COLUMN_NAME_PHONE + " TEXT, "
            + COLUMN_NAME_DATE + " TEXT, " +
            COLUMN_NAME_MESSAGE +" TEXT " +")";

    //Delete table Confg
    protected static final String DELETE_TABLE_CONFIG = "DROP TABLE IF EXISTS "
            + TABLE_NAME_CONFIG;
    //Delete table Message
    protected static final String DELETE_TABLE_MESSAGE = "DROP TABLE IF EXISTS "
            + TABLE_NAME_MESSAGE;

    protected static final String MAX_ID_TABLE_MESSAGE ="SELECT MAX(" +COLUMN_NAME_ID_MESSAGE+ ") AS " + COLUMN_NAME_ID_MESSAGE + " FROM "+ TABLE_NAME_MESSAGE;

}
