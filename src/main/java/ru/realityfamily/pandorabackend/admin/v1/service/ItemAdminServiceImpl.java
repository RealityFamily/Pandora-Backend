package ru.realityfamily.pandorabackend.admin.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
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

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

@Component
@AllArgsConstructor
public class ItemAdminServiceImpl implements IItemAdminService {
    private PhotoService photoService;
    private Model3dService model3dService;
    private UserRepository userRepository;
    private ItemRepository itemRepository;
    private SubtagRepository subtagRepository;

    @Override
    public Item addNewItem(String itemName, String itemDescription, ModelAccessStrategy modelAccessStrategy, MultipartFile photoLarge, MultipartFile photoSmall, MultipartFile model3d, String subtagId, String authorId) throws IOException {
        String photoSmallId = photoService.addPhoto(itemName + "_small", photoSmall, PhotoSize.Small);
        String photoLargeId = photoService.addPhoto(itemName + "_large", photoLarge, PhotoSize.Large);
        String model3dId = model3dService.addModel3d(itemName + "_3dModel", model3d);

        Set<String> photoSmallIdS = new TreeSet<>() {
            {
                add(photoSmallId);
            }
        };
        Set<String> photoLargeIdS = new TreeSet<>() {
            {
                add(photoLargeId);
            }
        };
        Set<String> model3dIdS = new TreeSet<>() {
            {
                add(model3dId);
            }
        };

        User user = userRepository.findById(authorId).get();
        Item item = new Item(itemName, itemDescription, modelAccessStrategy, photoSmallIdS, photoLargeIdS, model3dIdS, model3d.getBytes().length, user);
        item = itemRepository.save(item);

        Subtag subtag = subtagRepository.findById(subtagId).get();
        subtag.getItemList().add(item);
        subtagRepository.save(subtag);

        //// TODO: refactor it to service layer

        return item;
    }
}
