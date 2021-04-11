package ru.realityfamily.pandorabackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.realityfamily.pandorabackend.models.Item;

import java.util.Optional;

public interface ItemRepository extends MongoRepository<Item, String> {

    Optional<Item> findByName(String name);

    Optional<Item> findByDescription(String description);

}
