package io.github.kauanmedeirosss.locadora.unit.repository;

import io.github.kauanmedeirosss.locadora.entity.CarroEntity;
import io.github.kauanmedeirosss.locadora.repository.CarroRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test") //esse nome vem do "application-'TEST'.properties"
class CarroRepositoryTest {

    @Autowired
    CarroRepository carroRepository;

    @Test
    void deveSalvarCarro(){
        var entity = new CarroEntity("Sedan", 100.0);
        carroRepository.save(entity);
        assertNotNull(entity.getId());
        //já que o banco é reponsável por dar um ID, ter um ID é prova de que está salvo no banco
    }

}