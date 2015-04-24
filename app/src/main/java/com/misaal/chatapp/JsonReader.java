package com.misaal.chatapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Reads a JSON String and provides a method to extract message data into a list
 * Created by Misaal on 24/04/2015.
 */
public class JsonReader {

    public static final String TEST_DATA = "{\"chats\": [{\"timestamp\": \"default\", \"msg_id\": \"1\", \"msg_data\": \"Hello, how are you ?\", \"msg_type\": \"0\"}, {\"timestamp\": \"default\", \"msg_id\": \"2\", \"msg_data\": \"http://media.mediatemple.netdna-cdn.com/wp-content/uploads/2013/01/3.jpg\", \"msg_type\": \"1\"}, {\"timestamp\": \"default\", \"msg_id\": \"3\", \"msg_data\": \"How is weather ?\", \"msg_type\": \"0\"}, {\"timestamp\": \"default\", \"msg_id\": \"4\", \"msg_data\": \"http://media.mediatemple.netdna-cdn.com/wp-content/uploads/2013/01/3.jpg\", \"msg_type\": \"1\"}, {\"timestamp\": \"default\", \"msg_id\": \"5\", \"msg_data\": \"http://media.mediatemple.netdna-cdn.com/wp-content/uploads/2013/01/3.jpg\", \"msg_type\": \"1\"}, {\"timestamp\": \"default\", \"msg_id\": \"6\", \"msg_data\": \"Tomorrow is sunday\", \"msg_type\": \"0\"}, {\"timestamp\": \"default\", \"msg_id\": \"7\", \"msg_data\": \"To define one such view, you need to specify it an Android Context. This is usually the Activity where the tabs will be displayed. Supposing that you initialize your tabs in an Activity, simply pass the Activity instance as a Context\", \"msg_type\": \"0\"}, {\"timestamp\": \"default\", \"msg_id\": \"8\", \"msg_data\": \"http://media.mediatemple.netdna-cdn.com/wp-content/uploads/2013/01/3.jpg\", \"msg_type\": \"1\"}]}";
    private static final String MSG_ID = "msg_id";
    private static final String MSG_TYPE = "msg_type";
    private static final String MSG_DATA = "msg_data";
    private static final String MSG_TIMESTAMP = "timestamp";

    /** Extracts data from the JSON String and stores it in a List<Message>
     *
     * @return : List<Message>
     * @throws org.json.JSONException
     */
    public List<Message> getMessages() throws JSONException {
        JSONObject reader = new JSONObject(TEST_DATA);
        JSONArray messageArray = reader.getJSONArray("chats");
        List<Message> messages = new ArrayList<>();

        for(int i = 0; i < messageArray.length(); i++){
            JSONObject messageObj = messageArray.getJSONObject(i);
            String id = messageObj.getString(MSG_ID);
            String type = messageObj.getString(MSG_TYPE);
            String data = messageObj.getString(MSG_DATA);
            String time = messageObj.getString(MSG_TIMESTAMP);
            messages.add(new Message(id, type, data, time));
        }

        return messages;
    }

}
