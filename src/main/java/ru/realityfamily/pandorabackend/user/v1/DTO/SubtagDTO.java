package ru.realityfamily.pandorabackend.user.v1.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SubtagDTO {
    String id;
    String title;
    List<ItemCardShortDTO> itemCardShortDTOS;
}
