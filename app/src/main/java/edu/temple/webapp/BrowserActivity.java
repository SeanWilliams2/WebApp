package edu.temple.webapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.pdf.PdfDocument;
import android.os.Bundle;

import java.util.ArrayList;

public class BrowserActivity extends AppCompatActivity implements PageControlFragment.ButtonClickInterface, PageViewerFragment.changeEditText, BrowserControlFragment.tabButtonClickInterface, PagerFragment.changeEditText, PageListFragment.changePager {
    PageControlFragment pageControler = new PageControlFragment();
    PagerFragment pagerfragment = new PagerFragment();
    BrowserControlFragment browsercontrol = new BrowserControlFragment();
    PageListFragment pagelist = new PageListFragment();
    ArrayList<PageViewerFragment> fragments = new ArrayList<PageViewerFragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pageControler.newInstance("");
        fragments.add(new PageViewerFragment());
        pagerfragment.newInstance(fragments);
        pagelist.newInstance(fragments);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_1,pageControler)
                .add(R.id.container_2,pagerfragment)
                .add(R.id.container_3,browsercontrol)
                .add(R.id.container_4,pagelist)
                .commit();
    }

    @Override
    public void goButtonClick(String str) {
        String url = pagerfragment.loadPage(str);
        pageControler.updateEditText(url);
        //pagelist.fragmentChange(fragments,pagerfragment.getLastPosition());
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
        int position =  pagerfragment.fragmentChange(fragments);
        pagelist.newItem(fragments,position);
        pageControler.updateEditText("");
    }
    public void changeTitle(String title)
    {
        BrowserActivity.this.setTitle(title);
        pagelist.fragmentChange(fragments,pagerfragment.getLastPosition());
    }
    public void changePager(int position)
    {
        pagerfragment.updateViewer(position);
    }

}