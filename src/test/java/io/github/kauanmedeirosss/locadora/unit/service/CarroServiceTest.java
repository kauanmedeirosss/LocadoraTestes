package io.github.kauanmedeirosss.locadora.unit.service;

import io.github.kauanmedeirosss.locadora.entity.CarroEntity;
import io.github.kauanmedeirosss.locadora.repository.CarroRepository;
import io.github.kauanmedeirosss.locadora.service.CarroService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Test
    void demonstracaoManipulacao2(){
        var carroParaSalvar = new CarroEntity("Sedan", 10.0, 2025);
        var carroParaRetornar = new CarroEntity("Sedan", 100.0, 2025);

        Mockito.when(repository.save(Mockito.any())).thenReturn(carroParaRetornar);

        var carroSalvo = service.salvar(carroParaSalvar);

        assertNotNull(carroSalvo);
        assertEquals("Sedan", carroSalvo.getModelo());

        // "verifique se está salvando qualquer tipo de objeto"
        Mockito.verify(repository).save(Mockito.any());

        // aqui podemos observar que o comportamento do mockito é tão manipulável ao ponto que,
        // salvamos o carro com valor 10.0 mas o fizemos retornar o carro com valor 100.0
        System.out.println(carroSalvo.getValorDiaria());
    }

    @Test
    void deveSalvarCarro(){
        var carroParaSalvar = new CarroEntity("Sedan", 10.0, 2025);

        Mockito.when(repository.save(Mockito.any())).thenReturn(carroParaSalvar);

        var carroSalvo = service.salvar(carroParaSalvar);

        assertNotNull(carroSalvo);
        assertEquals("Sedan", carroSalvo.getModelo());
        Mockito.verify(repository).save(Mockito.any());
    }

    @Test
    void deveDarErroAoTentarSalvarCarroComDiariaNegativa(){
        var carroParaSalvar = new CarroEntity("Sedan", -10.0, 2025);

        // "E o mockito?" Essa exceção dispara antes mesmo do repository agir,
        // como visto no fluxo do método 'salvar', presente no service
        var erro = catchThrowable( () -> service.salvar(carroParaSalvar) );
        assertThat(erro).isInstanceOf(IllegalArgumentException.class);

        // Mockito.never() verifica se o save não foi chamado de forma alguma
        Mockito.verify(repository, Mockito.never()).save(Mockito.any());

        // para verificar se foi chamado pelo menos uma vez
        //Mockito.verify(repository, Mockito.atLeastOnce()).save(Mockito.any());

        // para verificar se foi chamado pelo menos 10 vezes, ideal para testar loops
        //Mockito.verify(repository, Mockito.atLeast(10)).save(Mockito.any());
    }

}
