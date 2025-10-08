package io.github.kauanmedeirosss.locadora.model;

import io.github.kauanmedeirosss.locadora.exception.ReservaInvalidaException;

public class Reserva {

    private Cliente cliente;
    private Carro carro;
    private Integer dias;

    public Reserva(Cliente cliente, Carro carro, Integer dias) {
        if (dias < 1){ throw new ReservaInvalidaException("Quantidade de dias da reserva não pode ser menor que 1!"); }

        this.cliente = cliente;
        this.carro = carro;
        this.dias = dias;
    }

    public Double calcularTotal(){
        return carro.calcularValorAluguel(dias);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

}
