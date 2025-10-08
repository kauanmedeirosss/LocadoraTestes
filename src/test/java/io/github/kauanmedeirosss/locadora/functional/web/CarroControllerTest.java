package io.github.kauanmedeirosss.locadora.functional.web;

import io.github.kauanmedeirosss.locadora.controller.CarroController;
import io.github.kauanmedeirosss.locadora.entity.CarroEntity;
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

}
