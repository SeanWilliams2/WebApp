package edu.temple.webapp;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BrowserControlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BrowserControlFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageButton saveBookmarkButton;
    ImageButton bookmarkPageButton;
    ImageButton tabButton;

    public BrowserControlFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BrowserControlFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BrowserControlFragment newInstance(String param1, String param2) {
        BrowserControlFragment fragment = new BrowserControlFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        View l =  inflater.inflate(R.layout.fragment_browser_control, container, false);
        tabButton = (ImageButton) l.findViewById(R.id.tabButton);
        tabButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                ((tabButtonClickInterface) getActivity()).tabButtonClick();
            }
        });
        bookmarkPageButton = (ImageButton) l.findViewById(R.id.bookmarkPage);
        bookmarkPageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                ((tabButtonClickInterface) getActivity()).openActivity();
            }
        });
        saveBookmarkButton = (ImageButton) l.findViewById(R.id.saveBookmark);
        saveBookmarkButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                ((tabButtonClickInterface) getActivity()).saveBookmark();
            }
        });

        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            setButtonsInvis();
        }
        else {
              setButtonsVis();
        }
        return l;
    }

    interface tabButtonClickInterface {
        void tabButtonClick();
        void openActivity();
        void saveBookmark();
    }

    public void setButtonsInvis()
    {
        saveBookmarkButton.setVisibility(View.INVISIBLE);
        bookmarkPageButton.setVisibility(View.INVISIBLE);
    }
    public void setButtonsVis()
    {
        saveBookmarkButton.setVisibility(View.VISIBLE);
        bookmarkPageButton.setVisibility(View.VISIBLE);
    }
}