package io.github.kauanmedeirosss.locadora.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarroTest {

    @Test
    @DisplayName("Deve calcular o valor correto do aluguel.")
    void deveCalcularValorAluguel(){
        // 1. Cenário
        Carro carro = new Carro("Sedan", 149.90);

        // 2. Execução
        Double total = carro.calcularValorAluguel(4);

        // 3. Verificação
        Assertions.assertEquals(599.60, total);
    }

    @Test
    @DisplayName("Deve calcular o valor do aluguel com desconto.")
    void deveCalcularValorAluguelComDesconto(){
        // 1. Cenário
        Carro carro = new Carro("Sedan", 149.90);
        Integer qtDias = 5;

        // 2. Execução
        Double total = carro.calcularValorAluguel(qtDias);

        // 3. Verificação
        Assertions.assertEquals(699.50, total);
    }

}
