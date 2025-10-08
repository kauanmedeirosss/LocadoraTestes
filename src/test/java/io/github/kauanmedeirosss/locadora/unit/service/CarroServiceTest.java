package io.github.kauanmedeirosss.locadora.unit.service;

import io.github.kauanmedeirosss.locadora.entity.CarroEntity;
import io.github.kauanmedeirosss.locadora.exception.EntityNotFoundException;
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

import java.util.List;
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

    @Test
    void deveAtualizarCarro(){
        //Cenário
        // EXISTE um carro antigo no banco de dados
        Long id = 1L;
        var carroAntigo = new CarroEntity("Gol", 50.0, 2025);
        carroAntigo.setId(id);

        // O USUÁRIO quer atualizar para estes NOVOS dados
        var novosDados = new CarroEntity("Voyage", 80.0, 2025); // Dados atualizados

        // Como o sistema DEVE responder após a atualização
        var carroAtualizado = new CarroEntity("Voyage", 80.0, 2025);
        carroAtualizado.setId(id);

        // mocks
        // Quando alguém buscar pelo ID 1, retorne o carro antigo
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(carroAntigo));
        // Quando alguém salvar qualquer carro, retorne o carro atualizado
        Mockito.when(repository.save(Mockito.any())).thenReturn(carroAtualizado);

        var resultado = service.atualizar(id, novosDados);

        // ASSERT
        assertEquals("Voyage", resultado.getModelo()); // Novo nome
        assertEquals(80.0, resultado.getValorDiaria()); // Novo valor
        assertEquals(2025, resultado.getAno()); // Ano mantido

        // VERIFY
        Mockito.verify(repository).findById(id);
        Mockito.verify(repository).save(Mockito.any());
    }

    @Test
    void deveDarErroAoTentarAtualizarCarroInexistente(){
        Long id = 1L;
        var carro = new CarroEntity("Gol", 50.0, 2025);

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        var erro = catchThrowable(() -> service.atualizar(id, carro));

        assertThat(erro).isInstanceOf(EntityNotFoundException.class);
        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void deveDeletarCarro(){
        Long id = 1L;
        var carro = new CarroEntity("Gol", 50.0, 2025);

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(carro));

        service.deletar(id);

        Mockito.verify(repository, Mockito.times(1)).delete(carro);
    }

    @Test
    void deveDarErroAoTentarDeletarCarroInexistente(){
        Long id = 1L;

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        var erro = catchThrowable(() -> service.deletar(id));

        assertThat(erro).isInstanceOf(EntityNotFoundException.class);
        Mockito.verify(repository, Mockito.never()).delete(Mockito.any());
    }

    @Test
    void deveBuscarCarro(){
        Long id = 1L;
        var carro = new CarroEntity("Gol", 50.0, 2025);

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(carro));

        var carroEncontrado = service.buscar(id);

        assertThat(carroEncontrado.getModelo()).isEqualTo("Gol");
        assertThat(carroEncontrado.getValorDiaria()).isEqualTo(50.0);
        assertThat(carroEncontrado.getAno()).isEqualTo(2025);
    }

    @Test
    void deveDarErroAoTentarBuscarCarroInexistente(){
        Long id = 1L;

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        var erro = catchThrowable(() -> service.buscar(id));

        assertThat(erro).isInstanceOf(EntityNotFoundException.class);
        Mockito.verify(repository).findById(id);
    }

    @Test
    void deveListarTodosOsCarros(){
        var carro1 = new CarroEntity(1L,"Gol", 50.0, 2025);
        var carro2 = new CarroEntity(2L,"Sedan", 100.0, 2025);
        var carro3 = new CarroEntity(3L,"Sedan", 80.0, 2023);

        var lista = List.of(carro1, carro2, carro3);

        Mockito.when(repository.findAll()).thenReturn(lista);

        List<CarroEntity> resultado = service.listar();

        assertThat(resultado).hasSize(3);
        Mockito.verify(repository, Mockito.times(1)).findAll();
        // verifica se não houveram mais interações com esse mock
        Mockito.verifyNoMoreInteractions(repository);
    }

}
