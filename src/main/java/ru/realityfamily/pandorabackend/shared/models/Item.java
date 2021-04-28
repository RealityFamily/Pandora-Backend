package ru.realityfamily.pandorabackend.shared.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Document(collection = "items")
@AllArgsConstructor
public class Item extends BaseMongoTemplate {

    private String name;
    private String description;

    private ModelAccessStrategy modelAccessStrategy = ModelAccessStrategy.Free;

    // mongodb gridFS
    private Set<String> miniPhotoGridFsFileIds;
    private Set<String> photoGridFsFileIds;
    private Set<String> modelGridFsFileIds; // TODO: save the file size to visualize it for user
    //

    @DBRef
    private User authorReference;

    @DBRef
    private Set<Category> categories = new HashSet<>();

    public Item() {
    }



    public Item(Set<String> photoGridFsFileIds, Set<String> modelGridFsFileIds, User authorReference) {
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

    public Item(String name, ModelAccessStrategy modelAccessStrategy) {
        this.name = name;
        this.modelAccessStrategy = modelAccessStrategy;
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
