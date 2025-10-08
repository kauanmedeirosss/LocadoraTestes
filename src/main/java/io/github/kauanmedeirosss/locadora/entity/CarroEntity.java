package io.github.kauanmedeirosss.locadora.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carros")
@Data
public class CarroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private Double valorDiaria;
    private Integer ano;

    public CarroEntity(String modelo, Double valorDiaria, Integer ano) {
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
        this.ano = ano;
    }

    public CarroEntity(Long id, String modelo, Double valorDiaria, Integer ano){
        this(modelo, valorDiaria, ano);
        this.id = id;
    }

    public CarroEntity() {
    }

}
