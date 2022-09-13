package com.email.email_svc.Service;

import com.email.email_svc.MessageModels.EmailContents;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
final class EmailHandler {

    private final SpringTemplateEngine thymeleafTemplateEngine;
    private final JavaMailSender mailSender;

    public String createEmailMessage(EmailContents emailContents)
    {
        final Map<String,Object> templateVariables = new HashMap<>();
        templateVariables.put("recipientName",emailContents.getRecipientName());
        templateVariables.put("message",emailContents.getMessage());
        templateVariables.put("senderName",emailContents.getSendersName());

        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateVariables);

        return thymeleafTemplateEngine.process(
                emailContents.getMessageType()==0?"otp_template.html" : "notification.html",
                thymeleafContext
        );
    }

    void sendHtmlMessage(EmailContents emailContents, String Body) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");
            mimeMessageHelper.setFrom(emailContents.getSendersEmailID());
            mimeMessageHelper.setTo(emailContents.getRecipientEmailID());
            mimeMessageHelper.setSubject(emailContents.getSubject());
            mimeMessageHelper.setText(Body, true);
        }catch(MessagingException messagingException){
            System.out.println("Exception in creating MIME message : " + messagingException.getMessage());
        }
        boolean isMailSent=false;
        short cnt=0;
        do {
            try{
                mailSender.send(message);
                System.out.println("Mail sent");
                isMailSent = true;
            }catch(MailException mailException){
                System.out.println("Mail not sent : " + mailException.getMessage());
                ++cnt;
            }
        }while(!isMailSent && cnt<3);
    }
}