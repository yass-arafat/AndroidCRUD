package app.main.com.databasehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hasanarim on 26-Dec-16.
 */

public class DatabaseCreate extends SQLiteOpenHelper {
    public  static String DATABASE_NAME = "Student.Db";
    public  static String TABlE_NAME = "Student_data_table";
    public  static String COL_1 = "ID";
    public  static String COL_2 = "Name";
    public  static String COL_3 = "Surname";
    public  static String COL_4 = "Marks";

    public DatabaseCreate(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table" + TABlE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Surname TEXT, Marks INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exixts" + TABlE_NAME);
        onCreate(db);
    }
}
