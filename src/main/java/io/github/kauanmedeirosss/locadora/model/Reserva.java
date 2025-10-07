package io.github.kauanmedeirosss.locadora.model;

import io.github.kauanmedeirosss.locadora.exception.ReservaInvalidaException;
import lombok.Data;

@Data
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

}
