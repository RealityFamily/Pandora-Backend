package ru.realityfamily.pandorabackend.user.v1.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.realityfamily.pandorabackend.shared.models.Item;
import ru.realityfamily.pandorabackend.user.v1.DTO.ItemCardLongDTO;
import ru.realityfamily.pandorabackend.user.v1.service.IItemClientService;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/client")
public class ItemClientController {

    IItemClientService itemClientService;

    @GetMapping("item/{item_id}")
    public ItemCardLongDTO getItemById(@PathVariable("item_id") String id){
        return convertItemToItemCardLongDTO(itemClientService.getItemById(id));
    }

    private ItemCardLongDTO convertItemToItemCardLongDTO(Item itemById) {
        return  new ItemCardLongDTO(itemById.getId(), itemById.getName(),itemById.getDescription(),itemById.getSizeInByte(),
                itemById.getAuthorReference().getNickname(), itemById.getAuthorReference().getMail());
    }

}
