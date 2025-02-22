package edu.temple.webapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class PageListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView listView;
    private int oldPos;
    private ArrayList<PageViewerFragment> fragments = new ArrayList<PageViewerFragment>();
    private static ArrayList<String> websites = new ArrayList<String>();
    public PageListFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static PageListFragment newInstance(ArrayList<PageViewerFragment> arr, ArrayList<String> sites) {
        PageListFragment fragment = new PageListFragment();
        Bundle args = new Bundle();
        websites = (ArrayList<String>) sites.clone();
        args.putParcelableArrayList(ARG_PARAM1, arr);
        args.putStringArrayList(ARG_PARAM2, websites);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setRetainInstance(true);
        Bundle bundle;
        if ((bundle = getArguments()) != null) {
            fragments = (ArrayList<PageViewerFragment>) bundle.getParcelableArrayList(ARG_PARAM1).clone();
            websites = (ArrayList<String>) bundle.getStringArrayList(ARG_PARAM2).clone();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View l=  inflater.inflate(R.layout.fragment_page_list, container, false);
        listView = (ListView) l.findViewById(R.id.listview);
        websites.add(new String());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, websites);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((changePager) getActivity()).changePager(position);
            }
        });
        return l;
    }

    public void newItem(ArrayList<PageViewerFragment> realFragments, int position)
    {
        websites.add(new String());
        fragments = (ArrayList<PageViewerFragment>)realFragments.clone();
        updateList();
        websites.set(position,fragments.get(position).getTitle());
    }
    public void fragmentChange(ArrayList<PageViewerFragment> realFragments, int position)
    {
        fragments = (ArrayList<PageViewerFragment>)realFragments.clone();
        while(websites.size() < fragments.size())
        {
            websites.add(new String());
        }
        websites.set(position, fragments.get(position).getTitle());
        updateList();
    }
    public void updateList()
    {
        ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
        adapter.notifyDataSetChanged();
    }
    public void setWebsites(int size)
    {
        while(websites.size() < size)
        {
            websites.add(new String());
        }
    }
    public void updateWebsites(ArrayList<String> sites)
    {
        websites = (ArrayList<String>) sites.clone();
    }
    interface changePager {
        public void changePager(int position);
    }
}