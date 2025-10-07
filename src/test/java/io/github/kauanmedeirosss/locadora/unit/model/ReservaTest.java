package io.github.kauanmedeirosss.locadora.unit.model;

import io.github.kauanmedeirosss.locadora.exception.ReservaInvalidaException;
import io.github.kauanmedeirosss.locadora.model.Carro;
import io.github.kauanmedeirosss.locadora.model.Cliente;
import io.github.kauanmedeirosss.locadora.model.Reserva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {

    Cliente cliente;
    Carro carro;

    @BeforeEach
    void setUp(){
        cliente = new Cliente("Kauan");
        carro = new Carro("Pickup", 100.0);
    }

    @Test
    void deveCriarReserva(){
        // 1. Cenário
        var dias = 5;

        // 2. Execução
        var reserva = new Reserva(cliente, carro, dias);

        // 3. Verificação
        assertThat(reserva).isNotNull();
    }

    @Test
    void deveDarErroCriarReservaDiasNegativos(){
        // JUnit
        assertThrows(ReservaInvalidaException.class, () -> new Reserva(cliente, carro, 0));
        assertThrows(ReservaInvalidaException.class, () -> new Reserva(cliente, carro, -2));
        assertDoesNotThrow(() -> new Reserva(cliente, carro, 3));

        // AssertJ
        var erro = catchThrowable(() -> new Reserva(cliente, carro, 0));
        assertThat(erro)
                .isInstanceOf(ReservaInvalidaException.class)
                .hasMessage("Quantidade de dias da reserva não pode ser menor que 1!");
    }

    @Test
    void deveCalcularValorAluguel(){
        // 1. Cenário
        var reserva1 = new Reserva(cliente, carro, 3);
        var reserva2 = new Reserva(cliente, carro, 5);

        // 2. Execução
        var total1 = reserva1.calcularTotal();
        var total2 = reserva2.calcularTotal();

        // 3. Verificação
        assertThat(total1).isEqualTo(300.0);
        assertThat(total2).isEqualTo(450.0);
    }

}