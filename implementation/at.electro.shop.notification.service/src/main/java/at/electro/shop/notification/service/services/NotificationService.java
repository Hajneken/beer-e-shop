package at.electro.shop.notification.service.services;

import java.util.Arrays;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Value;

import at.electro.shop.notification.service.models.Email;

public abstract class NotificationService<T> {
    protected Email email;

    @Value("${spring.mail.username}")
    protected String username;

    @Value("${email.sendTo}")
    protected String sendTo;

    protected abstract String templatePath();

    protected abstract String subject();

    protected abstract Map<String, Object> buildTemplateMap(T obj);

    public void send(String emailAddress, T model) {
        email.setFrom(username);
        email.setSubject(subject());
        email.setTemplate(templatePath());
        email.addRecipient(emailAddress);
        email.getTo().addAll(Arrays.asList(sendTo.split(",")));
        email.setTemplateModel(buildTemplateMap(model));
        try {
            email.sendMessage();
        } catch (MessagingException e) {
            log("error sending message", e);
        }
    }

    protected abstract void log(String message, Object arg);
}
