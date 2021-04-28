package ru.realityfamily.pandorabackend.shared.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document(collection = "subcategory")
@Getter
@Setter
public class Subcategory {
    @Id
    private String id;
    private String title;
    private String description;

    @DBRef
    private List<Subtag> inlineInterfaceTag;


    public Subcategory(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @PersistenceConstructor
    public Subcategory(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Subcategory(String title, String description, List<Subtag> inlineInterfaceTag) {
        this.title = title;
        this.description = description;
        this.inlineInterfaceTag = inlineInterfaceTag;
    }
}
