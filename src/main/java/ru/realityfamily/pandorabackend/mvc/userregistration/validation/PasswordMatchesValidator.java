package ru.realityfamily.pandorabackend.mvc.userregistration.validation;

import ru.realityfamily.pandorabackend.mvc.userregistration.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        UserDto user = (UserDto) value;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
