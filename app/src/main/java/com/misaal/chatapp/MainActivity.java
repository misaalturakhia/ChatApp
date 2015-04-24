package com.misaal.chatapp;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;


/**
 * Create Sqlite Database & a single table “MESSAGES” consisting of below columns

 Table columns:

 MESSAGE_ID (STRING)
 MESSAGE_TYPE (STRING) // 0 for text message: 1 for image url
 MESSAGE_DATA(STRING) // This could be String or Image web url.
 MESSAGE_TIMESTAMP (STRING)

 Populate test data from this link: http://pastebin.com/aqziuquq

 When the app opens, show a list view mapped to MESSAGES table using CursorLoader class.
 If the type of the message is “0” show a text view & if the type is 1 show an image in the list view.
 */
public class MainActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private CustomCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initialize helper, which creates the database if loaded the first time.
        MessageDBHelper helper = new MessageDBHelper(this);
        // get cursor
        Cursor cursor = helper.getMessages();

        mAdapter = new CustomCursorAdapter(this, cursor);
        setListAdapter(mAdapter);
        getLoaderManager().initLoader(0, null, this);

    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = MessageProvider.CONTENT_URI;
        return new CursorLoader(this, uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

}
