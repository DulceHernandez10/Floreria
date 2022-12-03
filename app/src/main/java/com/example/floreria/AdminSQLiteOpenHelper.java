package com.example.floreria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE clientes(id_c integer primary key autoincrement not null, nombre_c text, direccion_c text, correo_c text not null, telefono_c integer, contraseña_c text not null )");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS clientes");
        sqLiteDatabase.execSQL("CREATE TABLE clientes(id_c integer primary key autoincrement not null, nombre_c text, direccion_c text, correo_c text not null, telefono_c integer, contraseña_c text not null )");


    }
}
