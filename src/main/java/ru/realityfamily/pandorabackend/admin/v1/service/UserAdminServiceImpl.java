package ru.realityfamily.pandorabackend.admin.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.realityfamily.pandorabackend.shared.models.Role;
import ru.realityfamily.pandorabackend.shared.models.User;
import ru.realityfamily.pandorabackend.shared.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserAdminServiceImpl implements IUserAdminService {
    UserRepository userRepository;

    @Override
    public List<User> getAllUsersWithRole(Role role) {
        return userRepository.findAllUsersByRole(role).get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User getUsersByMail(String mail) {
        Optional<User> user = userRepository.findByMail(mail);
        if(user.isPresent()){
            return user.get();
        } else return null;
    }


    @Override
    public User createNewUser(User user) {
        return null;
    } //TODO

    @Override
    public User foundUserByNickname(String nickname) {
        Optional<User> user = userRepository.findByNickname(nickname);
        if(user.isPresent()){
            return user.get();
        } else return null;
    }
}
