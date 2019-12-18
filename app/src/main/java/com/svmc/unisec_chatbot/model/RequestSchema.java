package com.svmc.unisec_chatbot.model;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestSchema {
    private String message;
    private String sender;
    private ParseData parse_data;

    public RequestSchema(String message, String sender) {
        this.message = message;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public ParseData getParse_data() {
        return parse_data;
    }

    public void setParse_data(ParseData parse_data) {
        this.parse_data = parse_data;
    }

    @Override
    public String toString(){
        JSONObject ret = new JSONObject();
        try{
            ret.put("message", this.getMessage());
            ret.put("sender", this.getSender());
        } catch (JSONException e){
            return "";
        }
        return ret.toString();
    }
    public class ParseData{
    }
}