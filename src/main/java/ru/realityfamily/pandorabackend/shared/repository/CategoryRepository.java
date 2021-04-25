package ru.realityfamily.pandorabackend.shared.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.realityfamily.pandorabackend.shared.models.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

}
