package ru.realityfamily.pandorabackend.mvc.userregistration.listener;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ru.realityfamily.pandorabackend.mvc.userregistration.OnRegistrationCompleteEvent;
import ru.realityfamily.pandorabackend.shared.models.User;
import ru.realityfamily.pandorabackend.user.v1.service.IUserClientService;

import java.util.UUID;

@Component
@AllArgsConstructor
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private IUserClientService service;

    private MessageSource messages;

    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event){
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        String recipientAddress = user.getMail();
        String subject = "Подтверждение регистрации";
        String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token="+token;
        String message = "Вы зарегистрировались успешно. Кликните на ссылку ниже для подтверждения";//messages.getMessage("message.regSucc", null, event.getLocale());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message+ "\r\n" + "http://localhost:8082"+ confirmationUrl);
        mailSender.send(email);
    }
}
