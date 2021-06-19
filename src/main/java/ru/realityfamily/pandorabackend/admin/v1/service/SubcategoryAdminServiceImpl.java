package ru.realityfamily.pandorabackend.admin.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.realityfamily.pandorabackend.admin.v1.dto.CategoryWithDescriptionDTO;
import ru.realityfamily.pandorabackend.admin.v1.dto.SubcategoryWithDescriptionDTO;
import ru.realityfamily.pandorabackend.shared.models.Category;
import ru.realityfamily.pandorabackend.shared.models.Subcategory;
import ru.realityfamily.pandorabackend.shared.models.Subtag;
import ru.realityfamily.pandorabackend.shared.repository.CategoryRepository;
import ru.realityfamily.pandorabackend.shared.repository.SubcategoryRepository;
import ru.realityfamily.pandorabackend.shared.repository.SubtagRepository;

import java.util.*;
import java.util.function.Supplier;

@Component
@AllArgsConstructor
public class SubcategoryAdminServiceImpl implements ISubcategoryAdminService {
    SubcategoryRepository subcategoryRepository;
    CategoryRepository categoryRepository;
    SubtagRepository subtagRepository;

    @Override
    public Subcategory addNewSubcategoryToCategory(Subcategory subcategory, String categoryId) throws NoSuchElementException {
        if (subcategory != null && categoryId != null) {
            subcategory.setInlineInterfaceTag(subcategory.getInlineInterfaceTag() == null ? subtagRepository.saveAll(Arrays.asList(new Subtag("main")))
                    : subcategory.getInlineInterfaceTag());
            Subcategory subcategorySaved = subcategoryRepository.save(subcategory);
            Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
            Category category = categoryOptional.orElseThrow();
            Optional<List<Subcategory>> optionalSubcategoriesFromCategory = Optional.ofNullable(category.getSubcategorys());
            List<Subcategory> subcategoryList = optionalSubcategoriesFromCategory
                    .orElseGet(new Supplier<List<Subcategory>>() {
                        @Override
                        public List<Subcategory> get() {
                            return new ArrayList<Subcategory>();
                        }
                    });
            subcategoryList.add(subcategorySaved);
            category.setSubcategorys(subcategoryList);
            categoryRepository.save(category);
            return subcategorySaved;
        } else throw new NoSuchElementException("Maybe you try to provide categoryId or Subctegory that is null");
    }

    @Override
    public Subcategory updateSubcategoryFields(Subcategory subcategory, String subcategoryId) throws NoSuchElementException {
        Optional<Subcategory> subcategoryOptional = subcategoryRepository.findById(subcategoryId);
        Subcategory subcategoryFound = subcategoryOptional.orElseThrow();
        if (subcategory.getDescription() != null) subcategoryFound.setDescription(subcategory.getDescription());
        if (subcategory.getTitle() != null) subcategoryFound.setTitle(subcategory.getTitle());
        if (subcategory.getInlineInterfaceTag() != null)
            subcategoryFound.setInlineInterfaceTag(subcategory.getInlineInterfaceTag());
        return subcategoryRepository.save(subcategoryFound);
    }

    @Override
    public void deleteSubcategory(String subcategoryId) {
        subcategoryRepository.deleteById(subcategoryId);
    }

    @Override
    public SubcategoryWithDescriptionDTO getSubcategoryDetailedById(String subcategoryId) throws NoSuchElementException{
        Subcategory subcategory = subcategoryRepository.findById(subcategoryId).orElseThrow();
        return new SubcategoryWithDescriptionDTO(subcategory);
    }
}
