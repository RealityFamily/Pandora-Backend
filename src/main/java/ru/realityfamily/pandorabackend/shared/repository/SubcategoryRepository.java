package ru.realityfamily.pandorabackend.shared.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.realityfamily.pandorabackend.shared.models.Subcategory;

public interface SubcategoryRepository extends MongoRepository<Subcategory, String> {

}
