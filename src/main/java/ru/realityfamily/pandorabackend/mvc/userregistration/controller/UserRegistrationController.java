package ru.realityfamily.pandorabackend.mvc.userregistration.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ru.realityfamily.pandorabackend.mvc.userregistration.dto.UserDto;
import ru.realityfamily.pandorabackend.shared.models.User;
import ru.realityfamily.pandorabackend.user.v1.service.IUserClientService;
import ru.realityfamily.pandorabackend.user.v1.service.exceptions.UserAlreadyExistException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class UserRegistrationController {
    IUserClientService userClientService;

    @GetMapping("/user/registration")
    public String showRegistrationForm(WebRequest request, Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/user/registration")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDto userDto,
            HttpServletRequest request,
            Errors errors){

        ModelAndView mav = new ModelAndView();
        try {
            User registred = userClientService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException uaeEx){
            mav.addObject("message", "An account for that username/email already exists");
            return mav;
        }

        // rest of implementation
        return  new ModelAndView("successRegister", "user", userDto);// delete this
    }
}
