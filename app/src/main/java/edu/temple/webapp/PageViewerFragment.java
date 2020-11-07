package edu.temple.webapp;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PageViewerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PageViewerFragment extends Fragment implements Parcelable {

    String currURL;
    String savedURL;
    WebView myWebView;
    private Bundle webViewBundle;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String KEY_URL = "urlkey";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PageViewerFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static PageViewerFragment newInstance(String url) {
        PageViewerFragment fragment = new PageViewerFragment();
        Bundle args = new Bundle();
        args.putString(KEY_URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle;
        setRetainInstance(true);
    }
    public void onPause() {
        super.onPause();
        webViewBundle = new Bundle();
        myWebView.saveState(webViewBundle);
    }
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View l = inflater.inflate(R.layout.fragment_page_viewer, container, false);
        myWebView = (WebView) l.findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new MyWebViewClient());

        if(webViewBundle != null)
        {
            myWebView.restoreState(webViewBundle);
        }

        return l;
    }
    public String loadPage(String url)
    {
        if(url != null)
        {
            try {
                URL obj = new URL(url);
                myWebView.loadUrl(url);
            }
            catch (MalformedURLException e)
            {
                url = "https://www." + url;
                myWebView.loadUrl(url);
            }
        }
        currURL = url;
        return url;
    }
    public String getURL()
    {
        return currURL;
    }
    public void goBack()
    {
        if(myWebView.canGoBack())
        {
            myWebView.goBack();
        }
    }
    public void goForward()
    {
        if(myWebView.canGoForward())
        {
            myWebView.goForward();

        }

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            return false;
        }
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //((changeEditText) getActivity()).changeText(getURL());
        }
    }
    interface changeEditText {
        public void changeText(String url);
    }

    protected PageViewerFragment(Parcel in) {
        savedURL = in.readString();
        myWebView = (WebView) in.readValue(WebView.class.getClassLoader());
        webViewBundle = in.readBundle();
        mParam1 = in.readString();
        mParam2 = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(savedURL);
        dest.writeValue(myWebView);
        dest.writeBundle(webViewBundle);
        dest.writeString(mParam1);
        dest.writeString(mParam2);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PageViewerFragment> CREATOR = new Parcelable.Creator<PageViewerFragment>() {
        @Override
        public PageViewerFragment createFromParcel(Parcel in) {
            return new PageViewerFragment(in);
        }

        @Override
        public PageViewerFragment[] newArray(int size) {
            return new PageViewerFragment[size];
        }
    };
}
