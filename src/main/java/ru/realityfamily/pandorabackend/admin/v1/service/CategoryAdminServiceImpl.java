package ru.realityfamily.pandorabackend.admin.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.realityfamily.pandorabackend.admin.v1.dto.CategoryWithDescriptionDTO;
import ru.realityfamily.pandorabackend.admin.v1.dto.selectable.CategoryDTO;
import ru.realityfamily.pandorabackend.shared.models.Category;
import ru.realityfamily.pandorabackend.shared.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CategoryAdminServiceImpl implements ICategoryAdminService {
    CategoryRepository categoryRepository;
    ISubcategoryAdminService subcategoryAdminService;

    @Override
    public List<CategoryDTO> getAllCategorysInAntdCascaderForm() {
        return categoryRepository.findAll().stream().map(category -> new CategoryDTO(category)).collect(Collectors.toList());
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(String id) throws NoSuchElementException{
        Optional.ofNullable(categoryRepository.findById(id).orElseThrow().getSubcategorys()).orElse(new ArrayList<>()).stream().forEach(subcategory -> {
            subcategoryAdminService.deleteSubcategory(subcategory.getId());
        });
        categoryRepository.deleteById(id);
    }

    @Override
    public Category updateCategoryFields(String id, Category category) throws NoSuchElementException {
        Optional<Category> updatableCategory = categoryRepository.findById(id);
        Category editableCategory = updatableCategory.
                map((categoryOld) -> {
                    if (category.getTitle() != null) {
                        categoryOld.setTitle(category.getTitle());
                    }
                    if(category.getDescription()!= null){
                    categoryOld.setDescription(category.getDescription());
                    }

                    if(category.getSubcategorys() != null){
                    categoryOld.setSubcategorys(category.getSubcategorys());
                    }
                    return categoryRepository.save(categoryOld);
                }).
                orElseThrow(() -> new NoSuchElementException(id));
        return editableCategory;
    }

    @Override
    public CategoryWithDescriptionDTO getCategoryDetailedById(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        return new CategoryWithDescriptionDTO(category);
    }

    @Override
    public List<Category> getCategorysAllDetailed() {
        return categoryRepository.findAll();
    }



}
