package ru.realityfamily.pandorabackend.admin.v1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.realityfamily.pandorabackend.shared.models.Category;

import java.util.ArrayList;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryWithDescriptionDTO {

    private String id;

    private String title;

    private String description;

    private int childCount;

    public CategoryWithDescriptionDTO(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
        this.description= category.getDescription();
        this.childCount = Optional.ofNullable(category.getSubcategorys()).orElse(new ArrayList<>()).size();
    }
}
