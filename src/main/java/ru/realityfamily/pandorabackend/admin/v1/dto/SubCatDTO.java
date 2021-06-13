package ru.realityfamily.pandorabackend.admin.v1.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.realityfamily.pandorabackend.shared.models.Subcategory;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubCatDTO {
    @JsonProperty("value")
    private String id;

    @JsonProperty("label")
    private String title;

    @JsonProperty("children")
    private List<SubTagDTO> inlineInterfaceTag;

    public SubCatDTO(Subcategory subcategory) {
        this.id = subcategory.getId();
        this.title = subcategory.getTitle();
        if(subcategory.getInlineInterfaceTag()!= null) {
            this.inlineInterfaceTag = subcategory.getInlineInterfaceTag().stream().map(subtag -> new SubTagDTO(subtag)).collect(Collectors.toList());
        }
    }
}
