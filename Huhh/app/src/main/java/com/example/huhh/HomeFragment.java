package com.example.huhh;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DBaddHandler dbaddHandler;
    String name;
    public HomeFragment(String name) {
        // Required empty public constructor
        this.name = name;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2, String name) {
        HomeFragment fragment = new HomeFragment(name);
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
       View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView textView = (TextView) view.findViewById(R.id.user_text);
        textView.setText("Hello, Ms. "+name);
        //System.out.println(dbaddHandler.queryClass("students"));
        dbaddHandler = new DBaddHandler(view.getContext());
        String d = dbaddHandler.queryClass();
        TextView textView1 = (TextView) view.findViewById(R.id.enrolled_count);
        textView1.setText(d+" Enrolled Students");
        TextView textView2 = (TextView) view.findViewById(R.id.class_counter);
        String c = dbaddHandler.querySubjects();
        textView2.setText(c+" Sections Handled");
        String f = dbaddHandler.queryClasses();
        TextView textView3 = (TextView) view.findViewById(R.id.subject_count);
        textView3.setText(f+" Subjects Handled");
        return view;
    }
}