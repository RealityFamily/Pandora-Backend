package ru.realityfamily.pandorabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.realityfamily.pandorabackend.models.User;
import ru.realityfamily.pandorabackend.repository.UserRepository;

@RestController
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable String id){
        return userRepository.findById(id).get();
    }

    @PostMapping("/user/")
    User postUser(User user){

        return userRepository.insert(user);
    }
}
