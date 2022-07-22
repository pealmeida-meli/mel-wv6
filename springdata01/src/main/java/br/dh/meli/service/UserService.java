package br.dh.meli.service;

import br.dh.meli.exception.BadRequestException;
import br.dh.meli.exception.UserNotFoundException;
import br.dh.meli.model.UserBD;
import br.dh.meli.repository.IUserBdRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserBdRepo repo;

    @Override
    public UserBD getUserById(long id) {
        return repo.findById(id).orElseThrow(()-> new UserNotFoundException("Usuário não encontrado. id: " + id));
    }

    @Override
    public UserBD insertUser(UserBD newUser) {
        if(newUser.getId() > 0) {
            throw new BadRequestException("O usuário não pode ter Id para ser inseridos");
        };
        return repo.save(newUser);
    }

    @Override
    public UserBD update(UserBD user) {
        UserBD userFound = getUserById(user.getId());

        return repo.save(user);
    }

    @Override
    public UserBD updatePartial(long id, Map<String, String> changes) {
        UserBD userFound = getUserById(id);

        changes.forEach( (atributo, valor)-> {
            switch (atributo){
                case "name": userFound.setName(valor); break;
                case "email": userFound.setEmail(valor); break;
            }
        });

        return repo.save(userFound);
    }


    @Override
    public void deleteUser(long id) {
        UserBD userFound = getUserById(id);
        repo.delete(userFound);
    }

    @Override
    public List<UserBD> listAll() {
        return (List<UserBD>) repo.findAll();
    }

    @Override
    public UserBD findByEmail(String email) {
        return repo.findByEmail(email);
    }

}
