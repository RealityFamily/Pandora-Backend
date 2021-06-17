package ru.realityfamily.pandorabackend.admin.v1.service;

import ru.realityfamily.pandorabackend.shared.models.Role;
import ru.realityfamily.pandorabackend.shared.models.User;

import java.util.List;

public interface IUserAdminService {
    List<User> getAllUsersWithRole(Role role);

    List<User> getAllUsers();

    User getUserById(String id);

    User getUsersByMail(String mail);

    User createNewUser(User user);

    User foundUserByNickname(String nickname);
}
