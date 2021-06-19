package ru.realityfamily.pandorabackend.admin.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.realityfamily.pandorabackend.shared.models.Category;
import ru.realityfamily.pandorabackend.shared.models.Subcategory;

import java.util.ArrayList;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoryWithDescriptionDTO {

    private String id;

    private String title;

    private String description;

    private int childCount;

    public SubcategoryWithDescriptionDTO(Subcategory subcategory) {
        this.id = subcategory.getId();
        this.title = subcategory.getTitle();
        this.description= subcategory.getDescription();
        this.childCount = Optional.ofNullable(subcategory.getInlineInterfaceTag()).orElse(new ArrayList<>()).size();
    }
}
