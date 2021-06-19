package ru.realityfamily.pandorabackend.admin.v1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import ru.realityfamily.pandorabackend.shared.models.Category;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class CategoryDTO {
    @JsonProperty("value")
    private String id;

    @JsonProperty("label")
    private String title;

    @JsonProperty("children")
    private List<SubCatDTO> subcategorys;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
        if(category.getSubcategorys()!= null)
        this.subcategorys = category.getSubcategorys().stream().map(subcategory -> new SubCatDTO(subcategory)).collect(Collectors.toList());
    }
}
