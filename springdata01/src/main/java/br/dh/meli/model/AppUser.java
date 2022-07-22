package br.dh.meli.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Getter
@Setter
@Table(name = "tb_user")
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    @Size(min = 3, max = 50)
    private String name;

    @Column(length = 80, nullable = false, unique = true)
    @Email
    private String email;
}
