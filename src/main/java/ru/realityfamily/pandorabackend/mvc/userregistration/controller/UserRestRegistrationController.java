package ru.realityfamily.pandorabackend.mvc.userregistration.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.realityfamily.pandorabackend.mvc.userregistration.OnRegistrationCompleteEvent;
import ru.realityfamily.pandorabackend.mvc.userregistration.dto.UserDto;
import ru.realityfamily.pandorabackend.mvc.userregistration.util.GenericResponse;
import ru.realityfamily.pandorabackend.shared.models.User;
import ru.realityfamily.pandorabackend.user.v1.service.IUserClientService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

@RestController
@AllArgsConstructor
@Slf4j
public class UserRestRegistrationController {

    private final IUserClientService userService;
    private final ApplicationEventPublisher eventPublisher;


    @PostMapping("/user/registration")
    public GenericResponse registerUserAccount(@Valid final UserDto accountDto, final HttpServletRequest request){

        Locale locale = request.getLocale();

        log.debug("Registering user account with information: {}", accountDto);

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
}
