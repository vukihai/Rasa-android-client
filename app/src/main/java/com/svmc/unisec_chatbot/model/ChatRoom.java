package com.svmc.unisec_chatbot.model;

import android.content.Context;
import android.util.Log;

import com.apptakk.http_request.HttpRequest;
import com.apptakk.http_request.HttpRequestTask;
import com.apptakk.http_request.HttpResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChatRoom {
    private ChatRoomListener chatRoomListener;
    private String apiServerAddr;
    private String conversationId;
    private boolean isConnected = false;

    public ChatRoom(Context mContext, String apiServerAddr, String conversationId) {
        this.apiServerAddr = apiServerAddr;
        this.conversationId = conversationId;
        try {
            chatRoomListener = ((ChatRoomListener) mContext);
        } catch (ClassCastException e) {
            throw new ClassCastException(mContext.toString() + " must implement ChatRoomListener");
        }
    }

    public void connect() {
        new HttpRequestTask(
                new HttpRequest(this.getApiServerAddr(), HttpRequest.GET),
                new HttpRequest.Handler() {
                    @Override
                    public void response(HttpResponse response) {
                        try {
                            if (response.code == 200) {
                                chatRoomListener.onConnectSuccess(response.body);
                                isConnected = true;
                            } else {
                                chatRoomListener.onConnectFail(response.body);
                            }
                        } catch (NullPointerException e){
                            chatRoomListener.onConnectFail("can NOT connect to server");
                        }
                    }
                }
        ).execute();
    }
    public void sendMessage(String message){
        if(!this.isConnected){ // try to connect one more time...
            this.connect();
        }
        if(!this.isConnected){
            chatRoomListener.onMessageFail("can NOT connect to server");
            return;
        }
        RequestSchema requestSchema = new RequestSchema(message, this.getConversationId());
        String uri = this.getApiServerAddr() + "/webhooks/rest/webhook/";
        new HttpRequestTask(
                new HttpRequest(uri, HttpRequest.POST, requestSchema.toString()),
                new HttpRequest.Handler() {
                    @Override
                    public void response(HttpResponse response) {
                        try {
                            if (response.code == 200) {
                                try {
                                    JSONArray res = new JSONArray(response.body);
                                    JSONObject responseMes = res.getJSONObject(0);
                                    chatRoomListener.onMessageSuccess(responseMes.getString("text"));

                                } catch (JSONException e){
                                    chatRoomListener.onMessageFail(e.toString());
                                }
                            } else {
                                chatRoomListener.onMessageFail(response.body);
                            }
                        } catch (NullPointerException e){
                            chatRoomListener.onMessageSuccess("can NOT connect to server");
                        }
                    }
                }
        ).execute();
    }
    public String getApiServerAddr() {
        return apiServerAddr;
    }

    public void setApiServerAddr(String apiServerAddr) {
        this.apiServerAddr = apiServerAddr;
        this.isConnected = false;
    }

    public String getConversationId() {
        return conversationId;
    }
}
