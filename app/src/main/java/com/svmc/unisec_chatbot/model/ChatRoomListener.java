package com.svmc.unisec_chatbot.model;

public interface ChatRoomListener {
    void onConnectSuccess(String message);
    void onConnectFail(String error);
    void onMessageSuccess(String message);
    void onMessageFail(String error);
}
