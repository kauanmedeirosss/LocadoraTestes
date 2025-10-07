package io.github.kauanmedeirosss.locadora.repository;

import io.github.kauanmedeirosss.locadora.entity.CarroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<CarroEntity, Long> {
}