package br.dh.meli.repository;

import br.dh.meli.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findByEmail(String email);
}
