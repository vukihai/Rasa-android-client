package com.vukihai.unisecchatbot.data.model;

public class ChatMessage {
    private String text;
    private boolean isBotMessage;
    private boolean isBotSlider;
    private boolean isBotHtmlview;
    private boolean isBotTable;
    private boolean isBotButton;
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

    public boolean isBotSlider() {
        return isBotSlider;
    }

    public void setBotSlider(boolean botSlider) {
        isBotSlider = botSlider;
    }

    public boolean isBotHtmlview() {
        return isBotHtmlview;
    }

    public void setBotHtmlview(boolean botHtmlview) {
        isBotHtmlview = botHtmlview;
    }

    public boolean isBotTable() {
        return isBotTable;
    }

    public void setBotTable(boolean botTable) {
        isBotTable = botTable;
    }

    public boolean isBotButton() {
        return isBotButton;
    }

    public void setBotButton(boolean botButton) {
        isBotButton = botButton;
    }
}
