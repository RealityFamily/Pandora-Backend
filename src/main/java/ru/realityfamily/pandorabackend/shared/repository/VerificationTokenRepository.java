package ru.realityfamily.pandorabackend.shared.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.realityfamily.pandorabackend.shared.models.User;
import ru.realityfamily.pandorabackend.shared.models.VerificationToken;

public interface VerificationTokenRepository extends MongoRepository<VerificationToken, String> {
    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
