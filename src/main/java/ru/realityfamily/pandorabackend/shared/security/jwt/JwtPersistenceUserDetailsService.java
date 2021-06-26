package ru.realityfamily.pandorabackend.shared.security.jwt;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.realityfamily.pandorabackend.shared.models.Role;
import ru.realityfamily.pandorabackend.shared.models.User;
import ru.realityfamily.pandorabackend.shared.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("database")
@AllArgsConstructor
@Transactional
public class JwtPersistenceUserDetailsService implements UserDetailsService {

  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> userOpt = userRepository.findByNickname(username);
    if (!userOpt.isPresent()){
      userOpt = userRepository.findByMail(username);
    }
    if(!userOpt.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }
    User user = userOpt.get();

    return new JwtUserDetails(user.getId(), user.getNickname(), user.getPasswordHash(), user.getRole().stream().map(role -> {
      return (role.name());
    }).collect(Collectors.toList()) );

  }

}


