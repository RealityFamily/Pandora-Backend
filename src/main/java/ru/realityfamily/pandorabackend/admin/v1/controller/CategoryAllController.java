package ru.realityfamily.pandorabackend.admin.v1.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.realityfamily.pandorabackend.admin.v1.dto.CategoryDTO;
import ru.realityfamily.pandorabackend.admin.v1.service.ICategoryAdminService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin")
public class CategoryAllController {

    ICategoryAdminService categoryService;

    @GetMapping("/categorys/all")
    List<CategoryDTO> getAllCategorysForSelect(){
        return categoryService.getAllCategorysInAntdCascaderForm();
    }
}
