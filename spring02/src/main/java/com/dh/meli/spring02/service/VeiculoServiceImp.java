package com.dh.meli.spring02.service;

import com.dh.meli.spring02.model.Veiculo;
import com.dh.meli.spring02.repository.VeiculoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoServiceImp implements VeiculoService {

    @Autowired
    private VeiculoRepo repo;

    @Override
    public Veiculo getVeiculo(String placa) {
        return repo.getVeiculo(placa);
    }

    @Override
    public List<Veiculo> getAllVeiculo() {
        return repo.getAllVeiculo();
    }

    @Override
    public void saveVeiculo(Veiculo novoVeiculo) {
        repo.saveVeiculo(novoVeiculo);
    }

}
