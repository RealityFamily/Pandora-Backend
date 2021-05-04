package ru.realityfamily.pandorabackend.user.v1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.realityfamily.pandorabackend.shared.models.User;
import ru.realityfamily.pandorabackend.shared.repository.UserRepository;

@RestController
public class UserClientController {

    private UserRepository userRepository;

    public UserClientController(UserRepository userRepository) {
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
