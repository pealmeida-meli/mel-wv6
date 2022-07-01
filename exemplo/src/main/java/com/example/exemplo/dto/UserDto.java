package com.example.exemplo.dto;

import com.example.exemplo.controller.User;

public class UserDto {
    private String nome;

    public UserDto(User user) {
        this.nome = user.getNome();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
