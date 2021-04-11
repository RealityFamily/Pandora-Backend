package ru.realityfamily.pandorabackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.realityfamily.pandorabackend.models.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByMail(String mail);

    Optional<User> findByNickname(String nickname);


}

