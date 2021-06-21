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
import ru.realityfamily.pandorabackend.user.v1.DTO.ItemCardLongDTO;

import java.io.IOException;
import java.util.*;

@Component
@AllArgsConstructor
public class ItemAdminServiceImpl implements IItemAdminService {
    private PhotoService photoService;
    private Model3dService model3dService;
    private UserRepository userRepository;
    private ItemRepository itemRepository;
    private SubtagRepository subtagRepository;

    @Override
    public Item addNewItem(String itemName, String itemDescription, ModelAccessStrategy modelAccessStrategy, MultipartFile photoLarge, MultipartFile photoSmall, MultipartFile model3d, String subtagId, String authorId) throws IOException, NoSuchElementException {
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
        List<Item> itemList = Optional.ofNullable(subtag.getItemList()).orElse(new ArrayList<>());
        itemList.add(item);
        subtag.setItemList(itemList);
        subtagRepository.save(subtag);

        return item;
    }

    @Override
    public void deleteItem(String id) throws NoSuchElementException {
        Item itemFound = itemRepository.findById(id).orElseThrow();
        if (itemFound.getMiniPhotoGridFsFileIds() != null) {
            itemFound.getModelGridFsFileIds().stream().forEach(model -> {
                model3dService.deleteModel(model);
            });
        }
        if (itemFound.getPhotoGridFsFileIds() != null) {
            itemFound.getPhotoGridFsFileIds().stream().forEach(photoLarge -> {
                photoService.deletePhoto(photoLarge);
            });
        }
        if (itemFound.getMiniPhotoGridFsFileIds() != null) {
            itemFound.getMiniPhotoGridFsFileIds().stream().forEach(photoSmall -> {
                photoService.deletePhoto(photoSmall);
            });
        }

        itemRepository.deleteById(id);
    }

    @Override
    public ItemCardLongDTO updateItem(String itemId, ItemCardLongDTO item) throws NoSuchElementException{ // now supported only name and description mutating
        if(item != null){
            Item itemFound = itemRepository.findById(itemId).orElseThrow();
            itemFound.setName(Optional.ofNullable(item.getName()).orElse(""));
            itemFound.setDescription(Optional.ofNullable(item.getDescription()).orElse(""));
            return new ItemCardLongDTO(itemRepository.save(itemFound));
        } else throw new NoSuchElementException("Get item, and it was null");
    }
}
