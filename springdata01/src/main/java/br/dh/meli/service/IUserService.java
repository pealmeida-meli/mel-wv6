package br.dh.meli.service;

import br.dh.meli.model.UserBD;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<UserBD> getUserById(long id);
    UserBD insertUser(UserBD newUser);
    void deleteUser(long id);
    List<UserBD> listAll();
}
