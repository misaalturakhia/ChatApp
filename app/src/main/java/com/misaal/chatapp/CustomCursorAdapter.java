package com.misaal.chatapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/** A custom cursor adapter that displays the contents of the table MESSAGES in a listview.
 *  Displays a textview for text messages and an imageview for image url data
 * Created by Misaal on 24/04/2015.
 */
public class CustomCursorAdapter extends CursorAdapter {

    private static final int TYPE_MESSAGE = 0;
    private static final int TYPE_IMAGE = 1;


    /**
     * Constructor
     * @param context
     * @param cursor
     */
    public CustomCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, true);
    }


    @Override
    public int getItemViewType(int position) {
        Cursor cursor = (Cursor)getItem(position);
        return getItemViewType(cursor);
    }


    /**
     * Returns an item view type depending on the message type of the message
     * @param cursor : Cursor
     * @return : int - 0 for message, 1 for image
     */
    private int getItemViewType(Cursor cursor){
        String type = cursor.getString(cursor.getColumnIndex(MessageDBHelper.COLUMN_MESSAGE_TYPE));
        if (type.equals("0")) {
            return TYPE_MESSAGE;
        } else if(type.equals("1")){
            return TYPE_IMAGE;
        }else{
            return -1; // error
        }
    }


    @Override
    public int getViewTypeCount() {
        return 2; // 2 views
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // get message type
        String messageType = cursor.getString(cursor.getColumnIndex(MessageDBHelper.COLUMN_MESSAGE_TYPE));
        View view;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(messageType.equals("1")){
            view = inflater.inflate(R.layout.list_item_iv, null);
            view.setTag(TYPE_IMAGE);
        }else{ // if the message type is "0" or another invalid value, we ddisplay it in a text view. At max,
               //the url data will be displayed as a text message
            view = inflater.inflate(R.layout.list_item_tv, null);
            view.setTag(TYPE_MESSAGE);
        }
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // get the tag attached to the view
        int tag = (int)view.getTag();
        // fetch the message data from the cursor
        String data = cursor.getString(cursor.getColumnIndex(MessageDBHelper.COLUMN_MESSAGE_DATA));
        if(tag == TYPE_MESSAGE){// if text message
            TextView messageTv = (TextView)view.findViewById(R.id.message_text_tv);
            messageTv.setText(data);
        }else if(tag == TYPE_IMAGE){// if image data
            ImageView messageIv = (ImageView)view.findViewById(R.id.message_image_iv);
            if(UtilityMethods.isNetConnected(context)){
                Picasso.with(context).load(data).fit().into(messageIv);
            }else{
                Picasso.with(context).load(R.drawable.no_image).fit().into(messageIv);
            }
        }
    }
}
