package com.example.provamobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ImovelDAO implements InterfaceDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public ImovelDAO(Context context) {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }


    /*public List<Imovel> obterTodos(){
        List<Imovel> imoveis = new ArrayList<>();
        Cursor cursor = banco.query("imovel", new String[]{"idImovel", "apelido", "local", "area", "aluguel", "comprar", "queroAlugar", "queroComprar"},
                null, null, null, null, null);

        //Verfica se consegue mover para o proximo registro
        while (cursor.moveToNext()){
            Imovel imovel= new Imovel();
            imovel.setIdImovel(cursor.getInt(0));
            imovel.setApelido(cursor.getString(1));
            imovel.setLocal(cursor.getString(2));
            imovel.setArea(cursor.getString(3));
            imovel.setAluguel(cursor.getString(4));
            imovel.setComprar(cursor.getString(5));
            imovel.setQueroAlugar(cursor.getString(6));
            imovel.setQueroComprar(cursor.getString(7));

            imoveis.add(imovel);
        }
        return imoveis;
    }*/

    public List<Imovel> obterImoveis(String param) {
        List<Imovel> imoveis;
        Cursor cursor;

        if (param.toUpperCase().equalsIgnoreCase("TODOS")) {
            imoveis = new ArrayList<>();
            cursor = banco.query("imovel", new String[]{"idImovel", "apelido", "local", "area", "aluguel", "comprar", "queroAlugar", "queroComprar"},
                    null, null, null, null, null);
        } else if (param.toUpperCase().equalsIgnoreCase("ALUGAR")) {
            imoveis = new ArrayList<>();
            cursor = banco.query("imovel", new String[]{"idImovel", "apelido", "local", "area", "aluguel", "comprar", "queroAlugar", "queroComprar"},
                    "queroAlugar = ?", new String[]{"sim"}, null, null, null);
        } else if (param.toUpperCase().equalsIgnoreCase("COMPRAR")) {
            imoveis = new ArrayList<>();
            cursor = banco.query("imovel", new String[]{"idImovel", "apelido", "local", "area", "aluguel", "comprar", "queroAlugar", "queroComprar"},
                    "queroComprar = ?", new String[]{"sim"}, null, null, null);
        } else {
            imoveis = new ArrayList<>();
            cursor = banco.query("imovel", new String[]{"idImovel", "apelido", "local", "area", "aluguel", "comprar", "queroAlugar", "queroComprar"},
                    "apelido = ?", new String[]{param}, null, null, null);
        }

        //Verfica se consegue mover para o proximo registro
        while (cursor.moveToNext()) {
            Imovel imovel = new Imovel();
            imovel.setIdImovel(cursor.getInt(0));
            imovel.setApelido(cursor.getString(1));
            imovel.setLocal(cursor.getString(2));
            imovel.setArea(cursor.getString(3));
            imovel.setAluguel(cursor.getString(4));
            imovel.setComprar(cursor.getString(5));
            imovel.setQueroAlugar(cursor.getString(6));
            imovel.setQueroComprar(cursor.getString(7));

            imoveis.add(imovel);
        }
        return imoveis;
    }

    @Override
    public long inserir(Object obj) {
        Imovel imovel = (Imovel) obj;
        ContentValues values = new ContentValues();
        values.put("apelido", imovel.getApelido());
        values.put("local", imovel.getLocal());
        values.put("area", imovel.getArea());
        values.put("aluguel", imovel.getAluguel());
        values.put("comprar", imovel.getComprar());
        values.put("queroAlugar", imovel.getQueroAlugar());
        values.put("queroComprar", imovel.getQueroComprar());

        return banco.insert("imovel", null, values);
    }

    @Override
    public void deletar(Object obj) {
        Imovel imovel = (Imovel) obj;
        banco.delete("imovel", "idImovel = ?", new String[]{String.valueOf((imovel.getIdImovel()))});
        //return 0;
    }

    @Override
    public void atualizar(Object obj) {
        Imovel imovel = (Imovel) obj;

        ContentValues values = new ContentValues();
        values.put("apelido", imovel.getApelido());
        values.put("local", imovel.getLocal());
        values.put("area", imovel.getArea());
        values.put("aluguel", imovel.getAluguel());
        values.put("comprar", imovel.getComprar());
        values.put("queroAlugar", imovel.getQueroAlugar());
        values.put("queroComprar", imovel.getQueroComprar());
        banco.update("imovel", values, "idImovel = ?", new String[]{String.valueOf((imovel.getIdImovel()))});

    }
}
