package com.misaal.chatapp;

/**
 * Created by Misaal on 24/04/2015.
 */
public class Message {

    private String id;

    private String type;

    private String data;

    private String timeStamp;


    /**
     * Constructor
     * @param msgId
     * @param msgType
     * @param msgData
     * @param msgTimeStamp
     */
    public Message(String msgId, String msgType, String msgData, String msgTimeStamp){
        this.id = msgId;
        this.type = msgType;
        this.data = msgData;
        this.timeStamp = msgTimeStamp;
    }

    //GETTERS

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getData() {
        return data;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

}
