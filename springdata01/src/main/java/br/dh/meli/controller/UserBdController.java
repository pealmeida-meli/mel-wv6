package br.dh.meli.controller;

import br.dh.meli.dto.UpdateUserRequest;
import br.dh.meli.model.AppUser;
import br.dh.meli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserBdController {

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> buscaPorId(@PathVariable long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AppUser> buscaPorId(@PathVariable String email) {
        return ResponseEntity.ok(service.findByEmail(email));
    }

    @PostMapping
    public ResponseEntity<Object> insertNewUser(@Valid @RequestBody AppUser user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(new Object() {
                @SuppressWarnings("unused")
                public final String title = "Input invalid";
                @SuppressWarnings("unused")
                public final List<Map<String, Object>> errors = result.getFieldErrors().stream().map(fieldError -> {
                    var map = new HashMap<String, Object>();
                    map.put("field", fieldError.getField());
                    map.put("rejectedValue", fieldError.getRejectedValue());
                    map.put("message", fieldError.getDefaultMessage());
                    return map;
                }).collect(Collectors.toList());
                @SuppressWarnings("unused")
                public final int httpStatus = HttpStatus.BAD_REQUEST.value();
            });
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insertUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUSer(@PathVariable long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AppUser>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @PutMapping
    public ResponseEntity<AppUser> updateUser(@RequestBody AppUser user) {
        return ResponseEntity.ok(service.update(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable long id, @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(service.updatePartial(id, request));
    }

}
