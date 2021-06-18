package DataBase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import model.Profile;
import model.ProfileInformation;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Profile";
    private static final int DATABASE_VERSION = 3;
    private SQLiteDatabase DB;


    public DBHelper (@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.DB = sqLiteDatabase;


        final String PROFILE= " CREATE TABLE " + Profile.ProfileTable.TABLE_NAME
                + "(" +
                Profile.ProfileTable.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Profile.ProfileTable.COL_NAME + " TEXT," +
                Profile.ProfileTable.COL_EMAIL + " TEXT," +
                Profile.ProfileTable.COL_PASSWORD + " TEXT," +
                Profile.ProfileTable.COL_PHONE + " TEXT)";
        DB.execSQL(PROFILE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS " + Profile.ProfileTable.TABLE_NAME);
        onCreate(DB);

    };


    private void addinfo(ProfileInformation profileinformation) {
        ContentValues cv = new ContentValues();
        int id=0;
        String name ="";
        String email ="";
        String password ="";
        String phone ="";

        cv.put(Profile.ProfileTable.COL_ID,  id );
        cv.put(Profile.ProfileTable.COL_NAME, name);
        cv.put(Profile.ProfileTable.COL_EMAIL, email);
        cv.put(Profile.ProfileTable.COL_PASSWORD, password);
        cv.put(Profile.ProfileTable.COL_PHONE, phone);


        DB.insert(Profile.ProfileTable.TABLE_NAME, null, cv);
    }



    public Cursor ReadAllData(){
        String query ="SELECT * from DataInsert";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query , null);

        }
        return cursor;
    }
}
