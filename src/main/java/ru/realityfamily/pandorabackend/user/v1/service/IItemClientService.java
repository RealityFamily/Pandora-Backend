package ru.realityfamily.pandorabackend.user.v1.service;

import ru.realityfamily.pandorabackend.shared.models.Item;

import java.util.List;

public interface IItemClientService {
        public Item getItemById(String id);
        public List<Item> getAllItems();
}
