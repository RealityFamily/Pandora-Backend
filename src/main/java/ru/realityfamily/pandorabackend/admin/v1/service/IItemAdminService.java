package ru.realityfamily.pandorabackend.admin.v1.service;

import org.springframework.web.multipart.MultipartFile;
import ru.realityfamily.pandorabackend.shared.models.Item;
import ru.realityfamily.pandorabackend.shared.models.ModelAccessStrategy;

import java.io.IOException;

public interface IItemAdminService {

    Item addNewItem(String itemName, String itemDescription, ModelAccessStrategy modelAccessStrategy, MultipartFile photoLarge, MultipartFile photoSmall, MultipartFile model3d, String subtagId, String authorId) throws IOException;
}
