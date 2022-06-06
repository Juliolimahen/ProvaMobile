package com.example.provamobile;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "Banco1.db";
    private static final int version = 1;

    public Conexao(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table imovel(idImovel integer primary key autoincrement, " +
                "apelido varchar(50), local varchar(50), area varchar(50), aluguel varchar(50)" +
                ",comprar varchar(50),queroAlugar varchar(50),queroComprar varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}