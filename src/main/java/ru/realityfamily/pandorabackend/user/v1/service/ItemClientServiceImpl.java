package ru.realityfamily.pandorabackend.user.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.realityfamily.pandorabackend.shared.models.Item;
import ru.realityfamily.pandorabackend.shared.repository.ItemRepository;

@Component
@AllArgsConstructor
public class ItemClientServiceImpl implements IItemClientService{

    ItemRepository itemRepository;

    @Override
    public Item getItemById(String id) {
        return  itemRepository.findById(id).get();
    }
}
