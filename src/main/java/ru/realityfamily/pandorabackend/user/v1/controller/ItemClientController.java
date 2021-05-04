package ru.realityfamily.pandorabackend.user.v1.controller;

import lombok.AllArgsConstructor;
import org.apache.tomcat.jni.File;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.realityfamily.pandorabackend.shared.models.Item;
import ru.realityfamily.pandorabackend.shared.models.gridfs.Photo;
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

    @GetMapping("item/{item_id}")
    public ItemCardLongDTO getItemById(@PathVariable("item_id") String id){
        return convertItemToItemCardLongDTO(itemClientService.getItemById(id));
    }

    @GetMapping("item/{item_id}/photo/small")
    public void getItemSmallPhotoByItemId(@PathVariable("item_id") String itemId, HttpServletResponse response) throws IllegalStateException, IOException {
        Item selectedItem = itemClientService.getItemById(itemId);
        Photo photo = photoService.getPhoto(selectedItem.getMiniPhotoGridFsFileIds().iterator().next()); // TODO: refactor. It's not good because get's only first element from set
        FileCopyUtils.copy(photo.getStream(), response.getOutputStream());
//        response.addHeader("photoName", selectedItem.getName());
    }

    @GetMapping("item/{item_id}/photo/large")
    public void getItemLargePhotoByItemId(@PathVariable("item_id") String itemId, HttpServletResponse response) throws IllegalStateException, IOException {
        Item selectedItem = itemClientService.getItemById(itemId);
        Photo photo = photoService.getPhoto(selectedItem.getPhotoGridFsFileIds().iterator().next()); // TODO: refactor. It's not good because get's only first element from set
        FileCopyUtils.copy(photo.getStream(), response.getOutputStream());
    }

    private ItemCardLongDTO convertItemToItemCardLongDTO(Item itemById) {
        return  new ItemCardLongDTO(itemById.getId(), itemById.getName(),itemById.getDescription(),itemById.getSizeInByte(),
                itemById.getAuthorReference().getNickname(), itemById.getAuthorReference().getMail());
    }

}
