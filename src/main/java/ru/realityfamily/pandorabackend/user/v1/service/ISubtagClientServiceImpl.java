package ru.realityfamily.pandorabackend.user.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.realityfamily.pandorabackend.shared.models.Subcategory;
import ru.realityfamily.pandorabackend.shared.models.Subtag;
import ru.realityfamily.pandorabackend.shared.repository.SubcategoryRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class ISubtagClientServiceImpl implements ISubtagClientService {

    SubcategoryRepository subcategoryRepository;

    @Override
    public List<Subtag> geSubtagWithItems(String subcategory) {
        Subcategory subcategoryForMap =  subcategoryRepository.findById(subcategory).get();
        return subcategoryForMap.getInlineInterfaceTag();
    }
}
