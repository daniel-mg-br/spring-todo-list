package com.danielgarcia.spring_todo_list.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody UserModel user) {
        return user.getName() + " criado!";
    }
}
