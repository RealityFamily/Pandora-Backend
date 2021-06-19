package ru.realityfamily.pandorabackend.admin.v1.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.realityfamily.pandorabackend.admin.v1.service.ISubtagAdminService;
import ru.realityfamily.pandorabackend.shared.models.Subtag;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin")
public class SubtagAdminController {
    ISubtagAdminService subtagAdminService;

    @PostMapping("/subtag/add/to/{subcategoryId}")
    Subtag postSubtag(@PathVariable String subcategoryId, @RequestBody Subtag subtag){
        return subtagAdminService.addSubtagToSubcategoryByID(subcategoryId, subtag);
    }

    @PutMapping("/subtag/update/{subtagId}")
    Subtag updateSubtag(@PathVariable String subtagId, @RequestBody Subtag subtag){
        return  subtagAdminService.updateSubtagById(subtag, subtagId);
    }

    @DeleteMapping("/subtag/delete/{subtagId}")
    void deleteSubtag(@PathVariable String subtagId){
        subtagAdminService.deleteSubtag(subtagId);
    }

}
