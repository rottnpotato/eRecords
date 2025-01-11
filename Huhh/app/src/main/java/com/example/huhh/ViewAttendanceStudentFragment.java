package com.example.huhh;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewAttendanceStudentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewAttendanceStudentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    DBaddHandler dbaddHandler;
    FloatingActionButton fab;
    String sectionItem,subjectItem;
    AlertDialog dialog;
    ImageView imageView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayAdapter<String> studentAdapter;

    Button cancelBtn,addBtn;
    RecyclerView recyclerView;
    ViewAttendanceAdapter viewAttendanceAdapter;
    FragmentManager manager;

    ArrayList<ViewItem> viewItems = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    Spinner spinnerStudets,spinnerSubjects,spinnerSection;
    View views,viewer;
    StudentAdapter adapter;

    public ViewAttendanceStudentFragment(String subjectItem, String sectionItem, FragmentManager manager, FloatingActionButton fab, AlertDialog dialog, View views, ImageView imageView) {
        this.sectionItem = sectionItem;
        this.subjectItem = subjectItem;
        this.manager = manager;
        this.fab = fab;
        this.dialog = dialog;
        this.views = views;
        this.imageView= imageView;
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
    public static ViewAttendanceStudentFragment newInstance(String param1, String param2, String sectionItem, String subjectItem, FragmentManager manager, FloatingActionButton fab, AlertDialog dialog, View views, ImageView imageView) {
        ViewAttendanceStudentFragment fragment = new ViewAttendanceStudentFragment(subjectItem,sectionItem,manager,fab,dialog, views, imageView);
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
        viewAttendanceAdapter = new ViewAttendanceAdapter(view.getContext(),viewItems);
        recyclerView.setAdapter(viewAttendanceAdapter);
        imageView.setVisibility(View.INVISIBLE);
        setStudentDetails(view);
        //classAdapter.setOnItemClickListener(position -> changeStatus(position));
        //fab.show();
        //imageView.setOnClickListener(v -> showBottomDialog(imageView));
        return view;
    }
    private void showBottomDialog(ImageView imageView) {
        // ArrayList<StudentDataList> data;
        AlertDialog.Builder builder = new AlertDialog.Builder(imageView.getContext());
        View view = LayoutInflater.from(imageView.getContext()).inflate(R.layout.add_dialog,null);
        builder.setView(view);
        //views = view;
        dialog= builder.create();
        dialog.show();
        Button cancelBtn = view.findViewById(R.id.cancelAdd);
        Button addBtn = view.findViewById(R.id.addProceed);
        cancelBtn.setOnClickListener(v -> dialog.dismiss());
        //addBtn.setOnClickListener(v -> addToAttendanceTable(viewItems,view,dialog));
    }
    private String dateTime(){
         LocalDateTime localDateTime;
        localDateTime= LocalDateTime.now();
        return localDateTime.toString();
        //Toast.makeText(view.getContext(), "Date Now: "+localDateTime,Toast.LENGTH_LONG).show();
    }
    private void addToAttendanceTable(ArrayList<StudentItem> studentItems,View view,Dialog dialog){
        dbaddHandler = new DBaddHandler(view.getContext());
        int response=dbaddHandler.insertAttendance(studentItems,dateTime(),subjectItem);
        if (response == 1) Toast.makeText(view.getContext(),"Added Attendance Successfully!",Toast.LENGTH_LONG).show();
        else Toast.makeText(view.getContext(),"Adding Attendance Failed!",Toast.LENGTH_LONG).show();
        dialog.dismiss();
    }


    private void changeStatus(int position) {
        final ViewItem viewItem = viewItems.get(position);
        /*Intent intent = new Intent(viewer.getContext(),StudentActivity.class);
        intent.putExtra("name",classItems.get(position).getName());
        intent.putExtra("section",classItems.get(position).getSection());
        intent.putExtra("position",position);
        startActivity(intent);
        String name = classItems.get(position).getName();
        String section = classItems.get(position).getSection();
        int positioner = position;
        FragmentManager fragmentManager = manager;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, new StudentInfoFragment(name,section,positioner));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();*/
        String status = viewItem.getAttendance();
        if(status.equals("P")) status = "A";
        else if(status.equals("A")) status = "E";
        else if(status.equals("E")) status = "";
        else status="P";
        viewItems.get(position).setAttendance(status);
        viewAttendanceAdapter.notifyItemChanged(position);
       // System.out.println("VALUE"+studentItems.get(position).getStatus());
    }

    private void setStudentDetails(View view){
        ArrayList<ViewItem> viewItems1;
        dbaddHandler = new DBaddHandler(view.getContext());
        viewItems1=dbaddHandler.studentViewAttendance(sectionItem,subjectItem);
        System.out.println(sectionItem+subjectItem);
        int x=0;
        for (ViewItem stud:viewItems1){
            viewItems.add(new ViewItem(stud.getName(), stud.getSection(),stud.getAttendance(),stud.getDate()));
            System.out.println("DATA: "+stud.getName());
            System.out.println("DATA: "+stud.getSection());
            viewAttendanceAdapter.notifyItemChanged(x);
            x++;
        }
    }
}