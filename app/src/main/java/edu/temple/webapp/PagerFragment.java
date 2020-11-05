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
    ArrayList<PageViewerFragment> fragments = new ArrayList<>();
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
    public static PagerFragment newInstance(String param1, String param2) {
        PagerFragment fragment = new PagerFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(fragments);
        fragment.setArguments(args);
        return fragment;
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
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        viewpager.setAdapter(adapter);
        fragments.add(new PageViewerFragment());
        viewpager.getAdapter().notifyDataSetChanged();

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

        }
        public void onPageSelected(int position) {
            if(lastPosition < position) {
                lastPosition++;
                updateViewer();
            } else {
                lastPosition--;
                goBack();
            }

        }

    }
    public void updateViewer()
    {
        fragments.add(new PageViewerFragment());
        viewpager.getAdapter().notifyDataSetChanged();
        lastPosition++;
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
    }
}