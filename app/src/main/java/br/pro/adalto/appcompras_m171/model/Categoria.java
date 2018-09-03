package br.pro.adalto.appcompras_m171.model;

/**
 * Created by assparremberger on 27/08/2018.
 */

public class Categoria {

    private int id;
    private String nome;

    public Categoria(){

    }

    public Categoria(String nome){
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public String toString() {
        return nome;
    }
}
