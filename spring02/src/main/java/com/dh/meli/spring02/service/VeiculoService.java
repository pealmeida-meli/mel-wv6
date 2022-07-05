package com.dh.meli.spring02.service;

import com.dh.meli.spring02.model.Veiculo;

import java.util.List;

public interface VeiculoService {
    Veiculo getVeiculo(String placa);
    List<Veiculo> getAllVeiculo();
    void saveVeiculo(Veiculo novoVeiculo);
}
