package ru.realityfamily.pandorabackend.shared.security.jwt;

import java.util.*;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("inmemory")
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

  static {
    //System.out.println(new BCryptPasswordEncoder().encode("admin"));
    inMemoryUserList.add(new JwtUserDetails("1", "admin",
        "$2a$10$OAOb9zOPZe6GKVQXARCot.ApdBr297OU0orKRlIiz1hKHjhVrBUaK", Arrays.asList("ROLE_USER_2"), true));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
        .filter(user -> user.getUsername().equals(username)).findFirst();

    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }

    return findFirst.get();
  }

}


