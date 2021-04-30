package ru.realityfamily.pandorabackend.user.v1.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ItemCardLongDTO {
    private String id;
    private String name;
    private String description;

    private String authorNick;
    private String authorReference;
}
