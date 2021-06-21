package ru.realityfamily.pandorabackend.user.v1.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.realityfamily.pandorabackend.shared.models.Item;

import java.util.Optional;

@AllArgsConstructor
@Getter
@Setter
public class ItemCardLongDTO {
    private String id;
    private String name;
    private String description;
    private int modelSize;

    private String authorNick;
    private String authorReference;

    public ItemCardLongDTO(Item save) {
        this.id = save.getId();
        this.name = save.getName();
        this.description = save.getDescription();
        this.modelSize = save.getSizeInByte();
        this.authorNick = save.getAuthorReference().getNickname();
        this.authorReference = save.getAuthorReference().getMail();
    }
}
