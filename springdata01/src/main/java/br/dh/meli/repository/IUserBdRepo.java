package br.dh.meli.repository;

import br.dh.meli.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserBdRepo extends CrudRepository<AppUser, Long> {
    AppUser findByEmail(String email);
    AppUser findByName(String name);
    AppUser findByNameOrEmail(String name, String email);
}