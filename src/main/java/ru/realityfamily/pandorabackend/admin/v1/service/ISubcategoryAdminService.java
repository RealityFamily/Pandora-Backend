package ru.realityfamily.pandorabackend.admin.v1.service;

import ru.realityfamily.pandorabackend.shared.models.Subcategory;

public interface ISubcategoryAdminService {

    Subcategory addNewSubcategoryToCategory(Subcategory  subcategory, String categoryId);

    Subcategory updateSubcategoryFields(Subcategory subcategory, String subcategoryId);

    void deleteSubcategory(String subcategoryId);
}
