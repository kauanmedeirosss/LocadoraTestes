package io.github.kauanmedeirosss.locadora.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarroTest {

    @Test
    @DisplayName("Deve calcular o valor correto do aluguel.")
    void deveCalcularValorAluguel(){
        Carro carro = new Carro("Sedan", 149.90);
        Double total = carro.calcularValorAluguel(4);
        Assertions.assertEquals(599.60, total);
    }

}
