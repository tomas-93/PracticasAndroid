package gps.tomas.com.gps.models.sql;

import android.provider.BaseColumns;

/**
 * Created by Tomas on 12/09/2015.
 */
public class Schema implements BaseColumns
{
    //Tabla location
    public static final String TABLE_NAME_LOCATION = "location";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_ALTITUDE = "altitude";
    public static final String COLUMN_SPEED = "speed";
    public static final String COLUMN_ACC = "acc";

    //Tabla Config
    public static final String TABLE_NAME_CONFIG = "confing";
    public static final String COLUMN_HOST = "host";
    public static final String COLUMN_TIME = "timer";
    public static final String COLUMN_PROCESS = "process";

    //Column name null
    public final String COLUMN_NULLABLE = null;

    //Create table  location
    protected static final String CREATE_TABLE_LOCATION =
            "CREATE TABLE " + TABLE_NAME_LOCATION
            + " (" + BaseColumns._ID + " INTEGER PRIMARY KEY, "
            + COLUMN_LATITUDE + " TEXT, "
            + COLUMN_LONGITUDE + " TEXT, "
            + COLUMN_ALTITUDE + " TEXT, "
            + COLUMN_SPEED + " TEXT, "
            + COLUMN_ACC +  " TEXT)";

    //create table config
    protected static final String CREATE_TABLE_CONFIG =
            "CREATE TABLE " + TABLE_NAME_CONFIG
            + " ("+ BaseColumns._ID + " INTEGER PRIMARY KEY, "
            + COLUMN_HOST + " TEXT, "
            + COLUMN_TIME + " TEXT, "
            + COLUMN_PROCESS + " TEXT)";

    //Delete table location
    protected static final String DELETE_TABLE_LOCATION = "DROP TABLE IF EXISTS " + TABLE_NAME_LOCATION;

    //Delete table config
    protected static final String  DELTE_TABLE_CONFIG = "DROP TABLE IF EXISTS " + TABLE_NAME_CONFIG;

    //ID table location
    protected static final String MAX_ID_TABLE_LOCATION = "SELECT MAX(" + BaseColumns._ID + ") AS " + BaseColumns._ID + " FROM " +TABLE_NAME_LOCATION ;







}
