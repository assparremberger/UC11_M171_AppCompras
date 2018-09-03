package br.pro.adalto.appcompras_m171.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.pro.adalto.appcompras_m171.model.Categoria;
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

    public static List<Produto> getProdutos(Context contexto){
        List<Produto> listaDeProdutos = new ArrayList<>();
        Conexao conn = new Conexao(contexto);
        SQLiteDatabase banco = conn.getReadableDatabase();
        Cursor tabela = banco.rawQuery
                ("SELECT * FROM produtos", null);
        if ( tabela.getCount() > 0 ){
            tabela.moveToFirst();
            do {
                Categoria cat = new Categoria();
                cat.setId( tabela.getInt(3));

                Produto produto = new Produto();
                produto.setId( tabela.getInt(0) );
                produto.setNome( tabela.getString(1) );
                produto.setQuantidade( tabela.getDouble(2) );
                produto.setCategoria( cat );

                listaDeProdutos.add( produto );

            }while ( tabela.moveToNext() );
        }

        return listaDeProdutos;
    }


}






