package edu.temple.webapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PagerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String FRAGMENTS_KEY = "fragmentskey";
    private static final String VALUE_KEY = "doesntmatter";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int lastPosition = 0;
    private static ArrayList<PageViewerFragment> fragments;
    private PageViewerFragment currPageViewer;
    ViewPager viewpager;
    public PagerFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static void newInstance(ArrayList<PageViewerFragment> fragment, int lastPosition) {
        fragments = new ArrayList<PageViewerFragment>();
        fragments = (ArrayList<PageViewerFragment>)fragment.clone();
        Bundle args = new Bundle();
        args.putParcelableArrayList(FRAGMENTS_KEY,fragments);
        args.putInt(VALUE_KEY, lastPosition);
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
         //super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fragments = (ArrayList<PageViewerFragment>) getArguments().getParcelableArrayList(FRAGMENTS_KEY).clone();
            viewpager.getAdapter().notifyDataSetChanged();
            lastPosition = getArguments().getInt(VALUE_KEY);
            fragments.get(lastPosition).loadPage(fragments.get(lastPosition).getURL());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View l = inflater.inflate(R.layout.fragment_pager, container, false);
        viewpager = l.findViewById(R.id.viewpager);
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        viewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                adapter.pageChanged(position);
                lastPosition = position;
            }
        });
        viewpager.setOffscreenPageLimit(1);
        viewpager.setAdapter(adapter);
        return l;
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private final FragmentManager mFragmentManager;
        //private final FragmentActivity mContext;
        public ViewPagerAdapter(FragmentManager fm) {

            super(fm);
            this.mFragmentManager = fm;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }


        @Override
        public int getCount() {
            return fragments.size();
        }

        public void pageChanged(int position) {
            fragments.get(position).loadPage(fragments.get(position).getURL());
            ((changeEditText) getActivity()).changeText(fragments.get(position).getURL());

        }
        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
            try {
                super.restoreState(state, loader);
            } catch (Exception e) {
                Log.e("TAG", "Error Restore State of Fragment : " + e.getMessage(), e);
            }
        }


        public void onPageSelected(int position) {
        }

    }
    interface changeEditText {
        public void changeText(String url);
    }
    public void refresh()
    {
        viewpager.getAdapter().notifyDataSetChanged();
    }
    public int updateViewer()
    {
        viewpager.getAdapter().notifyDataSetChanged();
        lastPosition = fragments.size();
        viewpager.setCurrentItem(lastPosition);
        return lastPosition;
    }
    public void updateViewer(int position)
    {
        lastPosition = position;
        viewpager.setCurrentItem(lastPosition);
    }
    public String loadPage(String url)
    {
        currPageViewer = (PageViewerFragment) fragments.get(lastPosition);
        return fragments.get(lastPosition).loadPage(url);
    }

    public void goBack()
    {
        fragments.get(lastPosition).goBack();
    }
    public void goForward()
    {
        fragments.get(lastPosition).goForward();
    }

    public int fragmentChange(ArrayList<PageViewerFragment> realFragments)
    {
        fragments = (ArrayList<PageViewerFragment>)realFragments.clone();
        return updateViewer();
    }
    public int getLastPosition()
    {
        return lastPosition;
    }

    public String getCurUrl() { return fragments.get(getLastPosition()).getURL(); }

    public PageViewerFragment getCurViewer() { return (PageViewerFragment) currPageViewer; }
    public void setCurUrl(String url, int position)
    {
        fragments.get(position).setURL(url);
    }

}