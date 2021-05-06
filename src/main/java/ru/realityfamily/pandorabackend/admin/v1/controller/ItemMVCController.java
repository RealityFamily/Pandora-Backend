package ru.realityfamily.pandorabackend.admin.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/admin")
public class ItemMVCController {


    @GetMapping("item/upload")
    public String PostItemThrowForm(Model model){
        return "uploadItem";
    }
}
