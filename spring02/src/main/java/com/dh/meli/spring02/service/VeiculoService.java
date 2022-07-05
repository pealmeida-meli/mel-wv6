package com.dh.meli.spring02.service;

import com.dh.meli.spring02.dto.VeiculoDto;
import com.dh.meli.spring02.model.Veiculo;

import java.util.List;

public interface VeiculoService {
    VeiculoDto getVeiculo(String placa);
    List<VeiculoDto> getAllVeiculo();
    void saveVeiculo(Veiculo novoVeiculo);
    List<VeiculoDto> getAllOrderByValor();
    List<VeiculoDto> getAllOrderByModelo();
    List<VeiculoDto> getByModelo(String modelo);
}
