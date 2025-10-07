package io.github.kauanmedeirosss.locadora.unit.repository;

import io.github.kauanmedeirosss.locadora.entity.CarroEntity;
import io.github.kauanmedeirosss.locadora.repository.CarroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test") //esse nome vem do "application-'TEST'.properties"
class CarroRepositoryTest {

    @Autowired
    CarroRepository carroRepository;

    CarroEntity carro;

    @BeforeEach
    void setUp(){
        carro = new CarroEntity("Honda Civic", 200.00, 2025);
    }

    @Test
    void deveSalvarCarro(){
        var entity = new CarroEntity("Sedan", 100.0, 2022);
        carroRepository.save(entity);
        assertNotNull(entity.getId());
        //já que o banco é reponsável por dar um ID, ter um ID é prova de que está salvo no banco
    }

    @Test
    void deveBuscarCarroPorId(){
        var carroSalvo = carroRepository.save(carro);
        Optional<CarroEntity> carroEmcontrado = carroRepository.findById(carroSalvo.getId());
        assertThat(carroEmcontrado).isPresent();
        assertThat(carroEmcontrado.get().getModelo()).isEqualTo("Honda Civic");
    }

    @Test
    void deveAtualizarCarro(){
        var carroSalvo = carroRepository.save(carro);
        carroSalvo.setAno(2026);
        var carroAtualizado = carroRepository.save(carroSalvo);
        assertThat(carroAtualizado.getAno()).isEqualTo(2026);
    }

    @Test
    void deveDeletarCarro(){
        var carroSalvo = carroRepository.save(carro);
        carroRepository.deleteById(carroSalvo.getId());
        Optional<CarroEntity> carroEncontrado = carroRepository.findById(carroSalvo.getId());
        assertThat(carroEncontrado).isEmpty();
    }

}