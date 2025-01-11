package com.example.huhh;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizInfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DBaddHandler dbaddHandler;
    FloatingActionButton fab;
    String sectionItem,subjectItem;
    AlertDialog dialog;
    ImageView imageView;

    ArrayAdapter<String> studentAdapter;

    Button cancelBtn,addBtn;
    RecyclerView recyclerView;
    QuizListAdapter classAdapter;
    FragmentManager manager;

    ArrayList<QuizViewingItem> quizViewingItems = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    Spinner spinnerStudets,spinnerSubjects,spinnerSection;
    View views,viewer;
    StudentAdapter adapter;
    String name,section;
    int position;

    public QuizInfoFragment(String name, String section, int position) {
        // Required empty public constructor
        this.name = name;
        this.section = section;
        this.position = position;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizInfoFragment newInstance(String param1, String param2, String name, String section, int position) {
        QuizInfoFragment fragment = new QuizInfoFragment(name,section,position);
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
        View view = inflater.inflate(R.layout.fragment_list_student, container, false);
        viewer=view;
        recyclerView = view.findViewById(R.id.studentRecycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        classAdapter = new QuizListAdapter(view.getContext(),quizViewingItems);
        recyclerView.setAdapter(classAdapter);
        //imageView.setVisibility(View.VISIBLE);
        setStudentDetails(view,name,section);
        //classAdapter.setOnItemClickListener(position -> changeStatus(position));
        //fab.show();
        //imageView.setOnClickListener(v -> showBottomDialog(imageView));
         return view;
    }
    private void setStudentDetails(View view,String name, String section){
        ArrayList<QuizViewingItem> students;
        dbaddHandler = new DBaddHandler(view.getContext());
        students=dbaddHandler.quizDetails(section,name);
        System.out.println(section+name);
        int x=0;
        for (QuizViewingItem stud:students){
            quizViewingItems.add(new QuizViewingItem(
                    stud.getName(),
                    stud.getSection(),
                    stud.getScoreObtained(),
                    stud.getHps(),
                    stud.getDate()));
            System.out.println("DATA: "+stud.getName());
            System.out.println("DATA: "+stud.getSection());
            classAdapter.notifyItemChanged(x);
            x++;
        }

    }
}