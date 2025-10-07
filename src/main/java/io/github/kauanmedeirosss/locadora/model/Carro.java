package io.github.kauanmedeirosss.locadora.model;

import lombok.Data;

@Data

public class Carro {

    private String modelo;
    private Double valorDiaria;

    public Carro(String modelo, Double valorDiaria) {
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
    }

    public Double calcularValorAluguel(Integer dias){
        Double desconto = Double.valueOf(0.0);
        if (dias >= 5){
            desconto = 50.0;
        }
        return (dias * valorDiaria) - desconto;
    }

}
