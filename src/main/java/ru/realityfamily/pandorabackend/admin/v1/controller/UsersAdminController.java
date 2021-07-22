package ru.realityfamily.pandorabackend.admin.v1.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import ru.realityfamily.pandorabackend.admin.v1.service.IUserAdminService;
import ru.realityfamily.pandorabackend.shared.models.Role;
import ru.realityfamily.pandorabackend.shared.models.User;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin")
public class UsersAdminController {
    IUserAdminService userAdminService;

    @GetMapping("/roles")
    List<Role> getValidRoles() {
        Role[] roles = Role.values();
        return Arrays.stream(roles).map(role -> role).collect(Collectors.toList());
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userAdminService.getAllUsers();
    }

    @PostMapping("/users/{id}/setEnabled")
    User enableUserByAdmin(@PathVariable @NotNull String id, @RequestParam("enabled") @NotNull Boolean enabled){
        return userAdminService.enableUser(id, enabled);
    }

    @GetMapping("users/search/{nameOrEmail}")
    List<User> findUserWithEmailOrNickname(@PathVariable String nameOrEmail, HttpServletResponse response) {
        List<User> foundUsers = new ArrayList<>();
        if(nameOrEmail.contains("@")){
            foundUsers.add(userAdminService.getUsersByMail(nameOrEmail));
        } else foundUsers.add(userAdminService.foundUserByNickname(nameOrEmail));
        for( User u : foundUsers){
            if(u== null){
                response.setStatus(HttpStatus.NO_CONTENT.value());
            }
        }
        return foundUsers;
    }

}
