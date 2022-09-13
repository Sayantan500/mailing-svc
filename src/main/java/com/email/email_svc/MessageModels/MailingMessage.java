package com.email.email_svc.MessageModels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public final class MailingMessage
{
    private String otp;
    private String recipientName;
    private String Subject;
    private String recipientEmailID;
}
