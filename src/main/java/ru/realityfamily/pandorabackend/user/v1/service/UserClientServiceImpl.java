package ru.realityfamily.pandorabackend.user.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.realityfamily.pandorabackend.mvc.userregistration.dto.UserDto;
import ru.realityfamily.pandorabackend.shared.models.Role;
import ru.realityfamily.pandorabackend.shared.models.User;
import ru.realityfamily.pandorabackend.shared.repository.UserRepository;
import ru.realityfamily.pandorabackend.user.v1.service.exceptions.UserAlreadyExistException;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class UserClientServiceImpl implements IUserClientService {

    private UserRepository userRepository;

    @Override
    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
        if (emailExist(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmail());
        }

        User user = new User();
        user.setNickname(userDto.getNickName());
        user.setMail(userDto.getEmail());
        String password = userDto.getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        user.setPasswordHash(password);
        user.setRole(Arrays.asList(Role.User));
        // the rest registration operation
        return userRepository.save(user);
    }

    public boolean emailExist(String email) {
        return userRepository.findByMail(email).isPresent();
    }
}
