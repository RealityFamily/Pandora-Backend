package ru.realityfamily.pandorabackend.admin.v1.service;

import ru.realityfamily.pandorabackend.admin.v1.dto.CategoryDTO;

import java.util.List;

public interface ICategoryAdminService {
    List<CategoryDTO> getAllCategorysInAntdCascaderForm();
}
