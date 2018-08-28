package br.pro.adalto.appcompras_m171.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.pro.adalto.appcompras_m171.model.Categoria;

/**
 * Created by assparremberger on 28/08/2018.
 */

public class CategoriaDAO {

    public static void inserir(Context contexto, Categoria categoria){
        Conexao conn = new Conexao(contexto);
        SQLiteDatabase banco = conn.getWritableDatabase();

        banco.execSQL("INSERT INTO categorias (nome) VALUES " +
                      " ( '" + categoria.getNome() + "' )  ");

    }



}
