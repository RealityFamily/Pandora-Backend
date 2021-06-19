package ru.realityfamily.pandorabackend.admin.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.realityfamily.pandorabackend.shared.models.Subcategory;
import ru.realityfamily.pandorabackend.shared.models.Subtag;

import java.util.ArrayList;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubtagWithDescriptionDTO {

    private String id;

    private String title;

    private int childCount;

    public SubtagWithDescriptionDTO(Subtag subtag) {
        this.id = subtag.getId();
        this.title = subtag.getTitle();
        this.childCount = Optional.ofNullable(subtag.getItemList()).orElse(new ArrayList<>()).size();
    }
}
