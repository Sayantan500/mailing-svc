package com.email.email_svc.MessageModels;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class EmailContents
{
    private final String recipientName;
    private final String Subject;
    private final String message;
    private final String recipientEmailID;
    private final byte messageType;
    private String sendersEmailID;
    private String sendersName;

    public void setSendersEmailID(String sendersEmailID){
        this.sendersEmailID = sendersEmailID;
    }

    public void setSendersName(String sendersName) {
        this.sendersName = sendersName;
    }
}
