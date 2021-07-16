package ru.realityfamily.pandorabackend.mvc.userregistration.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ru.realityfamily.pandorabackend.mvc.userregistration.OnRegistrationCompleteEvent;
import ru.realityfamily.pandorabackend.mvc.userregistration.dto.UserDto;
import ru.realityfamily.pandorabackend.mvc.userregistration.util.GenericResponse;
import ru.realityfamily.pandorabackend.shared.models.User;
import ru.realityfamily.pandorabackend.shared.models.VerificationToken;
import ru.realityfamily.pandorabackend.user.v1.service.IUserClientService;
import ru.realityfamily.pandorabackend.user.v1.service.exceptions.UserAlreadyExistException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;
import java.util.Optional;

@Controller
@AllArgsConstructor
@Slf4j
public class UserRegistrationController {
    private IUserClientService userClientService;
    private MessageSource messages;
    private IUserClientService userService;

    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }


    @GetMapping("/registrationConfirm")
    public ModelAndView confirmRegistration(WebRequest request, ModelMap model, @RequestParam("token") String token) {
        Locale locale = request.getLocale();

        String result = userClientService.validateVerificationToken(token);

        if (result.equals("valid")) {

            return new ModelAndView("redirect:/successVerified");
        }

        model.addAttribute("messageKey", "auth.message." + result);
        model.addAttribute("expired", "expired".equals(result));
        model.addAttribute("token", token);
        return new ModelAndView("redirect:/badUser", model);

    }

    @GetMapping("/badUser")
    public ModelAndView badUser(final HttpServletRequest request, final ModelMap model, @RequestParam("messageKey" ) final Optional<String> messageKey, @RequestParam("expired" ) final Optional<String> expired, @RequestParam("token" ) final Optional<String> token) {

        Locale locale = request.getLocale();
        messageKey.ifPresent( key -> {
                    String message = messages.getMessage(key, null, locale);
                    model.addAttribute("message", message);
                }
        );

        expired.ifPresent( e -> model.addAttribute("expired", e));
        token.ifPresent( t -> model.addAttribute("token", t));

        return new ModelAndView("badUser", model);
    }

    @GetMapping("/successRegister")
    public ModelAndView registredSucssesfully(final HttpServletRequest request){
        Locale locale = request.getLocale();
        return new ModelAndView("successRegister");
    }

    @GetMapping("/successVerified")
    public ModelAndView verifiedSuccessfully(final HttpServletRequest request){
        Locale locale = request.getLocale();
        return new ModelAndView("successVerified");
    }
}
