package ru.realityfamily.pandorabackend.shared.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.realityfamily.pandorabackend.shared.models.Subtag;

public interface SubtagRepository extends MongoRepository<Subtag, String> {
}
