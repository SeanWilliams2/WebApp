package edu.temple.webapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.pdf.PdfDocument;
import android.os.Bundle;

import java.util.ArrayList;

public class BrowserActivity extends AppCompatActivity implements PageControlFragment.ButtonClickInterface, PageViewerFragment.changeEditText, BrowserControlFragment.tabButtonClickInterface, PagerFragment.changeEditText {
    PageControlFragment pageControler = new PageControlFragment();
    PagerFragment pagerfragment = new PagerFragment();
    BrowserControlFragment browsercontrol = new BrowserControlFragment();
    ArrayList<PageViewerFragment> fragments = new ArrayList<PageViewerFragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pageControler.newInstance("");
        fragments.add(new PageViewerFragment());
        pagerfragment.newInstance(fragments);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_1,pageControler)
                .add(R.id.container_2,pagerfragment)
                .add(R.id.container_3,browsercontrol)
                .commit();
    }

    @Override
    public void goButtonClick(String str) {
        String url = pagerfragment.loadPage(str);
        pageControler.updateEditText(url);
    }

    @Override
    public void backButtonClick(String url) {
        pagerfragment.goBack();
    }

    @Override
    public void forwardButtonClick(String url) {
        pagerfragment.goForward();
    }

    public void changeText(String url)
    {
        pageControler.updateEditText(url);
    }

    public void tabButtonClick() {
        fragments.add(new PageViewerFragment());
        pagerfragment.fragmentChange(fragments);
        pageControler.updateEditText("");
    }
}