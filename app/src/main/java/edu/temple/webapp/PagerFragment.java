package edu.temple.webapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

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
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PagerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static void newInstance(ArrayList<PageViewerFragment> fragment) {
        fragments = new ArrayList<PageViewerFragment>();
        fragments = (ArrayList<PageViewerFragment>)fragment.clone();
        Bundle args = new Bundle();
        args.putParcelableArrayList("hello",fragments);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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
            }
        });
        viewpager.setAdapter(adapter);
        return l;
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
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
        public void onPageSelected(int position) {
        }

    }
    interface changeEditText {
        public void changeText(String url);
    }
    public void updateViewer()
    {
        viewpager.getAdapter().notifyDataSetChanged();
        lastPosition++;
        viewpager.setCurrentItem(lastPosition);

    }
    public String loadPage(String url)
    {
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

    public void fragmentChange(ArrayList<PageViewerFragment> realFragments)
    {
        fragments = (ArrayList<PageViewerFragment>)realFragments.clone();
        updateViewer();
    }


}