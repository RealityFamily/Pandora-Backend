package ru.realityfamily.pandorabackend.user.v1.service;

import ru.realityfamily.pandorabackend.mvc.userregistration.dto.UserDto;
import ru.realityfamily.pandorabackend.shared.models.User;
import ru.realityfamily.pandorabackend.shared.models.VerificationToken;
import ru.realityfamily.pandorabackend.user.v1.service.exceptions.UserAlreadyExistException;

public interface IUserClientService {
    User registerNewUserAccount(UserDto userDto);

    User getUser(String verificationToken);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String token);

    void saveRegistredUser(User user);
}
