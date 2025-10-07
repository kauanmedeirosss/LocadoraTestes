package io.github.kauanmedeirosss.locadora.model;

import lombok.Data;

@Data
public class Cliente {

    private String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

}
