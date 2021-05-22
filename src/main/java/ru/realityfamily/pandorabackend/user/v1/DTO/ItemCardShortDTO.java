package ru.realityfamily.pandorabackend.user.v1.DTO;

import lombok.Getter;
import lombok.Setter;
import ru.realityfamily.pandorabackend.shared.models.Item;
import ru.realityfamily.pandorabackend.shared.models.ModelAccessStrategy;

@Getter
@Setter
public class ItemCardShortDTO {
    String id;
    ModelAccessStrategy modelAccessStrategy;

    public ItemCardShortDTO(Item item) {
        this.id = item.getId();
        this.modelAccessStrategy = item.getModelAccessStrategy();
    }

}
