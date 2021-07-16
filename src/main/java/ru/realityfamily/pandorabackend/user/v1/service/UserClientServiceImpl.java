package ru.realityfamily.pandorabackend.user.v1.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.realityfamily.pandorabackend.mvc.userregistration.dto.UserDto;
import ru.realityfamily.pandorabackend.shared.models.Role;
import ru.realityfamily.pandorabackend.shared.models.User;
import ru.realityfamily.pandorabackend.shared.models.VerificationToken;
import ru.realityfamily.pandorabackend.shared.repository.UserRepository;
import ru.realityfamily.pandorabackend.shared.repository.VerificationTokenRepository;
import ru.realityfamily.pandorabackend.user.v1.service.exceptions.UserAlreadyExistException;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Calendar;

@Service
@Transactional
@AllArgsConstructor
public class UserClientServiceImpl implements IUserClientService {

    public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";

    private UserRepository userRepository;

    private VerificationTokenRepository verificationTokenRepository;

    @Override
    public User registerNewUserAccount(UserDto userDto){
        if (emailExist(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmail());
        }
        if(nicknameExist(userDto.getNickName())){
            throw new UserAlreadyExistException("There is an account with that login address: " + userDto.getNickName());
        }

        // the rest registration operation
        User user = new User();
        user.setNickname(userDto.getNickName());
        user.setMail(userDto.getEmail());
        String password = userDto.getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        user.setPasswordHash(password);
        user.setRole(Arrays.asList(Role.User));

        return userRepository.save(user);
    }

    @Override
    public User getUser(String verificationToken) {
        User user = verificationTokenRepository.findByToken(verificationToken).getUser();
        return user;
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken anotherToken = new VerificationToken(token,user);
        verificationTokenRepository.save(anotherToken);
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Override
    public void saveRegistredUser(User user) {
        userRepository.save(user);
    }

    @Override
    public String validateVerificationToken(String token) {
        final VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken == null) {
            return TOKEN_INVALID;
        }

        final User user = verificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate()
                .getTime() - cal.getTime()
                .getTime()) <= 0) {
            verificationTokenRepository.delete(verificationToken);
            return TOKEN_EXPIRED;
        }

        user.setEnabled(true);
        verificationTokenRepository.delete(verificationToken);

        userRepository.save(user);
        return TOKEN_VALID;
    }

    private boolean nicknameExist(String nickName) {
        return userRepository.findByNickname(nickName).isPresent();
    }

    public boolean emailExist(String email) {
        return userRepository.findByMail(email).isPresent();
    }
}
