package ru.realityfamily.pandorabackend.admin.v1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.realityfamily.pandorabackend.shared.models.Subtag;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
public class SubTagDTO {

    @JsonProperty("value")
    String id;

    @JsonProperty("label")
    String title;

    public SubTagDTO(Subtag subtag) {
        this.id = subtag.getId();
        this.title = subtag.getTitle();
    }
}
