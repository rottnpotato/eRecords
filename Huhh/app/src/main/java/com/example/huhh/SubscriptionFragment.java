package com.example.huhh;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubscriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubscriptionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayAdapter<String> studentAdapter;
    private DBaddHandler dbaddHandler;
    Button button;
    RecyclerView recyclerView;
    AlertDialog dialog;
    ClassAdapter classAdapter;
    FragmentManager manager;
    FloatingActionButton fab;

    RecyclerView.LayoutManager layoutManager;
    Spinner spinnerAttendance,spinnerSubjects,spinnerSection;
    View views;
    String selected;
    ImageView imageView;

    public SubscriptionFragment(FragmentManager manager, FloatingActionButton fab, AlertDialog dialog, View  views, ImageView imageView) {
        // Required empty public constructor
        this.manager = manager;
        this.fab = fab;
        this.views = views;
        this.dialog = dialog;
        this.selected=selected;
        this.imageView = imageView;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubscriptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubscriptionFragment newInstance(String param1, String param2,FragmentManager manager,FloatingActionButton fab, AlertDialog dialog, View views, ImageView imageView) {
        SubscriptionFragment fragment = new SubscriptionFragment(manager,fab,dialog,views, imageView);
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
        View view = inflater.inflate(R.layout.fragment_subscription, container, false);
        dbaddHandler = new DBaddHandler(view.getContext());
        spinnerAttendance = view.findViewById(R.id.purpose1);
        spinnerSection = view.findViewById(R.id.section1);
        spinnerSubjects = view.findViewById(R.id.subject1);
        button = view.findViewById(R.id.lookS1);


        final List<String> section = dbaddHandler.distinctSections();

        studentAdapter= new ArrayAdapter<>(spinnerSection.getContext(), android.R.layout.simple_spinner_item, section);
        studentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSection.setAdapter(studentAdapter);
        final String[] sectionItem = new String[1];
        final String[] sectionItem1 = new String[1];
        spinnerSection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sectionItem[0] =spinnerSection.getSelectedItem().toString();
                final List<String> subjects = dbaddHandler.distinctSubjects(sectionItem[0]);
                studentAdapter= new ArrayAdapter<>(spinnerSubjects.getContext(), android.R.layout.simple_spinner_item, subjects);
                studentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSubjects.setAdapter(studentAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerAttendance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sectionItem1[0] =spinnerAttendance.getSelectedItem().toString();
                System.out.println(sectionItem1[0]);
                selected=sectionItem1[0];

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        button.setOnClickListener(v -> replaceSubscriptionFragment(view,fab,dialog,views,selected, imageView));

        return view;
    }

    private void replaceSubscriptionFragment(View view, FloatingActionButton fab, AlertDialog dialog, View views, String selected, ImageView imageView) {

        Spinner sectionSpinner = view.findViewById(R.id.section1);
        String sectionItems =sectionSpinner.getSelectedItem().toString();
        Spinner subjectSpinner = view.findViewById(R.id.subject1);
        String subjectItem =subjectSpinner.getSelectedItem().toString();
        System.out.println("sectionSpinner: "+sectionSpinner);
        System.out.println("subjectSpinner: "+subjectSpinner);
        switch (selected) {
            case "Attendance": {
                FragmentManager fragmentManager = manager;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new ViewAttendanceStudentFragment(subjectItem, sectionItems, manager, fab, dialog, views, imageView));
                fragmentTransaction.addToBackStack(null);
                imageView.setVisibility(View.INVISIBLE);
                fragmentTransaction.commit();
                break;
            }
            case "Student List": {
                FragmentManager fragmentManager = manager;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new ListStudentFragment(subjectItem, sectionItems, manager, fab, dialog, views, imageView));
                fragmentTransaction.addToBackStack(null);
                imageView.setVisibility(View.INVISIBLE);
                fragmentTransaction.commit();
                break;
            }
            case "Quizzes": {
                FragmentManager fragmentManager = manager;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new ListQuizesFragment(subjectItem, sectionItems, manager, fab, dialog, views, imageView));
                fragmentTransaction.addToBackStack(null);
                imageView.setVisibility(View.INVISIBLE);
                fragmentTransaction.commit();
                break;
            }
            case "Projects/Assignments": {
                FragmentManager fragmentManager = manager;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new ListProjectAssignmentFragment(subjectItem, sectionItems, manager, fab, dialog, views, imageView));
                fragmentTransaction.addToBackStack(null);
                imageView.setVisibility(View.INVISIBLE);
                fragmentTransaction.commit();
                break;
            }
            case "Major Exams": {
                FragmentManager fragmentManager = manager;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new ListMajorExamFragment(subjectItem, sectionItems, manager, fab, dialog, views, imageView));
                fragmentTransaction.addToBackStack(null);
                imageView.setVisibility(View.INVISIBLE);
                fragmentTransaction.commit();
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + selected);

        }
    }
}