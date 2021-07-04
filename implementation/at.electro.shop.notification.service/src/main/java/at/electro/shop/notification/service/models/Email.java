package at.electro.shop.notification.service.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Component
public class Email {
    private List<String> to;
    private String from;
    private String subject;
    private String template;
    private Map<String, Object> templateModel = new HashMap<String, Object>();

    @Autowired
    private SpringTemplateEngine thymeleafTemplateEngine;
    private final JavaMailSender emailSender;

    public Email(JavaMailSender emailSender) {
        to = new ArrayList<String>();
        this.emailSender = emailSender;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Map<String, Object> getTemplateModel() {
        return templateModel;
    }

    public void setTemplateModel(Map<String, Object> templateModel) {
        this.templateModel = templateModel;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public void addRecipient(String rec) {
        to.add(rec);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void sendMessage() throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        var thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        String htmlBody = thymeleafTemplateEngine.process(template, thymeleafContext);
        helper.setTo(to.stream().toArray(String[]::new));
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        helper.setFrom(from);
        emailSender.send(message);
    }
}
