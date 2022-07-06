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
        return null;
    }


}
