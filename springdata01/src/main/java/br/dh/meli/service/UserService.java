package br.dh.meli.service;

import br.dh.meli.model.UserBD;
import br.dh.meli.repository.IUserBdRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private IUserBdRepo repo;

    public UserBD getUserById(long id) {
        return (UserBD) repo.findById(id).orElse(null);
    }
}
