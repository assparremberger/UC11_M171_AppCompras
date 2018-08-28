package br.pro.adalto.appcompras_m171.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.pro.adalto.appcompras_m171.model.Produto;

/**
 * Created by assparremberger on 28/08/2018.
 */

public class ProdutoDAO {

    public static void inserir(Context contexto, Produto produto){
        Conexao conn = new Conexao(contexto);
        SQLiteDatabase banco = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", produto.getNome());
        valores.put("quantidade", produto.getQuantidade());
        valores.put("codCategoria", produto.getCategoria().getId());

        banco.insert("produtos", null, valores);

    }




}






