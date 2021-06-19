package ru.realityfamily.pandorabackend.admin.v1.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.realityfamily.pandorabackend.admin.v1.dto.CategoryWithDescriptionDTO;
import ru.realityfamily.pandorabackend.admin.v1.dto.selectable.CategoryDTO;
import ru.realityfamily.pandorabackend.admin.v1.service.ICategoryAdminService;
import ru.realityfamily.pandorabackend.shared.models.Category;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin")
public class CategoryAdminController {

    ICategoryAdminService categoryService;

    @GetMapping("/categorys/all/selectable")
    List<CategoryDTO> getAllCategorysForSelect() {
        return categoryService.getAllCategorysInAntdCascaderForm();
    }

    @GetMapping("/category/{categoryId}")
    CategoryWithDescriptionDTO getCategoryDetailed(@PathVariable String categoryId) {
        CategoryWithDescriptionDTO  category = categoryService.getCategoryDetailedById(categoryId);
        return category;
    }

    @PostMapping("/category/add")
    Category postCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @PutMapping("category/update/{id}")
    Category updateCategory(@PathVariable String id, @RequestBody Category category) {
        return categoryService.updateCategoryFields(id, category);
    }

    @DeleteMapping("category/delete/{id}")
    void deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id); // don't forget delete subcategory's, subtag's, and item's
    }
}
