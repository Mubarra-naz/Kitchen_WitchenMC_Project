package pk.edu.pucit.kitchen_witchenmc_project.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper{

    public DBHelper(@Nullable Context context) {
        super(context,"login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(mail TEXT PRIMARY KEY , pass TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
    }
    public boolean insert(String email , String pass){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValue= new ContentValues();
        contentValue.put("mail", email);
        contentValue.put("pass", pass);
        long ins=db.insert("users",null,contentValue);
        if(ins==1)return false;
        else return  true;


    }
    public boolean CheckEmail(String email)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from users where email=?",new String[]{email});
        if(cursor.getCount()>0)return false;
        else return  true;
    }
    public boolean signIn(String email,String pass)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from users where email=? AND pass=?",new String[]{pass});
        if(cursor.getCount()>0)return false;
        else return  true;
    }
}
