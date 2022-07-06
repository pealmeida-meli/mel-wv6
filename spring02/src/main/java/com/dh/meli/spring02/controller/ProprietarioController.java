package com.dh.meli.spring02.controller;

import com.dh.meli.spring02.model.Proprietario;
import com.dh.meli.spring02.service.ProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/proprietario")
public class ProprietarioController {

    @Autowired
    private ProprietarioService service;

    @GetMapping("/all")
    public ResponseEntity<List<Proprietario>> getAllVeiculo() {
        List<Proprietario> lista = service.getAllProprietario();
        return ResponseEntity.ok(lista);
    }

}
