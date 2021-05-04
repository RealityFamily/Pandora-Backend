package ru.realityfamily.pandorabackend.admin.v1.controller;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.realityfamily.pandorabackend.shared.models.gridfs.service.PhotoService;

import java.io.IOException;


@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin")
public class PhotoAdminController {
    PhotoService photoService;



 /*   @PostMapping("/videos/add")
    public String addVideo(@RequestParam("title") String title, @RequestParam("file") MultipartFile file, Model model) throws IOException {
        String id = photoService.addPhoto(title, file);
        return "redirect:/videos/" + id;
    }
*/


}
