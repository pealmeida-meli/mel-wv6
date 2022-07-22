package br.dh.meli.service;

import br.dh.meli.model.UserBD;
import br.dh.meli.repository.IUserBdRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserBdRepo repo;

    @Override
    public Optional<UserBD> getUserById(long id) {
        return repo.findById(id);
    }

    @Override
    public UserBD insertUser(UserBD newUser) {
        if(newUser.getId() > 0) return  null;
        return repo.save(newUser);
    }

    @Override
    public void deleteUser(long id) {
        if(repo.findById(id).isPresent()) {
            repo.deleteById(id);
        }
        // TODO: lançar exception UserNotFound
    }


}
