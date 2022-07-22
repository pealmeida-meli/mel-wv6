package br.dh.meli.repository;

import br.dh.meli.model.UserBD;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserBdRepo extends CrudRepository<UserBD, Long> {
    UserBD findByEmail(String email);
    UserBD findByName(String name);
    UserBD findByNameOrEmail(String name, String email);
}