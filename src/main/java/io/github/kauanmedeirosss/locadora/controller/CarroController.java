package io.github.kauanmedeirosss.locadora.controller;

import io.github.kauanmedeirosss.locadora.entity.CarroEntity;
import io.github.kauanmedeirosss.locadora.exception.EntityNotFoundException;
import io.github.kauanmedeirosss.locadora.service.CarroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// controller simples e sem uso de DTO já que o foco é teste
@RestController
@RequestMapping("/carros")
public class CarroController {

    private final CarroService service;

    public CarroController(CarroService service) {
        this.service = service;
    }

    // Retorna Object já que pode retornar mensagem de erro (String) ou o objeto salvo (CarroEntity)
    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody CarroEntity carro){
        try{
            var carroSalvo = service.salvar(carro);
            return ResponseEntity.status(HttpStatus.CREATED).body(carroSalvo);
        } catch (IllegalArgumentException e){ // tratando exception que pode ser lançada pelo service
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroEntity> buscar(@PathVariable("id") Long id){
        try{
            var carroBuscado = service.buscar(id);
            return ResponseEntity.ok(carroBuscado);
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

}
