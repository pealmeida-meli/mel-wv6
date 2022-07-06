package com.dh.meli.spring02.service;

import com.dh.meli.spring02.model.Proprietario;
import com.dh.meli.spring02.model.Veiculo;
import com.dh.meli.spring02.repository.ProprietarioRepo;
import com.dh.meli.spring02.repository.VeiculoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProprietarioServiceImp implements ProprietarioService {

    @Autowired
    private ProprietarioRepo proprietarioRepo;

    @Autowired
    private VeiculoRepo veiculoRepo;

    @Override
    public List<Proprietario> getAllProprietario() {
        List<Veiculo> listaVeiculos = new ArrayList<>(veiculoRepo.getAllVeiculo());
        List<Proprietario> listaProprietarios = proprietarioRepo.getAllProprietario();
        AtomicInteger cont = new AtomicInteger();
//        listaProprietarios.stream()
//                .forEach(proprietario ->
//                        listaVeiculos.forEach(veiculo -> {
//                    if(veiculo.getId_proprietario() == proprietario.getId()){
//                        proprietario.addVeiculo(veiculo);
//                    }
//                }));

        listaProprietarios.stream()
                .forEach(proprietario -> {
                    for (int i = 0; i < listaVeiculos.size(); i++) {
                        cont.getAndIncrement();
                        if(listaVeiculos.get(i).getId_proprietario() == proprietario.getId()){
                            proprietario.addVeiculo(listaVeiculos.remove(i));
                            i--;
                        }
                    }
                });
        System.out.println("Contagem:" + cont);
        return listaProprietarios;
    }


}
