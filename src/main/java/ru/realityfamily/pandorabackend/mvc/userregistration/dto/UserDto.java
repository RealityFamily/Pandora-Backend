package ru.realityfamily.pandorabackend.mvc.userregistration.dto;

import lombok.Getter;
import lombok.Setter;
import ru.realityfamily.pandorabackend.mvc.userregistration.validation.PasswordMatches;
import ru.realityfamily.pandorabackend.mvc.userregistration.validation.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@PasswordMatches
public class UserDto {
    @NotNull
    @NotEmpty
    private String nickName;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;
}
