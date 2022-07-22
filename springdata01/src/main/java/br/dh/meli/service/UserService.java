package br.dh.meli.service;

import br.dh.meli.dto.UpdateUserRequest;
import br.dh.meli.exception.BadRequestException;
import br.dh.meli.exception.UserNotFoundException;
import br.dh.meli.model.AppUser;
import br.dh.meli.repository.IAppUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IAppUserRepository repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public AppUser getUserById(long id) {
        return repo.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado. id: " + id));
    }

    @Override
    public AppUser insertUser(AppUser newUser) {
        if (newUser.getId() != null) {
            throw new BadRequestException("O usuário não pode ter Id para ser inseridos");
        }
        return repo.save(newUser);
    }

    @Override
    public AppUser update(AppUser user) {
        getUserById(user.getId());
        return repo.save(user);
    }

    @Override
    public AppUser updatePartial(long id, UpdateUserRequest changes) {
        AppUser userFound = getUserById(id);
        mapper.map(changes, userFound);
        return repo.save(userFound);
    }


    @Override
    public void deleteUser(long id) {
        AppUser userFound = getUserById(id);
        repo.delete(userFound);
    }

    @Override
    public List<AppUser> listAll() {
        return (List<AppUser>) repo.findAll();
    }

    @Override
    public AppUser findByEmail(String email) {
        return repo.findByEmail(email);
    }

}
