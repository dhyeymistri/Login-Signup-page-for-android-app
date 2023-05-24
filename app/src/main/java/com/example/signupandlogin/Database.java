package com.example.signupandlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Objects;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1 = "create table users(username text, email text, passwordHash text, saltValue text)";
        db.execSQL(qry1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int register(String username, String email, String passwordHash, String saltValue) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("passwordHash", passwordHash);
        cv.put("saltValue", saltValue);

        int check = 0;
        SQLiteDatabase db = getReadableDatabase();
        String[] str = new String[1];
        str[0] = email;
        Cursor c = db.rawQuery("select * from users where email=?", str);
        if(c.moveToFirst()) {
            check = 1;
        }
        db.close();

        if(check == 0) {
            SQLiteDatabase db1 = getWritableDatabase();
            db1.insert("users", null, cv);
            db1.close();
            return 0;
        } else return 1;

    }

    public int login(String email, String password) {
        int result = 0;
        String saltValue = "sdkfjslfj";
        String passwordHash = "sdfsadfasdiwe";
        SQLiteDatabase db = getReadableDatabase();
        String[] str = new String[1];
        str[0] = email;
        Cursor c = db.rawQuery("select * from users where email=?", str);
        if(c.moveToFirst()) {
            do {
                saltValue = c.getString(3);
                passwordHash = c.getString(2);
            }while(c.moveToNext());
//            result = 1;
        }
        db.close();
        String newPasswordHash = PassBasedEnc.generateSecurePassword(password, saltValue);
        if (Objects.equals(newPasswordHash, passwordHash)) result=1;
        return result;
    }

}
