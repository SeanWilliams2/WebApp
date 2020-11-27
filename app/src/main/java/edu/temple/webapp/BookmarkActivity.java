package edu.temple.webapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;

public class BookmarkActivity extends AppCompatActivity {
    private static ArrayList<PageViewerFragment> fragments = new ArrayList<PageViewerFragment>();
    private static ArrayList<String> bookmarks;
    private static ArrayList<String> urls;
    private static ArrayList<String> tempBookmarks;
    private static ArrayList<String> tempUrls;
    private boolean setBookmark = false;
    File file;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmark_activity);

        bookmarks = PrefConfig.readTitleFromPref(this);
        if(bookmarks == null)
            bookmarks = new ArrayList<String>();
        urls = PrefConfig.readUrlFromPref(this);
        if(urls == null)
            urls = new ArrayList<String>();

        tempBookmarks = getIntent().getStringArrayListExtra("bookmarks");
        tempUrls = getIntent().getStringArrayListExtra("urls");


        if(tempBookmarks != null)
            bookmarks.addAll(tempBookmarks);
        if(tempUrls != null) {
            urls.addAll(tempUrls);
        }




        Intent resultIntent = new Intent();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        BookmarkAdapter adapter = new BookmarkAdapter(bookmarks,this);
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("load", urls.get(position));
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
            });
        listview.setAdapter(adapter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        PrefConfig.writeListInPref(getApplicationContext(),urls,bookmarks);
    }


}
