package io.github.kauanmedeirosss.locadora.unit.service;

import io.github.kauanmedeirosss.locadora.entity.CarroEntity;
import io.github.kauanmedeirosss.locadora.repository.CarroRepository;
import io.github.kauanmedeirosss.locadora.service.CarroService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sound.midi.Soundbank;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CarroServiceTest {

    @InjectMocks
    CarroService service;

    @Mock
    CarroRepository repository;

    @Test
    void demonstracaoMockEhVazio(){
        repository.save(new CarroEntity("", 10.0, 2025));
        System.out.println(repository.findAll());
        System.out.println(repository.findById(1L));
        /*
        Retorna:
        []
        Optional.empty
        Pois um mock é uma instancia "falsa" de algo, logo não se comporta como o que deveria ser (neste caso, repository)
        */
    }

    @Test
    void demonstracaoManipulacaoComportamentoMock(){
        Mockito
                .when(repository.findById(1L))
                .thenReturn(Optional.of(new CarroEntity("Teste Mock", 10.0, 2025)));
        Optional<CarroEntity> carroEncontrado = repository.findById(1L);
        System.out.println(carroEncontrado.get().getModelo());
        // podemos manipular o comportamento de um Mock para simular um comportamento
    }



}
