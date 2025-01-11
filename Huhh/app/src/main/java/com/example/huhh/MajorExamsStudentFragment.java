package com.example.huhh;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
 * Use the {@link MajorExamsStudentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MajorExamsStudentFragment extends Fragment {

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
    QuizzAdapter classAdapter;
    FragmentManager manager;

    ArrayList<QuizzItem> quizzItems = new ArrayList<>();
    ArrayList<QuizAddItem> quizAddItems = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    EditText obtainedScore,hpScore;
    Spinner spinnerStudets,spinnerSubjects,spinnerSection;
    View views,viewer;
    StudentAdapter adapter;

    public MajorExamsStudentFragment(String subjectItem, String sectionItem, FragmentManager manager, FloatingActionButton fab, AlertDialog dialog, View views, ImageView imageView) {
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
    public static MajorExamsStudentFragment newInstance(String param1, String param2, String sectionItem, String subjectItem, FragmentManager manager, FloatingActionButton fab, AlertDialog dialog, View views, ImageView imageView) {
        MajorExamsStudentFragment fragment = new MajorExamsStudentFragment(subjectItem,sectionItem,manager,fab,dialog, views, imageView);
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
        classAdapter = new QuizzAdapter(view.getContext(),quizzItems);
        recyclerView.setAdapter(classAdapter);
        imageView.setVisibility(View.VISIBLE);
        setStudentDetails(view);
        classAdapter.setOnItemClickListener(position -> {
            System.out.println("Selected Position: "+position);
            changeStatus(position);
            showBottomDialog(view,position);
        });
        imageView.setOnClickListener(v -> showConfirmDialog(imageView));
        return view;
    }

    private void showConfirmDialog(ImageView imageView) {
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
        addBtn.setOnClickListener(v -> addToQuizTable(quizAddItems,view,dialog));
    }
    private void showBottomDialog(View imageView,int position) {
        // ArrayList<StudentDataList> data;
        //System.out.println(+quizzItems.get(position).getName());
        String data = "Name: "+quizzItems.get(position).getName()+"\nSection: "+quizzItems.get(position).getSection()+"\nSubject: "+subjectItem;
        //Toast.makeText(imageView.getContext(),data,Toast.LENGTH_LONG).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(imageView.getContext());
        View view = LayoutInflater.from(imageView.getContext()).inflate(R.layout.class_dialog,null);
        builder.setView(view);
        //views = view;
        dialog= builder.create();
        dialog.show();

        Button cancelBtn = view.findViewById(R.id.cancel);
        Button addBtn = view.findViewById(R.id.add);
        cancelBtn.setOnClickListener(v -> dialog.dismiss());
       // addBtn.setOnClickListener(v -> addToAttendanceTable(quizzItems,view,dialog));
        EditText obtained = view.findViewById(R.id.obtain);
        EditText hPSCORE = view.findViewById(R.id.hps);
        addBtn.setOnClickListener(v -> {
            int iskor,highesTT;
            if(obtained.getText().toString()==null|| obtained.getText().toString().equals("")){
                iskor =0;
            }else{
                iskor = Integer.parseInt(obtained.getText().toString());
            }
            if(hPSCORE.getText().toString()==null|| hPSCORE.getText().toString().equals("")){
                highesTT =0;
            }else{
                highesTT = Integer.parseInt(hPSCORE.getText().toString());
            }

            addQuizScores(iskor,highesTT,quizzItems.get(position).getSection(),subjectItem,dateTime(),quizzItems.get(position).getName());

            dialog.dismiss();
        });

    }
    private void addQuizScores(int score, int hps,String section,String subjectCode,String date,String name){
        quizAddItems.add(new QuizAddItem(1,score,hps,section,subjectCode,date,name));
    }
    private String dateTime(){
         LocalDateTime localDateTime;
        localDateTime= LocalDateTime.now();
        return localDateTime.toString();
        //Toast.makeText(view.getContext(), "Date Now: "+localDateTime,Toast.LENGTH_LONG).show();
    }
    private void addToQuizTable(ArrayList<QuizAddItem> quizAddItems,View view,Dialog dialog){

        dbaddHandler = new DBaddHandler(view.getContext());
            int response = dbaddHandler.insertMajorExam(quizAddItems);
            if (response == 1)
                Toast.makeText(view.getContext(), "Added Major Exam Successfully!", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(view.getContext(), "Adding Major Exam Failed!", Toast.LENGTH_LONG).show();
            dialog.dismiss();
    }


    private void changeStatus(int position) {
        final QuizzItem quizzItem = quizzItems.get(position);

        String status = quizzItem.getStatus();
        if(status.equals("P")) status = "A";
        else if(status.equals("A")) status = "E";
        else if(status.equals("E")) status = "";
        else status="P";
        quizzItems.get(position).setStatus(status);
        classAdapter.notifyItemChanged(position);
       // System.out.println("VALUE"+studentItems.get(position).getStatus());
    }

    private void setStudentDetails(View view){
        ArrayList<QuizzItem> students;
        dbaddHandler = new DBaddHandler(view.getContext());
        students=dbaddHandler.quizzDetailsStudents(sectionItem,subjectItem);
        System.out.println(sectionItem+subjectItem);
        int x=0;
        for (QuizzItem stud:students){
            quizzItems.add(new QuizzItem(stud.getName(), stud.getSection(),""));
            System.out.println("DATA: "+stud.getName());
            System.out.println("DATA: "+stud.getSection());
            classAdapter.notifyItemChanged(x);
            x++;
        }


    }
}