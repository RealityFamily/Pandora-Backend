package ru.realityfamily.pandorabackend.user.v1.service;

import ru.realityfamily.pandorabackend.shared.models.Item;

public interface IItemClientService {
        public Item getItemById(String id);
}
