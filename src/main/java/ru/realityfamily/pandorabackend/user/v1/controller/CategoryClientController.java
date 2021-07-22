package ru.realityfamily.pandorabackend.user.v1.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.realityfamily.pandorabackend.shared.models.Category;
import ru.realityfamily.pandorabackend.shared.models.Subcategory;
import ru.realityfamily.pandorabackend.user.v1.DTO.CategoryDTO;
import ru.realityfamily.pandorabackend.user.v1.DTO.SubcatFromCatDTO;
import ru.realityfamily.pandorabackend.user.v1.service.ICategoryClientService;


import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/client")
public class CategoryClientController {

    ICategoryClientService ICategoryClientService;

    @GetMapping("category/all") // возвращает все категории левого основного списка айтемов
    public List<CategoryDTO> getCategories(){
        return  convertListToDTO(ICategoryClientService.findAllCategories());
    }

    @GetMapping("category/{id}")
    public Category getCategoryById(@PathVariable("id") String id){
        return ICategoryClientService.findCategoryById(id);
    }

    @GetMapping("category/{id}/subcategories") // возвращает все подкатегории по заданной категории
    public List<SubcatFromCatDTO> getSubcategoryFromCategory(@PathVariable("id") String id){
        return convertCatToSubCatFromCatDTO(ICategoryClientService.findAllSubcategoriesFromCategory(id));
    }


    //DTO's converters
    private List<SubcatFromCatDTO> convertCatToSubCatFromCatDTO(List<Subcategory> subcategories){
        List<SubcatFromCatDTO> subcatFromCatDTOS = new ArrayList<>();
        if(subcategories != null){
        for( Subcategory s : subcategories) subcatFromCatDTOS.add(convertCatToSubCatFromCatDTO(s));
        }
        return subcatFromCatDTOS;
    }

    private SubcatFromCatDTO convertCatToSubCatFromCatDTO(Subcategory subcategory){
        return new SubcatFromCatDTO(subcategory.getId(),subcategory.getTitle());
    }

    private CategoryDTO convertToDTO(Category categoryById) {
        return new CategoryDTO(categoryById.getId(),categoryById.getTitle());
    }

    private List<CategoryDTO> convertListToDTO(List<Category> categories) {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for(Category c : categories){
            categoryDTOS.add(new CategoryDTO(c.getId(),c.getTitle()));
        }
        return  categoryDTOS;
    }



}
