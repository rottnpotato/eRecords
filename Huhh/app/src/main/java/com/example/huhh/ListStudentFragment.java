package com.example.huhh;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListStudentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListStudentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    DBaddHandler dbaddHandler;
    FloatingActionButton fab;
    String sectionItem,subjectItem;
    AlertDialog dialog;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayAdapter<String> studentAdapter;

    Button cancelBtn,addBtn;
    RecyclerView recyclerView;
    ClassAdapter classAdapter;
    FragmentManager manager;
    ArrayList<ClassItem> classItems = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    Spinner spinnerStudets,spinnerSubjects,spinnerSection;
    View views,viewer;
    ImageView imageView;

    public ListStudentFragment(String subjectItem,String sectionItem, FragmentManager manager, FloatingActionButton fab, AlertDialog dialog, View views, ImageView imageView) {
        this.sectionItem = sectionItem;
        this.subjectItem = subjectItem;
        this.manager = manager;
        this.fab = fab;
        this.dialog = dialog;
        this.views = views;
        this.imageView = imageView;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListStudentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListStudentFragment newInstance(String param1, String param2, String sectionItem, String subjectItem, FragmentManager manager,FloatingActionButton fab, AlertDialog dialog, View views, ImageView imageView) {
        ListStudentFragment fragment = new ListStudentFragment(subjectItem,sectionItem,manager,fab,dialog, views, imageView);
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

        View view =inflater.inflate(R.layout.fragment_list_student, container, false);
        viewer=view;
        recyclerView = view.findViewById(R.id.studentRecycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        classAdapter = new ClassAdapter(view.getContext(),classItems);
        recyclerView.setAdapter(classAdapter);
        setStudentDetails(view);
        //LinearLayout layout = views.findViewById(R.id.dialogo);
        classAdapter.setOnItemClickListener(position -> gotoItemActivity(position));
        imageView.setVisibility(View.VISIBLE);
        fab.show();
        return view;
    }

    private void gotoItemActivity(int position) {
        /*Intent intent = new Intent(viewer.getContext(),StudentActivity.class);
        intent.putExtra("name",classItems.get(position).getName());
        intent.putExtra("section",classItems.get(position).getSection());
        intent.putExtra("position",position);
        startActivity(intent);*/
        String name = classItems.get(position).getName();
        String section = classItems.get(position).getSection();
        int positioner = position;

        FragmentManager fragmentManager = manager;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, new StudentInfoFragment(name,section,positioner));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    private void setStudentDetails(View view){
        ArrayList<ClassItem> students;
        dbaddHandler = new DBaddHandler(view.getContext());
        students=dbaddHandler.studentDetails(sectionItem,subjectItem);
        System.out.println(sectionItem+subjectItem);
        for (ClassItem stud:students){
            classItems.add(new ClassItem(stud.getName(), stud.getSection()));
            System.out.println("DATA: "+stud.getName());
            System.out.println("DATA: "+stud.getSection());
            classAdapter.notifyDataSetChanged();
        }


    }
}