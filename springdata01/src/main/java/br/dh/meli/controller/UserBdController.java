package br.dh.meli.controller;

import br.dh.meli.model.UserBD;
import br.dh.meli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserBdController {

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserBD> buscaPorId(@PathVariable long id) {
        UserBD userFound = service.getUserById(id);

        if(userFound != null) {
            return ResponseEntity.ok(userFound);
        }
        return ResponseEntity.notFound().build();
    }

}
