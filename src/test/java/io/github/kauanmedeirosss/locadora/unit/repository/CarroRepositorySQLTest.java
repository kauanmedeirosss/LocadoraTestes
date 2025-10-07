package io.github.kauanmedeirosss.locadora.unit.repository;

import io.github.kauanmedeirosss.locadora.entity.CarroEntity;
import io.github.kauanmedeirosss.locadora.repository.CarroRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
class CarroRepositorySQLTest {

    @Autowired
    CarroRepository carroRepository;

    @Test
    @Sql("/sql/popular-carros.sql")
    void deveBuscarCarroPorModelo(){
        List<CarroEntity> lista = carroRepository.findByModelo("SUV");
        var carro = lista.stream().findFirst().get();

        assertEquals(2, lista.size());
        assertThat(carro.getModelo()).isEqualTo("SUV");
        assertThat(carro.getValorDiaria()).isEqualTo(150.0);
    }

}
