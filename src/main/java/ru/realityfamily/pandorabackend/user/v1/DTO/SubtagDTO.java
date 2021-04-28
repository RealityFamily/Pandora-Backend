package ru.realityfamily.pandorabackend.user.v1.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import ru.realityfamily.pandorabackend.shared.models.Item;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SubtagDTO {
    String id;
    String title;
    List<Item> itemList;
}
