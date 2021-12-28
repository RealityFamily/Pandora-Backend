package ru.realityfamily.pandorabackend.mvc.userregistration.listener;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ru.realityfamily.pandorabackend.mvc.userregistration.OnRegistrationCompleteEvent;
import ru.realityfamily.pandorabackend.shared.models.User;
import ru.realityfamily.pandorabackend.user.v1.service.IUserClientService;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final IUserClientService service;

    private final MessageSource messages;

    private final JavaMailSender mailSender;

    @Setter
    @Value("${server.web.address}")
    private String baseServerAddress;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        String recipientAddress = user.getMail();
        String subject = "Подтверждение регистрации";
        String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + token;
        String message = "Вы зарегистрировались успешно. Кликните на ссылку ниже для подтверждения";//messages.getMessage("message.regSucc", null, event.getLocale());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "" + baseServerAddress.trim() + confirmationUrl);
        mailSender.send(email);
    }
}
