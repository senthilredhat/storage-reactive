package com.brightly.storage.utility;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class FGATransactionRequestHolder {

    private List<String> sentMessageQueue;

    public List<String> getSentMessageQueue() {
        return sentMessageQueue;
    }

    public void setSentMessageQueue(List<String> sentMessageQueue) {
        this.sentMessageQueue = sentMessageQueue;
    }

    public void addToQueue(String message){
        if (sentMessageQueue == null) {
            sentMessageQueue = new ArrayList<>();
        }
        sentMessageQueue.add(message);
    }
}
