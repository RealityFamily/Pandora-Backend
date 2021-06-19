package ru.realityfamily.pandorabackend.shared.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@Document(collection = "subtag")
@NoArgsConstructor
public class Subtag {
    @Id
    String id;
    String title;

    @DBRef
    List<Item> itemList;

    public Subtag(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public Subtag(String title) {
        this.title = title;
    }

    @PersistenceConstructor
    public Subtag(String id, String title, List<Item> itemList) {
        this.id = id;
        this.title = title;
        this.itemList = itemList;
    }

    public Subtag(String title, List<Item> itemList) {
        this.title = title;
        this.itemList = itemList;
    }
}
