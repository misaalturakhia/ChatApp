package com.misaal.chatapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Misaal on 24/04/2015.
 */
public class MessageDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "MessageDB.db";
    public static final String MESSAGE_TABLE_NAME = "MESSAGES";
    public static final String COLUMN_MESSAGE_ID = "_id";
    public static final String COLUMN_MESSAGE_TYPE = "MESSAGE_TYPE";// 0 for text message, 1 for image
    public static final String COLUMN_MESSAGE_DATA = "MESSAGE_DATA"; // textmessage or image url
    public static final String COLUMN_MESSAGE_TIME = "MESSAGE_TIMESTAMP"; // time stamp


    /**
     * Constructor
     * @param context : Context
     */
    public MessageDBHelper(Context context){
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + MESSAGE_TABLE_NAME + " " +
                        "(" + COLUMN_MESSAGE_ID + " text primary key, "
                        + COLUMN_MESSAGE_TYPE + " text, "
                        + COLUMN_MESSAGE_DATA + " text, "
                        + COLUMN_MESSAGE_TIME + " text)"
        );
        JsonReader reader = new JsonReader();
        List<Message> messages = null;
        try {
            messages = reader.getMessages();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(messages != null){
            insertMessages(db, messages);
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS "+ MESSAGE_TABLE_NAME);
        onCreate(db);
    }


    public void deleteTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+ MESSAGE_TABLE_NAME);
        db.close();
    }


    /**
     * Inserts all the data from the list of restaurant objects into the table 'restaurants'
     * @param messages
     * @return
     */
    public void insertMessages(SQLiteDatabase db, List<Message> messages){
        for(Message message : messages){
            ContentValues contentValues = createRowContent(message.getId(), message.getType(),
                    message.getData(), message.getTimeStamp());
            db.insert(MESSAGE_TABLE_NAME, null, contentValues);
        }
    }


    /**
     * Creates a ContentValues object that holds data corresponding to one row in the MESSAGES table
     * @param msgId : id of the message
     * @param msgType : type of the message: 0 - text message, 1 - image
     * @param msgData : text of the message or url of the image
     * @param msgTimeStamp : time stamp of the message
     * @return : ContentValues
     */
    private ContentValues createRowContent(String msgId, String msgType, String msgData, String msgTimeStamp){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_MESSAGE_ID, msgId);
        contentValues.put(COLUMN_MESSAGE_TYPE, msgType);
        contentValues.put(COLUMN_MESSAGE_DATA, msgData);
        contentValues.put(COLUMN_MESSAGE_TIME, msgTimeStamp);
        return contentValues;
    }


    /**
     * Performs a read for all restaurant data and returns it in the form of List<Restaurant>
     * @return : List<Restaurant>
     */
    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+ MESSAGE_TABLE_NAME, null);
        if(!res.moveToFirst()){ // if the query returns nothing
            return messages;
        }
        while(res.isAfterLast() == false){
            String id = res.getString(res.getColumnIndex(COLUMN_MESSAGE_ID));
            String type = res.getString(res.getColumnIndex(COLUMN_MESSAGE_TYPE));
            String data = res.getString(res.getColumnIndex(COLUMN_MESSAGE_DATA));
            String time = res.getString(res.getColumnIndex(COLUMN_MESSAGE_TIME));

            messages.add(new Message(id, type, data, time));
            res.moveToNext();
        }
        db.close();
        return messages;
    }


    /**
     *
     * @return
     */
    public Cursor getMessages(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "select * from "+ MESSAGE_TABLE_NAME, null);
    }

}
