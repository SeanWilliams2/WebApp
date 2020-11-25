package edu.temple.webapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BookmarkActivity extends AppCompatActivity {
    private static ArrayList<PageViewerFragment> fragments = new ArrayList<PageViewerFragment>();
    private static ArrayList<String> bookmarks = new ArrayList<String>();
    private static ArrayList<String> urls = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmark_activity);
        Intent resultIntent = new Intent();
        bookmarks = getIntent().getStringArrayListExtra("bookmarks");
        urls = getIntent().getStringArrayListExtra("urls");

        BookmarkAdapter adapter = new BookmarkAdapter(bookmarks,this);
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("load", urls.get(position).toString());
                resultIntent.putExtra("bookmarks", bookmarks);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
            });
        listview.setAdapter(adapter);

    }


}
