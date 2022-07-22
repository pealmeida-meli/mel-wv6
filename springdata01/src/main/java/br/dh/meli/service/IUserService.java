package br.dh.meli.service;

import br.dh.meli.dto.UpdateUserRequest;
import br.dh.meli.model.AppUser;

import java.util.List;

public interface IUserService {
    AppUser getUserById(long id);
    AppUser insertUser(AppUser newUser);
    AppUser update(AppUser user);
    AppUser updatePartial(long id, UpdateUserRequest changes);
    void deleteUser(long id);
    List<AppUser> listAll();
    AppUser findByEmail(String email);
}
