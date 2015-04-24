package com.misaal.chatapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by Misaal on 24/04/2015.
 */
public class MessageProvider extends ContentProvider {

    public static final String PROVIDER_NAME = "com.misaal.chatapp.messageprovider";

    /** A uri to do operations on cust_master table. A content provider is identified by its uri */
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/messages" );

    /** Constants to identify the requested operation */
    private static final int MESSAGES = 1;

    private static final UriMatcher uriMatcher ;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "messages", MESSAGES);
    }

    /** This content provider does the database operations by this object */
    MessageDBHelper mMessageDbHelper;


    @Override
    public boolean onCreate() {
        mMessageDbHelper = new MessageDBHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if(uriMatcher.match(uri)== MESSAGES){
            return mMessageDbHelper.getMessages();
        }else{
            return null;
        }
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
