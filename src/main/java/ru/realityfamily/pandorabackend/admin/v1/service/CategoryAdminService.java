package ru.realityfamily.pandorabackend.admin.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.realityfamily.pandorabackend.admin.v1.dto.CategoryDTO;
import ru.realityfamily.pandorabackend.shared.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CategoryAdminService implements ICategoryAdminService {
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> getAllCategorysInAntdCascaderForm() {
        return categoryRepository.findAll().stream().map(category -> new CategoryDTO(category)).collect(Collectors.toList());
    }
}
