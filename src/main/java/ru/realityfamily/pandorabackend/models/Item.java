package ru.realityfamily.pandorabackend.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Setter
@Getter
@Document(collection = "items")
public class Item {
    @Id
    private String id;
    private String name;
    private String description;

    // mongodb gridFS
    private List<String> miniPhotoGridFsFileIds;
    private List<String> photoGridFsFileIds;
    private List<String> modelGridFsFileIds;

    @DBRef
    private User authorReference;

    public Item() {
    }

    public Item(List<String> photoGridFsFileIds, List<String> modelGridFsFileIds, User authorReference) {
        this.photoGridFsFileIds = photoGridFsFileIds;
        this.modelGridFsFileIds = modelGridFsFileIds;
        this.authorReference = authorReference;
    }

    public Item(String description, User authorReference) {
        this.description = description;
        this.authorReference = authorReference;
    }

    public Item(String name) {
        this.name = name;
    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", miniPhotoGridFsFileIds=" + miniPhotoGridFsFileIds +
                ", photoGridFsFileIds=" + photoGridFsFileIds +
                ", modelGridFsFileIds=" + modelGridFsFileIds +
                ", authorReference=" + authorReference +
                '}';
    }
}
