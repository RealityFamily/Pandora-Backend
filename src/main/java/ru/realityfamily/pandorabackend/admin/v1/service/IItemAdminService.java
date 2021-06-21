package ru.realityfamily.pandorabackend.admin.v1.service;

import org.springframework.web.multipart.MultipartFile;
import ru.realityfamily.pandorabackend.shared.models.Item;
import ru.realityfamily.pandorabackend.shared.models.ModelAccessStrategy;
import ru.realityfamily.pandorabackend.user.v1.DTO.ItemCardLongDTO;

import java.io.IOException;

public interface IItemAdminService {

    Item addNewItem(String itemName, String itemDescription, ModelAccessStrategy modelAccessStrategy, MultipartFile photoLarge, MultipartFile photoSmall, MultipartFile model3d, String subtagId, String authorId) throws IOException;

    void deleteItem(String id);

    ItemCardLongDTO updateItem(String itemId, ItemCardLongDTO item);
}
