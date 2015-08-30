package slqite.tomas.com.sqlite;
import android.provider.BaseColumns;
/**
 * Created by Tomas on 22/08/2015.
 */
public class Schema implements BaseColumns
{
    public static final String TABLE_PRUBEA ="prueba";

    public static final String COLUMN_NAME_TEXT = "cadena";

    public static final String CREATE_TABLE = "create table " + TABLE_PRUBEA +" ("+
                                              BaseColumns._ID + " integer primary key, " +
                                              COLUMN_NAME_TEXT + " text);";

    public static final String DELETE_TABLE = "drop table if exists "+TABLE_PRUBEA;
    public static final String MAX_ID = "select max(" + BaseColumns._ID +") as "+BaseColumns._ID+" from " + TABLE_PRUBEA;


}
