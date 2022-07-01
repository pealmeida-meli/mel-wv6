package com.example.exemplo.controller;

import com.example.exemplo.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Indica que esta classe é um controller Rest
@RequestMapping("/user") // indica que "/user" é usado para acionar este controller
public class Hello {

    @GetMapping("/ola/{nome}/{id}") // Este método é acionado por uma requisição GET
    public String digaHello(@PathVariable String nome, @PathVariable int id) {
        return "Olá " + nome + ": " + id + "!";
    }

    @GetMapping("/ola") // Este método é acionado por uma requisição GET
    public String digaHello2(@RequestParam String nome, @RequestParam int id) {
        return "Olá " + nome + ": " + id + "!";
    }

    @PostMapping
    public ResponseEntity<UserDto> getUser(@RequestBody User user) {
        if(user.getId() == 123) {
            UserDto userDto = new UserDto(user);
            return new ResponseEntity(userDto, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }
}
