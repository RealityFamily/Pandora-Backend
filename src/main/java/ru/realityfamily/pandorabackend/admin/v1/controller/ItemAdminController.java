package ru.realityfamily.pandorabackend.admin.v1.controller;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.realityfamily.pandorabackend.shared.models.Item;
import ru.realityfamily.pandorabackend.shared.models.ModelAccessStrategy;
import ru.realityfamily.pandorabackend.shared.models.User;
import ru.realityfamily.pandorabackend.shared.models.gridfs.PhotoSize;
import ru.realityfamily.pandorabackend.shared.models.gridfs.service.Model3dService;
import ru.realityfamily.pandorabackend.shared.models.gridfs.service.PhotoService;
import ru.realityfamily.pandorabackend.shared.repository.ItemRepository;
import ru.realityfamily.pandorabackend.shared.repository.UserRepository;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/admin")
public class ItemAdminController {
    private PhotoService photoService;
    private Model3dService model3dService;
    private UserRepository userRepository; // TODO: refactor it to service layer
    private ItemRepository itemRepository; // TODO: refactor it to service layer

    @PostMapping("item/add")
    public Item addItem(@RequestParam("itemName") String itemName,
                          @RequestParam("description") String itemDescription,
                          @RequestParam("modelAccessStrategy") ModelAccessStrategy modelAccessStrategy,
                          @RequestParam("photoLarge")MultipartFile photoLarge,
                          @RequestParam("photoSmall") MultipartFile photoSmall,
                          @RequestParam("3dmodel")MultipartFile model3d,
                          @RequestParam("subtagid") String subtagId,
                          @RequestParam("authorid") String authorId) throws IOException {

        //// TODO: refactor it to service layer
        String photoSmallId = photoService.addPhoto(itemName +"_small",photoSmall, PhotoSize.Small);
        String photoLargeId = photoService.addPhoto(itemName +"_large", photoLarge, PhotoSize.Large);
        String model3dId = model3dService.addModel3d(itemName+"_3dModel", model3d);

        Set<String> photoSmallIdS = new TreeSet<>() {
            {add(photoSmallId);}
        };
        Set<String> photoLargeIdS = new TreeSet<>() {
            {add(photoLargeId);}
        };
        Set<String> model3dIdS = new TreeSet<>() {
            {add(model3dId);}
        };

        User user = userRepository.findById(authorId).get();
        Item item = new Item(itemName, itemDescription, modelAccessStrategy, photoSmallIdS,photoLargeIdS, model3dIdS, model3d.getBytes().length, user);
        item = itemRepository.save(item);
        //// TODO: refactor it to service layer

        return item;
    }


}
