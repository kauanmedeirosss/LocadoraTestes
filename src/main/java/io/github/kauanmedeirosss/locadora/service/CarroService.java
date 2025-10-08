package io.github.kauanmedeirosss.locadora.service;

import io.github.kauanmedeirosss.locadora.entity.CarroEntity;
import io.github.kauanmedeirosss.locadora.exception.EntityNotFoundException;
import io.github.kauanmedeirosss.locadora.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository repository;

    public CarroEntity salvar(CarroEntity carro){
        if (carro.getValorDiaria() <= 0){
            throw new IllegalArgumentException("Preço da diária não pode ser zero ou negativo!");
        }
        return repository.save(carro);
    }

    public CarroEntity atualizar(Long id, CarroEntity carroAtualizado){
        var carroExistente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado!"));
        carroExistente.setAno(carroAtualizado.getAno());
        carroExistente.setModelo(carroAtualizado.getModelo());
        carroExistente.setValorDiaria(carroAtualizado.getValorDiaria());
        return repository.save(carroExistente);
    }

    public void deletar(Long id){
        var carroExistente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado!"));
        repository.delete(carroExistente);
    }

    public CarroEntity buscar(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado!"));
    }

    public List<CarroEntity> listar(){
        return repository.findAll();
    }

}
