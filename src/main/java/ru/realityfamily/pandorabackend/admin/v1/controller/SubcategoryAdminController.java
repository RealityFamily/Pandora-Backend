package ru.realityfamily.pandorabackend.admin.v1.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.realityfamily.pandorabackend.admin.v1.dto.CategoryWithDescriptionDTO;
import ru.realityfamily.pandorabackend.admin.v1.dto.SubcategoryWithDescriptionDTO;
import ru.realityfamily.pandorabackend.admin.v1.service.ISubcategoryAdminService;
import ru.realityfamily.pandorabackend.shared.models.Subcategory;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin")
public class SubcategoryAdminController {
    ISubcategoryAdminService subcategoryAdminService;

    @GetMapping("/subcategory/{subcategoryId}")
    SubcategoryWithDescriptionDTO getSubcategoryDetailed(@PathVariable String subcategoryId){
        SubcategoryWithDescriptionDTO subcategory = subcategoryAdminService.getSubcategoryDetailedById(subcategoryId);
        return subcategory;
    }

    @PostMapping("subcategory/add/to/{categoryId}")
    Subcategory postSubcategory(@RequestBody Subcategory subcategory, @PathVariable String categoryId){
        return subcategoryAdminService.addNewSubcategoryToCategory(subcategory, categoryId);
    }

    @PutMapping("subcategory/update/{subcategoryId}")
    Subcategory putSubcategory(@RequestBody Subcategory subcategory, @PathVariable String subcategoryId){
        return  subcategoryAdminService.updateSubcategoryFields(subcategory, subcategoryId);
    }

    @DeleteMapping("subcategory/delete/{subcategoryId}")
    void deleteSubcategory(@PathVariable String subcategoryId){
        subcategoryAdminService.deleteSubcategory(subcategoryId); // don't forget delete subtag's and items
    }
}
