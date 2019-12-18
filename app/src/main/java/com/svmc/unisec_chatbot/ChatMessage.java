package com.svmc.unisec_chatbot;

public class ChatMessage {
    private String text;
    private boolean isBotMessage;

    public ChatMessage(String text, boolean isBotMessage) {
        this.text = text;
        this.isBotMessage = isBotMessage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isBotMessage() {
        return isBotMessage;
    }

    public void setBotMessage(boolean botMessage) {
        isBotMessage = botMessage;
    }
}
