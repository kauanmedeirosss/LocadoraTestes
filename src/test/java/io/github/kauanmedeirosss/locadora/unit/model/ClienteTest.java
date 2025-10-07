package io.github.kauanmedeirosss.locadora.unit.model;

import io.github.kauanmedeirosss.locadora.model.Cliente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class ClienteTest {

    @Test
    void deveCriarClienteComNome(){
        // 1. Cenário
        Cliente cliente = new Cliente("Kauan");

        // 2. Execução
        String nome = cliente.getNome();

        // 3. Verificação
        assertNotNull(nome);
        assertTrue(nome.startsWith("K"));
        assertFalse(nome.length() == 100);

        // Demonstração da biblioteca assertj
        assertThat(nome).isEqualTo("Kauan");
        assertThat(nome).isLessThan("Kauan1");
        assertThat(nome.length()).isLessThan(100);
        assertThat(nome).contains("Ka");
        assertThat(nome).contains("uan");
    }

    @Test
    void deveCriarClienteSemNome(){
        // 1. Cenário
        Cliente cliente = new Cliente(null);

        // 2. Execução
        var nome = cliente.getNome();

        // 3. Verificação
        assertNull(nome);
    }

}
