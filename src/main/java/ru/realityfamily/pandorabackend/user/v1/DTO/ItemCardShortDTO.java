package ru.realityfamily.pandorabackend.user.v1.DTO;

import lombok.Getter;
import lombok.Setter;
import ru.realityfamily.pandorabackend.shared.models.Item;

@Getter
@Setter
public class ItemCardShortDTO {
    String id;

    public ItemCardShortDTO(Item item) {
        this.id = item.getId();
    }
}
