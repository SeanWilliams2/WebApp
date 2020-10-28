package edu.temple.webapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PageControlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PageControlFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String KEY_EDITTEXT = "exittext";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText editText;
    String savedEdittext;

    public PageControlFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static PageControlFragment newInstance(String editText) {
        PageControlFragment fragment = new PageControlFragment();
        Bundle args = new Bundle();
        args.putString(KEY_EDITTEXT,editText);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle;
        if ((bundle = getArguments()) != null) {
            savedEdittext = bundle.getString(KEY_EDITTEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View l = inflater.inflate(R.layout.fragment_page_control, container, false);
        editText = (EditText) l.findViewById(R.id.editText);
        ImageButton goButton = (ImageButton) l.findViewById(R.id.goButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                ((ButtonClickInterface) getActivity()).goButtonClick(editText.getText().toString());
            }
        });

        ImageButton backButton = (ImageButton) l.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                ((ButtonClickInterface) getActivity()).backButtonClick(editText.getText().toString());
            }
        });

        ImageButton forwardButton = (ImageButton) l.findViewById(R.id.forwardButton);
        forwardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                ((ButtonClickInterface) getActivity()).forwardButtonClick(editText.getText().toString());
            }
        });
        if(savedEdittext != null){
            editText.setText(savedEdittext);
        }
        return l;
    }

    public void updateEditText(String str)
    {
        editText.setText(str);
    }

    interface ButtonClickInterface {
        void goButtonClick(String url);
        void backButtonClick(String url);
        void forwardButtonClick(String url);

    }
}