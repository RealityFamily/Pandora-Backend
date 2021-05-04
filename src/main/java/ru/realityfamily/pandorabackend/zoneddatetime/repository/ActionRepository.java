package ru.realityfamily.pandorabackend.zoneddatetime.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.realityfamily.pandorabackend.zoneddatetime.model.Action;

public interface ActionRepository extends MongoRepository<Action, String> { }
