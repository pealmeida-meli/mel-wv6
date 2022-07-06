package com.dh.meli.spring02.repository;

import com.dh.meli.spring02.model.Proprietario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Repository
public class ProprietarioRepo {
    private final String linkFile = "src/main/resources/proprietarios.json";

    public List<Proprietario> getAllProprietario() {
        ObjectMapper mapper = new ObjectMapper();
        List<Proprietario> lista = null;
        try {
            lista = Arrays.asList
                    (mapper.readValue(new File(linkFile), Proprietario[].class));

        } catch (Exception ex) {
            System.out.println("Erro no aquivo " + linkFile);
        }
        return lista;
    }
}
