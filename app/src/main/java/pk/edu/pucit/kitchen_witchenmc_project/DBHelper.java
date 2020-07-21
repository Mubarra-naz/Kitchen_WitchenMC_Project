package pk.edu.pucit.kitchen_witchenmc_project;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import pk.edu.pucit.kitchen_witchenmc_project.model.User;

import static android.widget.Toast.LENGTH_SHORT;

public class DBHelper extends SQLiteOpenHelper{

    public DBHelper(@Nullable Context context){
        super(context,"KitchenWitchen",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USERS(email TEXT PRIMARY KEY , name TEXT, password TEXT)");
        db.execSQL("CREATE TABLE categories(cat_name TEXT PRIMARY KEY, cat_img TEXT)");
        db.execSQL("CREATE TABLE dishes(dish_name TEXT PRIMARY KEY, dish_img TEXT, dish_ingr TEXT, dish_loc TEXT, dish_price INT, cat_name TEXT)");
        db.execSQL("CREATE TABLE cart(item_no INT PRIMARY KEY AUTOINCREMENT, item_name TEXT, item_price INT, item_loc TEXT)");
        //Toast.makeText(DBHelper.this,"tables done", LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }

    Cursor getData(String sql) {
        SQLiteDatabase sqlDB = getReadableDatabase();
        return sqlDB.rawQuery(sql, null);
    }

    void dataQuery(String sql) {
        SQLiteDatabase sqlDB = getWritableDatabase();
        sqlDB.execSQL(sql);
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public boolean checkEmail(String email)
    {
        String sql = "SELECT email FROM USERS WHERE email=?";
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery(sql, new String[]{email});
        boolean isValid = cursor.moveToFirst();
        cursor.close();
        return isValid;
    }

    public boolean signUp(User usr){
        if (checkEmail(usr.getMail())){
            return false;
        }
        SQLiteDatabase db=getWritableDatabase();
        String query="INSERT INTO USERS VALUES(?,?,?)";
        SQLiteStatement statement = db.compileStatement(query);
        statement.clearBindings();
        statement.bindString(1, usr.getMail());
        statement.bindString(2, usr.getName());
        statement.bindString(3, usr.getPassword());

        ((SQLiteStatement) statement).executeInsert();
        return true;
    }
}
