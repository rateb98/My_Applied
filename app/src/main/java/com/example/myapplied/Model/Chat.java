package com.example.myapplied.Model;

public class Chat {
    private String sender;
    private String receiver;
    private String massage;


    public Chat(String sender, String receiver, String massage) {
        this.sender = sender;
        this.receiver = receiver;
        this.massage = massage;
    }

    public Chat() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
