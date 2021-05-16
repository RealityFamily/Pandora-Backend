package ru.realityfamily.pandorabackend.admin.v1.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.realityfamily.pandorabackend.shared.models.Category;
import ru.realityfamily.pandorabackend.shared.models.Item;
import ru.realityfamily.pandorabackend.user.v1.service.ICategoryClientService;
import ru.realityfamily.pandorabackend.user.v1.service.IItemClientService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("api/v1/admin")
public class ItemMVCController {

    ICategoryClientService iCategoryClientService;
    IItemClientService itemClientService;

    @GetMapping("item/upload")
    public String postItemThrowForm(Model model){
        return "uploadItem";
    }

    @GetMapping("item/all")
    public String displayItems(Model model){
        List<Item> allItems = itemClientService.getAllItems();
        model.addAttribute("items", allItems);
        return "allItems";
    }

    @GetMapping("category/detailed")
    public String displayAllCategorysDetailed(Model model){
        List<Category> categories = iCategoryClientService.getAllCategorys();
        model.addAttribute("categorys", categories);
        return "allCategorys";
    }
}
