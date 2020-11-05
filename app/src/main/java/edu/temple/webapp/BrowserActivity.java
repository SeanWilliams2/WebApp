package edu.temple.webapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BrowserActivity extends AppCompatActivity implements PageControlFragment.ButtonClickInterface, PageViewerFragment.changeEditText {
    PageControlFragment pageControler = new PageControlFragment();
    PageViewerFragment pageViewer = new PageViewerFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pageControler.newInstance("");
        pageViewer.newInstance("");
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_1,pageControler)
                .add(R.id.container_2,pageViewer)
                .commit();
    }

    @Override
    public void goButtonClick(String str) {
        String url = pageViewer.loadPage(str);
        pageControler.updateEditText(url);
    }

    @Override
    public void backButtonClick(String url) {
        pageViewer.goBack();
    }

    @Override
    public void forwardButtonClick(String url) {
        pageViewer.goForward();
    }

    public void changeText(String url)
    {
        pageControler.updateEditText(url);
    }
}