package com.email.email_svc.Service;

import com.email.email_svc.MessageModels.EmailContents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
final class EmailService implements Email
{
    @Value("${senders.name}")
    private String sendersName;

    @Value("${senders.email}")
    private String sendersEmail;

    private final EmailHandler emailHandler;

    @Autowired
    public EmailService(EmailHandler emailHandler) {
        this.emailHandler = emailHandler;
    }

    @Override
    public void sendMessage(EmailContents emailContents) {
        emailContents.setSendersEmailID(sendersEmail);
        emailContents.setSendersName(sendersName);
        String emailMessage = emailHandler.createEmailMessage(emailContents);
        emailHandler.sendHtmlMessage(emailContents,emailMessage);
    }
}
