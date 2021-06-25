package ru.realityfamily.pandorabackend.user.v1.service;

import ru.realityfamily.pandorabackend.mvc.userregistration.dto.UserDto;
import ru.realityfamily.pandorabackend.shared.models.User;
import ru.realityfamily.pandorabackend.user.v1.service.exceptions.UserAlreadyExistException;

public interface IUserClientService {
    User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException;
}
