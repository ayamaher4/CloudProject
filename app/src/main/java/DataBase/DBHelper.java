package DataBase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import model.Profile;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    SQLiteDatabase DB;

    public DBHelper (@Nullable Context context) {
        super(context, "UniversityDB", null, 1);
        DB = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Profile.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Profile.DROP_TABLE);
        onCreate(sqLiteDatabase);

    }



    public boolean DataInsert(String Name , String Password , String Email , String Phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put( "userName" , Name);
        CV.put( "password" , Password);
        CV.put( "Email" , Email);
        CV.put( "Phone" , Phone);
        Long result = db.insert("Profile" , null , CV);
        if (result == -1){
            return false;
        }else{
            return true;
        }
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
