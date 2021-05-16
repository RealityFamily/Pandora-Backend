package ru.realityfamily.pandorabackend.user.v1.controller;

import lombok.AllArgsConstructor;
import org.apache.tomcat.jni.File;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import ru.realityfamily.pandorabackend.shared.models.Item;
import ru.realityfamily.pandorabackend.shared.models.gridfs.Model3d;
import ru.realityfamily.pandorabackend.shared.models.gridfs.Photo;
import ru.realityfamily.pandorabackend.shared.models.gridfs.service.Model3dService;
import ru.realityfamily.pandorabackend.shared.models.gridfs.service.PhotoService;
import ru.realityfamily.pandorabackend.user.v1.DTO.ItemCardLongDTO;
import ru.realityfamily.pandorabackend.user.v1.service.IItemClientService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/client")
public class ItemClientController {

    IItemClientService itemClientService;
    PhotoService photoService;
    Model3dService model3dService;

    @GetMapping("item/{item_id}")
    public ItemCardLongDTO getItemById(@PathVariable("item_id") String id){
        return convertItemToItemCardLongDTO(itemClientService.getItemById(id));
    }

    @GetMapping(value = "item/{item_id}/photo/small", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[]
    getItemSmallPhotoByItemId(@PathVariable("item_id") String itemId) throws IllegalStateException, IOException {
        Item selectedItem = itemClientService.getItemById(itemId);
        Photo photo = photoService.getPhoto(selectedItem.getMiniPhotoGridFsFileIds().iterator().next()); // TODO: refactor. It's not good because get's only first element from set
        return photo.getStream().readAllBytes();
    }

    @GetMapping(value = "item/{item_id}/photo/large", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getItemLargePhotoByItemId(@PathVariable("item_id") String itemId) throws IllegalStateException, IOException {
        Item selectedItem = itemClientService.getItemById(itemId);
        Photo photo = photoService.getPhoto(selectedItem.getPhotoGridFsFileIds().iterator().next()); // TODO: refactor. It's not good because get's only first element from set
        return photo.getStream().readAllBytes();
    }


    @GetMapping("item/{item_id}/download")
    public void getItemModel3dByItemId(@PathVariable("item_id") String itemId, HttpServletResponse response) throws IllegalStateException, IOException {
        Item selectedItem = itemClientService.getItemById(itemId);
        Model3d model = model3dService.getModel3d(selectedItem.getModelGridFsFileIds().iterator().next()); // TODO: refactor. It's not good because get's only first element from set
        FileCopyUtils.copy(model.getStream(), response.getOutputStream());
    }


    private ItemCardLongDTO convertItemToItemCardLongDTO(Item itemById) {
        String nicknameOfAuthor = "No author";
        String mailOfAuthor = "No mail of author";
        if(itemById.getAuthorReference() != null){
            nicknameOfAuthor = itemById.getAuthorReference().getNickname();
            mailOfAuthor = itemById.getAuthorReference().getMail();
        }
        return  new ItemCardLongDTO(itemById.getId(), itemById.getName(),itemById.getDescription(),itemById.getSizeInByte(),
                nicknameOfAuthor, mailOfAuthor);
    }

}
