package ru.realityfamily.pandorabackend.user.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.realityfamily.pandorabackend.shared.models.Category;
import ru.realityfamily.pandorabackend.shared.models.Subcategory;
import ru.realityfamily.pandorabackend.shared.repository.CategoryRepository;
import ru.realityfamily.pandorabackend.user.v1.DTO.CategoryDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
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
