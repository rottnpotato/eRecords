package com.example.huhh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huhh.api.ApiClasses;
import com.example.huhh.api.ApiQuery;
import com.example.huhh.api.ApiQueryy;
import com.example.huhh.requestResponse.UserDetails;
import com.example.huhh.requestResponse.userData;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FloatingActionButton fab;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    HorizontalScrollView horizontalScrollView;
    EditText username, password;
    Fragment fragment;
    Button button;
    RecyclerView recyclerView;
    ClassAdapter classAdapter;
    ArrayList<ClassItem> classItems = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    ArrayList<StudentDataList> studentDataLists = new ArrayList<>();
    ArrayAdapter<String> studentAdapter;

    TextView dispName,dispEmail,one,two,three;
    AlertDialog dialog;
    View views;
    ImageView imageView;
    //List<UserDetails> userDetails;
    private DBaddHandler dbaddHandler;

    Menu menu;
    MenuItem menuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header);
        if(pref.getBoolean("first_open",true)){
            queryAndSave(savedInstanceState);

        }else{
            showDef(savedInstanceState);
        }
    }
    private void queryAndSave(Bundle savedInstanceState){
        Thread thread1 = new Thread(new Runnable(){
            @Override
            public void run(){
        SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        Intent intent = getIntent();
                final String[] token = {"Bearer " + intent.getStringExtra("token")};
        String rft = intent.getStringExtra("rft");
        //Log.d("token",token);

                Call<UserDetails> userDetailsCall = ApiQuery.getQueryUserService().userDetails(token[0]);
                userDetailsCall.enqueue(new Callback<UserDetails>() {
                    @Override
                    public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                        UserDetails userDetails = response.body();

                                Call<List<ClassDetails>> classDetailsCall = ApiClasses.getClassService().classesDetails(token[0]);
                                classDetailsCall.enqueue(new Callback<List<ClassDetails>>() {
                                    @Override
                                    public void onResponse(Call<List<ClassDetails>> call, Response<List<ClassDetails>> response) {
                                        List<ClassDetails> classDetails = response.body();
                                        //System.out.println("ROTTEN POTATO");
                                        //System.out.println(classDetails.toString());
                                        dbaddHandler = new DBaddHandler(DashActivity.this);
                                        for (ClassDetails details: classDetails) {
                                            //System.out.println(details.getSubjectCodes());
                                            if(dbaddHandler.addClass(details.getSubjectCodes())){
                                                Log.d("MESSAGE", "ADD "+details.getSubjectCodes()+" SUCCESS");
                                            }else{
                                                Toast.makeText(DashActivity.this, "Database Adding Failure", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        System.out.println(classDetails);
                                        SharedPreferences.Editor edt = pref.edit();
                                        edt.putBoolean("first_open",false);
                                        edt.apply();
                                        String[] name = userDetails.getMe().getFull_name().split(", ");
                                        String fname = name[1].toUpperCase().charAt(0) + name[1].substring(1).toLowerCase();
                                        System.out.println(fname);
                                        Thread thread2 = new Thread(new Runnable(){
                                            @Override
                                            public void run(){
                                                for (ClassDetails classDetailsz : classDetails) {
                                                    String url = "https://api.bisu.edu.ph/api/faculty/grades/" + String.valueOf(classDetailsz.getId());

                                                        Call<StudentDetails> studentDetailsCall = ApiQueryy.getQueryStudentService().studentDetails(url, token[0]);

                                                    studentDetailsCall.enqueue(new Callback<StudentDetails>() {
                                                        @Override
                                                        public void onResponse(Call<StudentDetails> call, Response<StudentDetails> response) {
                                                            StudentDetails details = response.body();
                                                            //Log.d("DATA", String.valueOf(details));
                                                            //Log.d("DATA", details.());
                                                            //details.getStudents();
                                                            Thread thread3 = new Thread(new Runnable(){
                                                                @Override
                                                                public void run(){
                                                                    assert details != null;
                                                                    for (Student data : details.getStudents()) {
                                                                        //System.out.println(data.getStudentName().toString());
                                                                        // creating a new dbaddhandler class
                                                                        // and passing our context to it.
                                                                        dbaddHandler = new DBaddHandler(DashActivity.this);
                                                                        // on below line we are calling a method to add new
                                                                        // course to sqlite data and pass all our values to it.
                                                                        if (dbaddHandler.addNewStudent(data.getStudentName(), details.getHeader().getSubjects().get(0).getCode(), details.getHeader().getSection(), data.getStudentId())) {

                                                                            //Toast.makeText(DashActivity.this,String.valueOf(x) , Toast.LENGTH_SHORT).show();
                                                                            Log.d("Database Status", "Adding " + data.getStudentName() + " Success");


                                                                        } else {
                                                                            Toast.makeText(DashActivity.this, "Database Failure", Toast.LENGTH_SHORT).show();
                                                                            break;
                                                                        }
                                                                    }
                                                                }
                                                            });thread3.start();
                                                            Toast.makeText(DashActivity.this, "Successfully added to Database!", Toast.LENGTH_SHORT).show();
                                                            //System.out.println("POTATO");
                                                        }

                                                        @Override
                                                        public void onFailure(Call<StudentDetails> call, Throwable throwable) {
                                                            Log.d("E", "E", throwable);
                                                        }
                                                    });
                                                }
                                            }
                                        });thread2.start();


                                        dbaddHandler.addUser(userDetails.getMe().getFull_name(),fname,userDetails.getSession().getSy());

                                        showDef(savedInstanceState);
                                    }

                                    @Override
                                    public void onFailure(Call<List<ClassDetails>>call, Throwable throwable) {
                                        Log.d("E","E",throwable);
                                    }
                                });

                    }

                    @Override
                    public void onFailure(Call<UserDetails> call, Throwable throwable) {
                        Log.d("E","E",throwable);
                                }
                            });
                         }
                    });thread1.start();
            }




    @Override
    public void onBackPressed(){
        FragmentManager fm = this.getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            imageView.setVisibility(View.INVISIBLE);
            fm.popBackStack();

        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }
    private  void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    private void replaceShortsFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.replace(R.id.frame_layout, new ListStudentFragment());
        FragmentManager fragsManager = getSupportFragmentManager();
        fragment =fragsManager.findFragmentById(R.id.studentRecycleView);
        FragmentManager fragManager = getSupportFragmentManager();
        Fragment fragments =fragManager.findFragmentById(R.id.home);
        //button = fragments.getView().findViewById(R.id.lookS);
        Spinner sectionSpinner = (Spinner) findViewById(R.id.section);
        String sectionItem =sectionSpinner.getSelectedItem().toString();
        Spinner subjectSpinner = (Spinner) findViewById(R.id.subject);
        String subjectItem =subjectSpinner.getSelectedItem().toString();
        setStudentDetails(sectionItem,subjectItem);

        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);

        classAdapter = new ClassAdapter(this,classItems);
        recyclerView.setAdapter(classAdapter);
        fragmentTransaction.commit();
    }

    private void setStudentDetails(String sectionItem, String subjectItem){
        dbaddHandler = new DBaddHandler(DashActivity.this);

    }



    private void showDef(Bundle savedInstanceState){
        dbaddHandler = new DBaddHandler(DashActivity.this);
        setContentView(R.layout.activity_dash);
        Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);

        imageView = toolbar.findViewById(R.id.save);
        imageView.setVisibility(View.INVISIBLE);
        ArrayList<userData> userDataArrayList = dbaddHandler.readUserData();
        //studentDataLists= dbaddHandler.readStudentData();
       ArrayList<StudentDataList> data= dbaddHandler.readStudentData();
        String name= userDataArrayList.get(0).getName();
        String sy = userDataArrayList.get(0).getSy();
        String fname = userDataArrayList.get(0).getFullname();
        replaceFragment(new HomeFragment(fname));

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fab);
        drawerLayout = findViewById(R.id.drawer_layout);

        fab.hide();
        View headerView = navigationView.getHeaderView(0);
        dispName = headerView.findViewById(R.id.navName);
        dispEmail = headerView.findViewById(R.id.navEmail);
        //
        dispName.setText(name);
        dispEmail.setText(sy);


        //menuItem.

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState == null){
           getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment(fname)).commit();
           // navigationView.setCheckedItem(R.id.nav_home);


        }
        navigationView.bringToFront();
        menu = navigationView.getMenu();
            navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);


       /*  menu= navigationView.getMenu();
        menuItem=menu.findItem(R.id.nav_logout);
       boolean b= this.onMenuItemSelected(R.id.nav_logout,menuItem);
       System.out.println("LOGOUT SELECTED: "+b);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem Item) {
                SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
                SharedPreferences.Editor edt = pref.edit();
                edt.clear();
                edt.apply();
                System.out.println("HEHEHHEHEHE");
                Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run(){
                        startActivity(new Intent(DashActivity.this, MainActivity.class));
                        finish();
                    }
                });thread.start();
                return true;
            }
        });*/


        replaceFragment(new HomeFragment(fname));
        FragmentManager manager = getSupportFragmentManager();
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home: {
                    fab.hide();
                    imageView.setVisibility(View.INVISIBLE);
                    replaceFragment(new HomeFragment(fname));
                    break;
                }
                case R.id.shorts: {
                    fab.hide();
                    imageView.setVisibility(View.INVISIBLE);
                    replaceFragment(new ShortsFragment(manager,fab,dialog,views,imageView));
                    break;
                }
                case R.id.subscriptions: {
                    fab.hide();
                    imageView.setVisibility(View.INVISIBLE);
                    replaceFragment(new SubscriptionFragment(manager,fab,dialog,views,imageView));
                    break;
                }
                case R.id.library: {
                    fab.hide();
                    imageView.setVisibility(View.INVISIBLE);
                    replaceFragment(new LibraryFragment(manager,fab,dialog,views,imageView));
                    break;
                }
            }

            return true;
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog(data);

            }
        });
    }
    private void showBottomDialogg() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        LinearLayout videoLayout = dialog.findViewById(R.id.layoutVideo);
        LinearLayout shortsLayout = dialog.findViewById(R.id.layoutShorts);
        LinearLayout liveLayout = dialog.findViewById(R.id.layoutLive);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);

        videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(DashActivity.this,"Upload a Video is clicked",Toast.LENGTH_SHORT).show();

            }
        });

        shortsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(DashActivity.this,"Create a short is Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        liveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(DashActivity.this,"Go live is Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);


    }
/*

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        LinearLayout videoLayout = dialog.findViewById(R.id.layoutVideo);
        LinearLayout shortsLayout = dialog.findViewById(R.id.layoutShorts);
        LinearLayout liveLayout = dialog.findViewById(R.id.layoutLive);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);

        videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(DashActivity.this,"Upload a Video is clicked",Toast.LENGTH_SHORT).show();

            }
        });

        shortsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(DashActivity.this,"Create a short is Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        liveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(DashActivity.this,"Go live is Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

 */
    private void showBottomDialog(ArrayList<StudentDataList> data) {
       // ArrayList<StudentDataList> data;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.class_dialog,null);
        builder.setView(view);
        views = view;
        dialog= builder.create();
        dialog.show();
        Button cancelBtn = views.findViewById(R.id.cancel);
        Button addBtn = views.findViewById(R.id.add);
        cancelBtn.setOnClickListener(v -> dialog.dismiss());
        addBtn.setOnClickListener(v -> dateTime(view,dialog));
    }
    private void dateTime(View view,Dialog dialog){
        LocalDateTime localDateTime = LocalDateTime.now();
        Toast.makeText(view.getContext(), "Date Now: "+localDateTime,Toast.LENGTH_LONG).show();
        dialog.dismiss();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        System.out.println("HEHEHHEHEHE");
        dbaddHandler = new DBaddHandler(DashActivity.this);
            int id = item.getItemId();
            if(id == R.id.nav_logout){
                SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
                SharedPreferences.Editor edt = pref.edit();
                edt.clear();
                edt.apply();
                dbaddHandler.logoutDrop();
                System.out.println("HEHEHHEHEHE");
                Thread thread6 = new Thread(new Runnable(){
                    @Override
                    public void run(){
                        startActivity(new Intent(DashActivity.this, MainActivity.class));
                        finish();
                    }
                });thread6.start();
            }else{
                System.out.println("MALI ID");
            }
            return false;
        }
}