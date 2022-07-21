package br.dh.meli.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "tb_user")
public class UserBD {
    @Id // indica que é chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // indica geração sequencial 1, 2, 3, ...
    private long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(length = 80, nullable = false, unique = true)
    private String email;

}
