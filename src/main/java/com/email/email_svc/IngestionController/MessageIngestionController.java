package com.email.email_svc.IngestionController;

import com.email.email_svc.MessageModels.MailingMessage;
import com.email.email_svc.MessageModels.EmailContents;
import com.email.email_svc.Service.Email;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public final class MessageIngestionController {

   private final Email emailService;

    public MessageIngestionController(Email emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "OTP_MAILING_QUEUE")
    public void OtpMessageController(MailingMessage messageFromRabbit)
    {
        //Todo : To be removed before final push
        System.out.println("message = " + messageFromRabbit.getOtp() + "\t" + messageFromRabbit.getRecipientEmailID());
        EmailContents emailContents =
                new EmailContents(
                        messageFromRabbit.getRecipientName(),
                        messageFromRabbit.getSubject(),
                        messageFromRabbit.getOtp(),
                        messageFromRabbit.getRecipientEmailID(),
                        (byte) 0);
        emailService.sendMessage(emailContents);
    }

    //Todo : write the method to handle the other notification messages,
    // i.e. messages from notification queue.

}
