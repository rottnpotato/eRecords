package com.example.huhh;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LibraryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button button;
    RecyclerView recyclerView;
    AlertDialog dialog;
    ClassAdapter classAdapter;
    FragmentManager manager;
    FloatingActionButton fab;

    Spinner spinnerAttendance,spinnerSubjects,spinnerSection;
    View views;
    String selected;
    ImageView imageView;
    DBaddHandler dbaddHandler;
    ArrayAdapter<String> studentAdapter;

    public LibraryFragment(FragmentManager manager, FloatingActionButton fab, AlertDialog dialog,View views, ImageView imageView) {
        // Required empty public constructor
        this.manager = manager;
        this.fab = fab;
        this.dialog = dialog;
        this.views= views;
        this.imageView = imageView;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LibraryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LibraryFragment newInstance(String param1, String param2,FragmentManager manager, FloatingActionButton fab, AlertDialog dialog,View views, ImageView imageView) {
        LibraryFragment fragment = new LibraryFragment(manager,fab,dialog,views,imageView);
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

        View view =inflater.inflate(R.layout.fragment_library, container, false);
        dbaddHandler = new DBaddHandler(view.getContext());
        Spinner sectionSpinner = view.findViewById(R.id.sectionSelector);
        final List<String> section = dbaddHandler.distinctSections();
        studentAdapter= new ArrayAdapter<>(sectionSpinner.getContext(), android.R.layout.simple_spinner_item, section);
        studentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sectionSpinner.setAdapter(studentAdapter);

        Button buttonProceed = view.findViewById(R.id.proceed);

        buttonProceed.setOnClickListener(v -> {
            String sectionItem =sectionSpinner.getSelectedItem().toString();
            FragmentManager fragmentManager = manager;
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, new GradesFragment(sectionItem,sectionItem,manager, fab, dialog, views, imageView));
            fragmentTransaction.addToBackStack(null);
            imageView.setVisibility(View.INVISIBLE);
            fragmentTransaction.commit();
        });



        return view;

    }
}