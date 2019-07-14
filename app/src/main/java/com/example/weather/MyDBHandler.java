package com.example.weather;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final  String TAG = "MyDBHandler";

    public static  final int DATABASE_VERSION=1;

    private static final String DATABASE_NAME= "account.db";

    public static  final  String ACCOUNTS="Accounts";

    public static  final String COLUMN_USERNAME="UserName";

    public static  final String COLUMN_PASSWORD="Password";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,DATABASE_NAME,factory,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        String CREATE_ACCOUNTS_TABLE ="CREATE TABLE "+ACCOUNTS+"("+COLUMN_USERNAME+" TEXT,"+COLUMN_PASSWORD+" TEXT"+")";

        db.execSQL(CREATE_ACCOUNTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS);

        onCreate(db);
    }

    public void addUser(UserData userData){
        ContentValues values= new ContentValues();
        values.put(COLUMN_USERNAME,userData.getMyUserName());
        values.put(COLUMN_PASSWORD,userData.getMyPassword());
        SQLiteDatabase db= this.getWritableDatabase();
        Log.v(TAG,values.toString());
        db.insert(ACCOUNTS,null,values);
        db.close();
    }

    public UserData findUser(String username)
    {
        String query= "SELECT * FROM "+ ACCOUNTS+ " WHERE "+COLUMN_USERNAME + "= \""+ username +"\"";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        UserData queryData= new UserData();
        if (cursor.moveToFirst()){
            queryData.setMyUserName(cursor.getString(0));
            queryData.setMyPassword(cursor.getString(1));
            cursor.close();
        }

        else{
            queryData=null;
        }
        db.close();
        return  queryData;
    }


    public boolean deleteAccount(String username)
    {
        boolean result = false;
        String query = "SELECT * FROM "+ACCOUNTS+" WHERE "+COLUMN_USERNAME+"=\""+username+"\"";
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor= db.rawQuery(query,null);

        UserData delData= new UserData();

        if(cursor.moveToFirst())
        {
            delData.setMyUserName(cursor.getString(0));
            db.delete(ACCOUNTS, COLUMN_USERNAME+"=?", new String[]{String.valueOf((delData.getMyUserName()))});
            cursor.close();
            result= true;
        }
        db.close();
        return result;
    }




}
