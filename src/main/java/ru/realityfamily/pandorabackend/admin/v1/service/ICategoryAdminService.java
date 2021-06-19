package ru.realityfamily.pandorabackend.admin.v1.service;

import ru.realityfamily.pandorabackend.admin.v1.dto.CategoryDTO;
import ru.realityfamily.pandorabackend.shared.models.Category;

import java.util.List;

public interface ICategoryAdminService {

    List<CategoryDTO> getAllCategorysInAntdCascaderForm();

    Category addCategory(Category category);

    void deleteCategory(String id);

    Category updateCategoryFields(String id, Category category);

}
