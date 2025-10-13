package io.github.kauanmedeirosss.locadora.functional.web;

import io.github.kauanmedeirosss.locadora.controller.CarroController;
import io.github.kauanmedeirosss.locadora.entity.CarroEntity;
import io.github.kauanmedeirosss.locadora.exception.EntityNotFoundException;
import io.github.kauanmedeirosss.locadora.service.CarroService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(CarroController.class)
class CarroControllerTest {

    // serve como cliente http que vai realizar os testes da API
    @Autowired
    MockMvc mvc;

    // o fato do service estar mockado, faz desse teste integrado
    @MockitoBean
    CarroService service;

    @Test
    void deveSalvarCarro() throws Exception{
        // 1. Cenário
        var carro = new CarroEntity(1L, "Lancer", 150.0, 2025);

        Mockito.when(service.salvar(Mockito.any())).thenReturn(carro);

        String json = """
                {
                    "modelo": "Lancer",
                    "valorDiaria": 150.0,
                    "ano": 2025
                }
                """;

        // 2. Execução
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders
                        .post("/carros") // requisição para
                        .contentType(MediaType.APPLICATION_JSON) // tipo de conteúdo
                        .content(json) // body/conteúdo
        );

        // 3. Verificação
        result
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.modelo").value("Lancer"));
    }

    @Test
    void deveBuscarCarro() throws Exception{
        Mockito.when(service.buscar(Mockito.any()))
                .thenReturn(new CarroEntity(1L, "Lancer", 120.0, 2025));

        // Execução e verificação numa lapada só
        mvc.perform(
                MockMvcRequestBuilders.get("/carros/1")
        ).andExpect(MockMvcResultMatchers.status().isOk())
         .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
         .andExpect(MockMvcResultMatchers.jsonPath("$.modelo").value("Lancer"))
         .andExpect(MockMvcResultMatchers.jsonPath("$.valorDiaria").value(120.0))
         .andExpect(MockMvcResultMatchers.jsonPath("$.ano").value(2025));
    }

    @Test
    void deveDarErroAoBuscar() throws Exception{
        Mockito.when(service.buscar(Mockito.any())).thenThrow(EntityNotFoundException.class);

        mvc.perform(
                        MockMvcRequestBuilders.get("/carros/1")
                ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void deveListarCarros() throws Exception{
        var listagem = List.of(
                new CarroEntity(1L, "Civic", 150.0, 2025),
                new CarroEntity(2L, "Lancer", 150.0, 2025),
                new CarroEntity(3L, "Eclipse", 180.0, 2025),
                new CarroEntity(4L, "Supra", 200.0, 2025)
        );

        Mockito.when(service.listar()).thenReturn(listagem);

        mvc.perform(MockMvcRequestBuilders.get("/carros")
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].modelo").value("Civic"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].modelo").value("Lancer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].modelo").value("Eclipse"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].modelo").value("Supra"));
    }

    @Test
    void deveAtualizarCarro() throws Exception{
        Mockito.when(service.atualizar(Mockito.any(), Mockito.any()))
                .thenReturn(new CarroEntity(1L, "Civic", 150.0, 2024));

        String json = """
                {
                    "modelo": "Civic",
                    "valorDiaria": 150.0,
                    "ano": 2024
                }
                """;

        mvc.perform(MockMvcRequestBuilders.put("/carros/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void deveDarErroAoAtualizar() throws Exception{
        Mockito.when(service.atualizar(Mockito.any(), Mockito.any()))
                .thenThrow(EntityNotFoundException.class);

        String json = """
                {
                    "modelo": "Civic",
                    "valorDiaria": 150.0,
                    "ano": 2024
                }
                """;

        mvc.perform(MockMvcRequestBuilders.put("/carros/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void deveDeletarCarro() throws Exception{
        Mockito.doNothing().when(service).deletar(Mockito.any());

        mvc.perform(MockMvcRequestBuilders.delete("/carros/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void deveDarErroDeletarCarro() throws Exception{
        Mockito.doThrow(EntityNotFoundException.class).when(service).deletar(Mockito.any());

        mvc.perform(MockMvcRequestBuilders.delete("/carros/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
