package ru.realityfamily.pandorabackend.shared.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.realityfamily.pandorabackend.shared.models.Role;
import ru.realityfamily.pandorabackend.shared.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByMail(String mail);

    Optional<User> findByNickname(String nickname);

    Optional<List<User>> findAllUsersByRole(Role role);

}

