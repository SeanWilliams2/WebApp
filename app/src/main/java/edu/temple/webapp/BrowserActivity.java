package edu.temple.webapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BrowserActivity extends AppCompatActivity implements PageControlFragment.ButtonClickInterface, PageViewerFragment.changeEditText, BrowserControlFragment.tabButtonClickInterface, PagerFragment.changeEditText, PageListFragment.changePager {
    private static PageControlFragment pageControler = new PageControlFragment();
    private static PagerFragment pagerfragment = new PagerFragment();
    private static BrowserControlFragment browsercontrol = new BrowserControlFragment();
    private static PageListFragment pagelist = new PageListFragment();
    private static ArrayList<PageViewerFragment> fragments = new ArrayList<PageViewerFragment>();
    private static ArrayList<String> websites = new ArrayList<String>();
    private int lastPosition = 0;
    private boolean pageListOn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


            setContentView(R.layout.activity_main);
            if(getSupportFragmentManager().findFragmentById(R.id.page_controller) == null) //if fragment 1,2,3 are null start program
            {
                websites.add(new String());
                pageControler.newInstance("");
                fragments.add(new PageViewerFragment());
                pagerfragment.newInstance(fragments, 0);
                pagelist.newInstance(fragments, websites);
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.page_controller, pageControler)
                        .add(R.id.page_viewer, pagerfragment)
                        .add(R.id.browser_control, browsercontrol)
                        .commit();
                if(findViewById(R.id.page_list) != null) //if we are in landscape add fourth fragment
                {
                    pageListOn = true;
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.page_list, pagelist)
                            .commit();
                    //pagelist.updateWebsites(websites);
                }
            }
            else //else we already have instances
            {
                 if(getSupportFragmentManager().findFragmentById(R.id.page_list) == null) //if theres no fragment in pagelist
                    {
                        pageListOn = true;
                        getSupportFragmentManager()
                                .beginTransaction()
                                .add(R.id.page_list, pagelist)
                                .commit();
                        pagelist.updateWebsites(websites);

                    }
                    else
                    {
                        pagelist = (PageListFragment) getSupportFragmentManager().findFragmentById(R.id.page_list);
                        pagelist.updateWebsites(websites);

                    }


                pageControler = (PageControlFragment) getSupportFragmentManager().findFragmentById(R.id.page_controller);
                pagerfragment = (PagerFragment) getSupportFragmentManager().findFragmentById(R.id.page_viewer);
                browsercontrol = (BrowserControlFragment) getSupportFragmentManager().findFragmentById(R.id.browser_control);
            }
                

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
        int position = pagerfragment.fragmentChange(fragments);
        if(pageListOn)
        {
            pagelist.newItem(fragments,position);
        }

        websites.add(new String());
        websites.set(position,"");
        pageControler.updateEditText("");
        changeTitle("");
    }
    public void changeTitle(String title, String url)
    {
        if(url != null && pagerfragment.getCurUrl() != null) {
            if (pagerfragment.getCurUrl().equals(url)) {
                BrowserActivity.this.setTitle(title);
                changeText(pagerfragment.getCurUrl());
                websites.set(pagerfragment.getLastPosition(), title);
            }
            if (pageListOn) {
                pagelist.fragmentChange(fragments, pagerfragment.getLastPosition());
            }
        }
    }
    public void changeTitle(String title)
    {
        BrowserActivity.this.setTitle(title);
    }
    public void changePager(int position)
    {
        pagerfragment.updateViewer(position);
        changeText(pagerfragment.getCurUrl());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

}