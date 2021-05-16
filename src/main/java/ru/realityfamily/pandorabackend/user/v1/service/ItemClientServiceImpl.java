package ru.realityfamily.pandorabackend.user.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.realityfamily.pandorabackend.shared.models.Item;
import ru.realityfamily.pandorabackend.shared.repository.ItemRepository;

import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class ItemClientServiceImpl implements IItemClientService{

    ItemRepository itemRepository;

    @Override
    public Item getItemById(String id) {
        return  itemRepository.findById(id).get();
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
