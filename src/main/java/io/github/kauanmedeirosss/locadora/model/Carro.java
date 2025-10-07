package io.github.kauanmedeirosss.locadora.model;

public class Carro {

    private String modelo;
    private Double valorDiaria;


    public Carro(String modelo, Double valorDiaria) {
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
    }

    public Double calcularValorAluguel(Integer dias){
        Double desconto = Double.valueOf(0);
        if (dias >= 5){
            desconto = 50.0;
        }
        return (dias * valorDiaria) - desconto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }
}
