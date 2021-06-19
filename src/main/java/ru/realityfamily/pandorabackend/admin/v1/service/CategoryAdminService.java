package ru.realityfamily.pandorabackend.admin.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.realityfamily.pandorabackend.admin.v1.dto.CategoryDTO;
import ru.realityfamily.pandorabackend.shared.models.Category;
import ru.realityfamily.pandorabackend.shared.repository.CategoryRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CategoryAdminService implements ICategoryAdminService {
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> getAllCategorysInAntdCascaderForm() {
        return categoryRepository.findAll().stream().map(category -> new CategoryDTO(category)).collect(Collectors.toList());
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(String id) {
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


}
