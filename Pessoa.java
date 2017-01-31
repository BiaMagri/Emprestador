package com.facebook.bianca.emprestando;

import java.io.Serializable;

/**
 * Created by Bianca on 25/01/2017.
 */

public class Pessoa implements Serializable {

    private String nome;
    private Double saldo;

    public Pessoa(String nome, Double saldo){
        this.nome = nome;
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
