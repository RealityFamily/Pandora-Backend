package ru.realityfamily.pandorabackend.user.v1.service;

import ru.realityfamily.pandorabackend.shared.models.Category;
import ru.realityfamily.pandorabackend.shared.models.Subcategory;

import java.util.List;

public interface CategoryService {
     public List<Category> findAllCategories();

    public Category findCategoryById(String id);

    public List<Subcategory> findAllSubcategoriesFromCategory(String id);

}
