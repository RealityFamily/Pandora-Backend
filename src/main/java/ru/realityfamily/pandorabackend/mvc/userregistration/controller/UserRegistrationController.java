package ru.realityfamily.pandorabackend.mvc.userregistration.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

@Controller
@AllArgsConstructor
public class UserRegistrationController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private IUserClientService userClientService;
    private ApplicationEventPublisher eventPublisher;
    private MessageSource messages;
    private IUserClientService userService;

    @GetMapping("/user/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/user/registration")
    public GenericResponse registerUserAccount(@Valid final UserDto accountDto, final HttpServletRequest request){

        Locale locale = request.getLocale();

        LOGGER.debug("Registering user account with information: {}", accountDto);

        String appUrl = request.getContextPath();
        final User registered = userService.registerNewUserAccount(accountDto);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
        return new GenericResponse("success");

//        try {
//            User registred = userClientService.registerNewUserAccount(userDto);
//
//            String appUrl = request.getContextPath();
//            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registred, request.getLocale(), appUrl));
//        } catch (UserAlreadyExistException uaeEx) {
//            ModelAndView mav = new ModelAndView("registration", "user", userDto);
//            mav.addObject("message", /*"Аккаунт пользователя с таким именем/почтой уже существует");*/ messages.getMessage("UniqueUsername.user.username", null, locale));
//            return mav;
//        } catch (RuntimeException ex) {
//            ex.printStackTrace();
//            return new ModelAndView("emailError", "user", userDto);
//        }
//
//        // rest of implementation
//        return new ModelAndView("successRegister", "user", userDto);// delete this
    }

    @GetMapping("/registrationConfirm")
    public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) {
        Locale locale = request.getLocale();

        VerificationToken verificationToken = userClientService.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            return "redirect:/badUser.html";
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messages.getMessage("auth.message.expired", null, locale);
            model.addAttribute("message", messageValue);
            return "redirect:/badUser.html";
        }

        user.setEnabled(true);
        userClientService.saveRegistredUser(user);
        return "redirect:/";
    }
}
