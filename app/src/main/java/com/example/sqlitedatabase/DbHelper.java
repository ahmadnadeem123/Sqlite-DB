package com.example.sqlitedatabase;

import  android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.session.PlaybackState;

import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
public class DbHelper extends SQLiteOpenHelper {

    public static final String STUDENT_NAME = "STUDENTName";
    public static final String STUDENT_ID = "STUDENTID";
    public static final String STUDENT_TABLE = "StudentTable";

    public DbHelper(@Nullable Context context) {
        super(context, "studentDB.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSTatement = "CREATE TABLE " + STUDENT_TABLE + "(" + STUDENT_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + STUDENT_NAME + " Text) ";
        db.execSQL(createTableSTatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
        onCreate(db);
    }

    public void  addStudent(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        //Hash map, as we did in bundles
        ContentValues cv = new ContentValues();

        cv.put(STUDENT_NAME, name);
        db.insert(STUDENT_TABLE, null, cv);
        db.close();

        //NullCoumnHack
        //long insert =
        //if (insert == -1) { return false; }
        //else{return true;}
    }


    public ArrayList<String> getAllStudents() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + STUDENT_TABLE, null);

        ArrayList<String> studentArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {

                studentArrayList.add(new String(cursorCourses.getString(1)));

            } while (cursorCourses.moveToNext());

        }

        cursorCourses.close();
        return studentArrayList;
    }

    public boolean delete(String name)
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c=db.rawQuery("select * from StudentTable where STUDENTName=?", new String[] {name});
        if(c.getCount()>0)
        {
            db.delete("StudentTable", "STUDENTName=?",new String[] {name} );
            return true;
        }

        else
            return false;
    }

    public boolean update(String name, String newname)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues con=new ContentValues();
        con.put("STUDENTName" , newname);
        Cursor c=db.rawQuery("select * from StudentTable where STUDENTName=?", new String[] {name});
        if(c.getCount()>0)
        {
            db.update("StudentTable", con,  "STUDENTName=?",new String[] {name} );
            return true;
        }

        else
            return false;
    }
}
