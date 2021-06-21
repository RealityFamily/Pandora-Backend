package ru.realityfamily.pandorabackend.admin.v1.controller;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.realityfamily.pandorabackend.admin.v1.service.IItemAdminService;
import ru.realityfamily.pandorabackend.shared.models.Item;
import ru.realityfamily.pandorabackend.shared.models.ModelAccessStrategy;
import ru.realityfamily.pandorabackend.shared.models.Subtag;
import ru.realityfamily.pandorabackend.shared.models.User;
import ru.realityfamily.pandorabackend.shared.models.gridfs.PhotoSize;
import ru.realityfamily.pandorabackend.shared.models.gridfs.service.Model3dService;
import ru.realityfamily.pandorabackend.shared.models.gridfs.service.PhotoService;
import ru.realityfamily.pandorabackend.shared.repository.ItemRepository;
import ru.realityfamily.pandorabackend.shared.repository.SubtagRepository;
import ru.realityfamily.pandorabackend.shared.repository.UserRepository;
import ru.realityfamily.pandorabackend.user.v1.DTO.ItemCardLongDTO;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/admin")
public class ItemAdminController {

    IItemAdminService itemAdminService;

    @PostMapping("item/add")
    public Item addItem(@RequestParam("itemName") String itemName,
                        @RequestParam("description") String itemDescription,
                        @RequestParam("modelAccessStrategy") ModelAccessStrategy modelAccessStrategy,
                        @RequestParam("photoLarge") MultipartFile photoLarge,
                        @RequestParam("photoSmall") MultipartFile photoSmall,
                        @RequestParam("3dmodel") MultipartFile model3d,
                        @RequestParam("subtagid") String subtagId,
                        @RequestParam("authorid") String authorId) throws IOException {
        return itemAdminService.addNewItem(itemName, itemDescription, modelAccessStrategy, photoLarge, photoSmall, model3d, subtagId, authorId);
    }

    @DeleteMapping("/item/delete/{id}")
    public void deleteItem(@PathVariable String id){
        itemAdminService.deleteItem(id);
    }


    @PutMapping("item/update/{itemId}")
    public ItemCardLongDTO updateItem(@PathVariable String itemId, @RequestBody ItemCardLongDTO item){
        return itemAdminService.updateItem(itemId,item);
    }
}
