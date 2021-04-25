package ru.realityfamily.pandorabackend.shared.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@Document(collection = "category")
public class Category {
    @Id
    private String id;
    private String title;
    private String description;
    @DBRef
    private List<Subcategory> subcategorys;

    public Category(String title, String description, List<Subcategory> subcategorys) {
        this.title = title;
        this.description = description;
        this.subcategorys = subcategorys;
    }

    public Category(String id, String title, String description) { // debug constructor
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @PersistenceConstructor
    public Category(String id, String title, String description, List<Subcategory> subcategorys) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.subcategorys = subcategorys;
    }


}
