package io.github.kauanmedeirosss.locadora.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "carros")
@Data
public class CarroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private Double valorDiaria;

    public CarroEntity(String modelo, Double valorDiaria) {
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
    }

}
