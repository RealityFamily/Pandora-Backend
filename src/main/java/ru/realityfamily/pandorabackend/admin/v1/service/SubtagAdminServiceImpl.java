package ru.realityfamily.pandorabackend.admin.v1.service;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.realityfamily.pandorabackend.admin.v1.dto.SubcategoryWithDescriptionDTO;
import ru.realityfamily.pandorabackend.admin.v1.dto.SubtagWithDescriptionDTO;
import ru.realityfamily.pandorabackend.shared.models.Subcategory;
import ru.realityfamily.pandorabackend.shared.models.Subtag;
import ru.realityfamily.pandorabackend.shared.repository.SubcategoryRepository;
import ru.realityfamily.pandorabackend.shared.repository.SubtagRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@AllArgsConstructor
public class SubtagAdminServiceImpl implements ISubtagAdminService {
    SubtagRepository subtagRepository;
    SubcategoryRepository subcategoryRepository;

    @Override
    public Subtag addSubtagToSubcategoryByID(@NotNull String subcategoryId,@NotNull Subtag subtag) throws NoSuchElementException {
        Optional<Subcategory> subcategoryOptional = subcategoryRepository.findById(subcategoryId);
        Subcategory subcategoryEditable =  subcategoryOptional.orElseThrow();
        Subtag subtagSaved = subtagRepository.save(subtag);
        Optional.ofNullable(subcategoryEditable.getInlineInterfaceTag()).orElseThrow().add(subtagSaved);
        subcategoryRepository.save(subcategoryEditable);
        return subtagSaved;
    }

    @Override
    public Subtag updateSubtagById(@NotNull Subtag subtag,@NotNull String subtagId) throws NoSuchElementException {
        Subtag subtagEditable = subtagRepository.findById(subtagId).orElseThrow();
        subtagEditable.setItemList(Optional.ofNullable(subtag.getItemList()).orElse(subtagEditable.getItemList()));
        subtagEditable.setTitle(Optional.ofNullable(subtag.getTitle()).orElse(subtagEditable.getTitle()));
        return subtagRepository.save(subtagEditable);
    }

    @Override
    public void deleteSubtag(String subtagId) { // dont forget remove items
        subtagRepository.deleteById(subtagId);
    }

    @Override
    public SubtagWithDescriptionDTO getSubtagDetailedById(String subtagId) {
        Subtag subtag = subtagRepository.findById(subtagId).orElseThrow();
        return new SubtagWithDescriptionDTO(subtag);
    }
}
