package com.danielgarcia.spring_todo_list.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody UserModel user) {
        var userTry = userRepository.findByUsername(user.getUsername());

        if (userTry != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body("Usuário já cadastrado!");
        }

        var hashPassword = BCrypt.withDefaults().
                hashToString(12, user.getPassword().toCharArray());

        user.setPassword(hashPassword);

        var userCreated = this.userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
