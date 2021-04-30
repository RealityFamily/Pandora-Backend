package ru.realityfamily.pandorabackend.user.v1.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.realityfamily.pandorabackend.shared.models.Item;
import ru.realityfamily.pandorabackend.shared.models.Subtag;
import ru.realityfamily.pandorabackend.user.v1.DTO.ItemCardShortDTO;
import ru.realityfamily.pandorabackend.user.v1.DTO.SubtagDTO;
import ru.realityfamily.pandorabackend.user.v1.service.ISubtagClientService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/client")
public class SubtagClientController {

    ISubtagClientService ISubtagClientService;

    @GetMapping("item/bysubgroup/{subgroup_id}")
    public List<SubtagDTO> getSubtagWithItems(@PathVariable("subgroup_id") String subgroupId){
        return convertSubtagListToSubtagDTOList(ISubtagClientService.geSubtagWithItems(subgroupId));
    }


    private List<SubtagDTO> convertSubtagListToSubtagDTOList(List<Subtag> subtags){
        List<SubtagDTO> subtagDTOS = new ArrayList<>();
        for(Subtag s : subtags){
            subtagDTOS.add(convertSubtagToSubtagDTO(s));
        }
        return subtagDTOS;
    }

    private ItemCardShortDTO convertItemToItemCardShortDTO (Item item){
        return new ItemCardShortDTO(item);
    }

    private List<ItemCardShortDTO> convertAllItemToItemCardShortDTO (List<Item> itemList){
        List<ItemCardShortDTO> itemCardShortDTOS = new ArrayList<>();
        for(Item i : itemList){
            itemCardShortDTOS.add(convertItemToItemCardShortDTO(i));
        }
        return itemCardShortDTOS;
    }

    private SubtagDTO convertSubtagToSubtagDTO(Subtag subtag){
        return  new SubtagDTO(subtag.getId(), subtag.getTitle(), convertAllItemToItemCardShortDTO(subtag.getItemList()));
    }



}
