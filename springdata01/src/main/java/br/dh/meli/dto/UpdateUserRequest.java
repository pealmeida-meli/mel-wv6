package br.dh.meli.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

//@Getter
//@Setter
public class UpdateUserRequest {
    @Size(min = 3, max = 50)
    public String name;

    @Email
    public String email;

    public String cpf;
}
