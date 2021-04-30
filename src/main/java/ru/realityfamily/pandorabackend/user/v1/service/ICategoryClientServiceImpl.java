package ru.realityfamily.pandorabackend.user.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.realityfamily.pandorabackend.shared.models.Category;
import ru.realityfamily.pandorabackend.shared.models.Subcategory;
import ru.realityfamily.pandorabackend.shared.repository.CategoryRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class ICategoryClientServiceImpl implements ICategoryClientService {
    CategoryRepository categoryRepository;

    public List<Category> findAllCategories(){
        return categoryRepository.findAll();
    }

    public Category findCategoryById(String id) {
        return categoryRepository.findById(id).get();
    }

    public List<Subcategory> findAllSubcategoriesFromCategory(String id) {
        return categoryRepository.findById(id).get().getSubcategorys();
    }
}
