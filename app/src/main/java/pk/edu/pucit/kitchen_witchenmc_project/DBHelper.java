package pk.edu.pucit.kitchen_witchenmc_project;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import pk.edu.pucit.kitchen_witchenmc_project.model.User;
import pk.edu.pucit.kitchen_witchenmc_project.model.cartItem;
import pk.edu.pucit.kitchen_witchenmc_project.model.category;
import pk.edu.pucit.kitchen_witchenmc_project.model.dish;
import pk.edu.pucit.kitchen_witchenmc_project.model.order;

import static pk.edu.pucit.kitchen_witchenmc_project.common.common.currentUser;

public class DBHelper extends SQLiteOpenHelper{

    public DBHelper(@Nullable Context context){
        super(context,"KitchenWitchen",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USERS(user_id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT UNIQUE , name TEXT, password TEXT)");
        db.execSQL("CREATE TABLE CATEGORIES(cat_name TEXT PRIMARY KEY, cat_img TEXT)");
        db.execSQL("CREATE TABLE DISHES(dish_name TEXT PRIMARY KEY, dish_img TEXT, dish_ingr TEXT, dish_price LONG, cat_name TEXT)");
        db.execSQL("CREATE TABLE CART(item_no INTEGER PRIMARY KEY AUTOINCREMENT, item_name TEXT, item_img TEXT, item_price LONG, item_qty LONG, user_id INTEGER, item_status INTEGER, order_id INTEGER)");
        db.execSQL("CREATE TABLE ORDERS(order_no INTEGER PRIMARY KEY AUTOINCREMENT, order_status TEXT, order_price LONG, user_id INTEGER)");//, order_item TEXT, order_quantity LONG)");
        //in cart status=0 i.e. in the cart, status=1 i.e. ordered/confirmed, status=2 i.e. order done now its time to delete
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
//User authentication start
    public boolean checkEmail(String email) {
        String sql = "SELECT email FROM USERS WHERE email=?";
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery(sql, new String[]{email});
        boolean isValid = cursor.moveToFirst();
        cursor.close();
        return isValid;
    }
    boolean checkPassword(String email, String password){
        String sql = "SELECT password FROM USERS WHERE email=?";
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery(sql, new String[]{email});
        boolean isValid = false;
        while (cursor.moveToNext()){
            String pass = cursor.getString(0);
            isValid = password.equals(pass);
        }
        cursor.close();
        return isValid;
    }
    public boolean signUp(User usr){
        if (checkEmail(usr.getMail())){
            return false;
        }
        SQLiteDatabase db=getWritableDatabase();
        String query="INSERT INTO USERS VALUES(NULL,?,?,?)";
        SQLiteStatement statement = db.compileStatement(query);
        statement.clearBindings();
        statement.bindString(1, usr.getMail());
        statement.bindString(2, usr.getName());
        statement.bindString(3, usr.getPassword());
        statement.executeInsert();
        return true;
    }
    public boolean signIn(User usr){
        if (checkEmail(usr.getMail())){
            if (checkPassword(usr.getMail(),usr.getPassword())){
                return true;
            }
        }
        return false;
    }
    public String getUserName(String email){
        String sql = "SELECT name FROM USERS WHERE email=?";
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery(sql, new String[]{email});
        String usrName=null;
        cursor.moveToNext();
        usrName = cursor.getString(cursor.getColumnIndex("name"));
        cursor.close();
        return usrName;
    }
    public int getUserID(String mail){
        String sql = "SELECT user_id FROM USERS WHERE email=?";
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery(sql, new String[]{mail});
        int id;
        cursor.moveToNext();
        id = cursor.getInt(cursor.getColumnIndex("user_id"));
        cursor.close();
        return id;
    }
    //User authentication end

    //add category
    public boolean addCategory(category item){
        if(categoryCheck(item)==false) {
            SQLiteDatabase sql = getWritableDatabase();
            String query = "INSERT INTO CATEGORIES VALUES(?,?)";
            SQLiteStatement statement=sql.compileStatement(query);
            statement.clearBindings();
            statement.bindString(1, item.getName());
            statement.bindString(2, item.getImg());
            statement.executeInsert();
            return true;
        }
        return false;
    }

    private boolean categoryCheck(category item) {
        String sql = "SELECT cat_name FROM CATEGORIES WHERE cat_name=?";
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery(sql, new String[]{item.getName()});
        boolean isValid = cursor.moveToFirst();
        cursor.close();
        return isValid;
    }

    //Categories loading
    public ArrayList<category> loadCategories(){
        category catItem=new category();
        ArrayList<category> categories=new ArrayList<>();
        String query="SELECT * from CATEGORIES";
        Cursor cursor=getData(query);
        while (cursor.moveToNext()){
            catItem.setImg(cursor.getString(cursor.getColumnIndex("cat_img")));
            catItem.setName(cursor.getString(cursor.getColumnIndex("cat_name")));
            categories.add(catItem);
        }
        cursor.close();
        return categories;
    }
    //load dishes of a speific category
    public ArrayList<dish> loadCategoryDishes(String nameOfCategory){
        dish dishItem=new dish();
        ArrayList<dish> dishes=new ArrayList<>();
        String sql="SELECT * from DISHES WHERE cat_name=?";
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery(sql, new String[]{nameOfCategory});
        while (cursor.moveToNext()){
            dishItem.setImg(cursor.getString(cursor.getColumnIndex("dish_img")));
            dishItem.setName(cursor.getString(cursor.getColumnIndex("dish_name")));
            dishItem.setPrice(cursor.getInt(cursor.getColumnIndex("dish_price")));
            dishes.add(dishItem);
        }
        return dishes;
    }
    //single dishd etail

    public boolean addDish(dish item){
        if(dishCheck(item)==false) {
            SQLiteDatabase sql = getWritableDatabase();
            String query = "INSERT INTO DISHES VALUES(?,?,?,?,?)";
            SQLiteStatement statement=sql.compileStatement(query);
            statement.clearBindings();
            statement.bindString(1, item.getName());
            statement.bindString(2, item.getImg());
            statement.bindString(3, item.getIngredients());
            statement.bindLong(4, item.getPrice());
            statement.bindString(5, item.getCategoryName());
            statement.executeInsert();
            return true;
        }
        return false;
    }
    private boolean dishCheck(dish item) {
        String sql = "SELECT dish_name FROM DISHES WHERE dish_name=?";
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery(sql, new String[]{item.getName()});
        boolean isValid = cursor.moveToFirst();
        cursor.close();
        return isValid;
    }
    public void editDish(dish item){
        SQLiteDatabase sql = getWritableDatabase();
        String query = "UPDATE DISHES SET dish_name=?,dish_img=?, dish_ingr=?, dish_price=?, cat_name=?";
        SQLiteStatement statement=sql.compileStatement(query);
        statement.clearBindings();
        statement.bindString(1, item.getName());
        statement.bindString(2, item.getImg());
        statement.bindString(3, item.getIngredients());
        statement.bindLong(4, item.getPrice());
        statement.bindString(5, item.getCategoryName());
        statement.executeUpdateDelete();
    }
    public dish getDishDetail(String dishName){
        dish dishItem=new dish();
        String sql="SELECT * from DISHES WHERE dish_name=?";
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery(sql, new String[]{dishName});
        cursor.moveToNext();
        dishItem.setImg(cursor.getString(cursor.getColumnIndex("dish_img")));
        dishItem.setName(cursor.getString(cursor.getColumnIndex("dish_name")));
        dishItem.setPrice(cursor.getLong(cursor.getColumnIndex("dish_price")));
        dishItem.setIngredients(cursor.getString(cursor.getColumnIndex("dish_ingr")));
        cursor.close();
        return dishItem;
    }

    public void insertCartItem(cartItem item){
        SQLiteDatabase db=getWritableDatabase();
        String query;
        query="INSERT INTO CART VALUES(NULL,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(query);
        statement.clearBindings();
        statement.bindString(1, item.getName());
        statement.bindString(2, item.getImg());
        statement.bindLong(3, item.getPrice());
        statement.bindLong(4, item.getQuantity());
        statement.bindLong(5, item.getUserId());
        statement.bindLong(6, item.getStatus());
        statement.executeInsert();
    }
    public void cancelItem(cartItem item){
        SQLiteDatabase db=getWritableDatabase();
        String query="DELETE FROM CART WHERE item_no=? AND user_id=?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.clearBindings();
        statement.bindLong(1, item.getId());
        statement.bindLong(2, item.getUserId());
        statement.executeUpdateDelete();
    }
    public void clearCart(){
        SQLiteDatabase db=getWritableDatabase();
        String query="DELETE FROM CART WHERE item_status=? AND user_id=?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindLong(1, 2);
        statement.bindLong(1, currentUser.getId());
        statement.executeUpdateDelete();
    }
    public ArrayList<cartItem> loadCart(){
        String sql="SELECT * FROM CART WHERE item_status=? AND user_id=?";
        cartItem cItem=new cartItem();
        ArrayList<cartItem> cartList=new ArrayList<>();
        int status=0;
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery(sql, new String[]{String.valueOf(status), String.valueOf(currentUser.getId())});
        while (cursor.moveToNext()){
            cItem.setImg(cursor.getString(cursor.getColumnIndex("item_img")));
            cItem.setName(cursor.getString(cursor.getColumnIndex("item_name")));
            cItem.setUserId(cursor.getInt(cursor.getColumnIndex("user_id")));
            cItem.setPrice(cursor.getLong(cursor.getColumnIndex("item_price")));
            cItem.setQuantity(cursor.getInt(cursor.getColumnIndex("item_qty")));
            cItem.setStatus(cursor.getInt(cursor.getColumnIndex("item_status")));
            cItem.setId(cursor.getInt(cursor.getColumnIndex("item_no")));
            cartList.add(cItem);
        }
        cursor.close();
        return cartList;
    }
    public int cartSum(){
    String sql="SELECT item_price FROM CART WHERE item_status=? AND user_id=?";
    SQLiteDatabase sqlDB = this.getReadableDatabase();
    Cursor cursor = sqlDB.rawQuery(sql, new String[]{String.valueOf(0), String.valueOf(currentUser.getId())});
    int sum=0;
    while(cursor.moveToNext()){
        sum+=cursor.getInt(0);
    }
    cursor.close();
    return sum;
}
    public void bookOrder(){
        int totalPrice=cartSum();
        SQLiteDatabase db=getWritableDatabase();
        String query,sql,query1,query2;
        query="INSERT INTO ORDERS VALUES(NULL,?,?,?)";
        SQLiteStatement stmt=db.compileStatement(query);
        stmt.clearBindings();
        stmt.bindString(1, "confirmed");//order confirmed
        stmt.bindLong(2,totalPrice);
        stmt.bindLong(3,currentUser.getId());
        SQLiteDatabase readable=getReadableDatabase();
        query1="SELECT order_no FROM ORDERS where user_id=?";
        Cursor cursor=readable.rawQuery(query1, new String[]{String.valueOf(currentUser.getId())});
        cursor.moveToLast();
        int orderNo=cursor.getInt(0);
        cursor.close();
        query2="SELECT item_no FROM CART WHERE item_status=? AND user_id=?";
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor cur = sqlDB.rawQuery(query2, new String[]{String.valueOf(0), String.valueOf(currentUser.getId())});
        int length=cur.getCount();
        cur.close();
        for (int i=0;i<length;i++){
            sql="UPDATE CART SET item_status=?, order_id=? where item_status=? and user_id=?";
            SQLiteStatement statement=db.compileStatement(sql);
            statement.clearBindings();
            statement.bindLong(1, 1);
            statement.bindLong(2,orderNo);
            statement.bindLong(3, 0);
            statement.bindLong(4,currentUser.getId());
            statement.executeUpdateDelete();
        }
    }

    public ArrayList<order> viewOrders() {
        order item=new order();
        ArrayList<order> orderList=new ArrayList<>();
        SQLiteDatabase readable=getReadableDatabase();
        String query1="SELECT * FROM ORDERS where user_id=?";
        Cursor cursor=readable.rawQuery(query1, new String[]{String.valueOf(currentUser.getId())});
        while (cursor.moveToNext()){
            item.setOrderId(cursor.getInt(cursor.getColumnIndex("order_id")));
            item.setOrderStatus(cursor.getString(cursor.getColumnIndex("order_status")));
            item.setOrderPrice(cursor.getInt(cursor.getColumnIndex("order_price")));
            orderList.add(item);
        }
        cursor.close();
        return orderList;
    }

    public ArrayList<dish> loadDishes() {
        dish dishItem=new dish();
        ArrayList<dish> dishes=new ArrayList<>();
        String sql="SELECT * from DISHES";
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery(sql, new String[]{});
        while (cursor.moveToNext()){
            dishItem.setImg(cursor.getString(cursor.getColumnIndex("dish_img")));
            dishItem.setName(cursor.getString(cursor.getColumnIndex("dish_name")));
            dishItem.setIngredients(cursor.getString(cursor.getColumnIndex("dish_ingr")));
            dishItem.setCategoryName(cursor.getString(cursor.getColumnIndex("cat_name")));
            dishItem.setPrice(cursor.getInt(cursor.getColumnIndex("dish_price")));
            dishes.add(dishItem);
        }
        return dishes;
    }

    public void removeDish(String name) {
        SQLiteDatabase db=getWritableDatabase();
        String query="DELETE FROM DISH WHERE dish_name=?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.clearBindings();
        statement.bindString(1, name);
        statement.executeUpdateDelete();
    }
}


