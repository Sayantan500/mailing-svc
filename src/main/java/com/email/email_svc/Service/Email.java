package com.email.email_svc.Service;

import com.email.email_svc.MessageModels.EmailContents;

@FunctionalInterface
public interface Email
{
    void sendMessage(EmailContents emailContents);
}
