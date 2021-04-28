package ru.realityfamily.pandorabackend.user.v1.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.realityfamily.pandorabackend.user.v1.DTO.SubtagDTO;
import ru.realityfamily.pandorabackend.user.v1.service.SubtagService;

import java.util.List;

@AllArgsConstructor
public class SubtagController {

    SubtagService subtagService;

    @GetMapping("category/{group_id}/{subgroup_id}")
    public List<SubtagDTO> getSubtagWithItems(@PathVariable("group_id") String groupId, @PathVariable("subgroup_id") String subgroupId){
        return subtagService.geSubtagWithItemsDTO();
    }

}
