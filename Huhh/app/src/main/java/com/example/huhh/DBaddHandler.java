package com.example.huhh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.huhh.requestResponse.QuizItem;
import com.example.huhh.requestResponse.userData;

import java.util.ArrayList;
import java.util.Locale;

public class DBaddHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "recordsdb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "students";
    private static final String TABLE1_NAME = "classes";
    private static final String TABLE2_NAME = "quizes";
    private static final String TABLE3_NAME = "projects";
    private static final String TABLE4_NAME = "major_exams";
    private static final String TABLE5_NAME = "attendance";
    private static final String TABLE6_NAME = "userData";

    private static final String ID_COL = "id";
    // below variable is for our studentId column.
    private static final String STUDENTID_COL = "studentid";

    // below variable is for our student name column
    private static final String NAME_COL = "name";

    // below variable id for our subject column.
    private static final String SUBJECT_COL = "subject";

    // below variable is for our section column.
    private static final String SECTION_COL = "section";

    // creating a constructor for our database handler.
    public DBaddHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }



    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        //db.execSQL("DROP TABLE " + TABLE_NAME);
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + STUDENTID_COL + " INTEGER, "
                + NAME_COL + " TEXT,"
                + SUBJECT_COL + " INTEGER,"
                + SECTION_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
        System.out.println("Added "+TABLE_NAME+" Successfully!");
        String [] tables= {TABLE2_NAME,TABLE3_NAME,TABLE4_NAME,};
        for (String tabs:tables) {
            query = "CREATE TABLE " + tabs + " ("
                    + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "entryNumber" + " INTEGER,"
                    + "scoreObtained" + " INTEGER, "
                    + "hps" + " INTEGER,"
                    + "section" + " TEXT,"
                    + "subjectCode" + " TEXT,"
                    + "date" + " TEXT,"
                    + "name" + " TEXT)";
            db.execSQL(query);
            System.out.println("Added "+tabs+" Successfully!");
        }
        query = "CREATE TABLE " + TABLE5_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "attendance" + " TEXT, "
                + "date" + " TEXT,"
                + "section" + " TEXT,"
                + "subjectCode" + " TEXT,"
                + "name" + " TEXT)";
        db.execSQL(query);
        System.out.println("Added "+TABLE5_NAME+" Successfully!");

        query = "CREATE TABLE " + TABLE1_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "subjectCode" + " TEXT)";
        db.execSQL(query);
        System.out.println("Added "+TABLE1_NAME+" Successfully!");
        query = "CREATE TABLE " + TABLE6_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "sy" + " TEXT, "
                + "fullname" + " TEXT, "
                + "name" + " TEXT)";
        db.execSQL(query);
        System.out.println("Added "+TABLE6_NAME+" Successfully!");

    }


    public int insertAttendance(@NonNull ArrayList<StudentItem> studentItems, String date, String subject) {
        SQLiteDatabase database = this.getWritableDatabase();
        final String INSERT = "insert into "
                + TABLE5_NAME+ " ("+"attendance,date,section,subjectCode,name"+")"+"values (?,?,?,?,?)";
        int aSize = studentItems.size();
        database.beginTransaction();
        try {
            SQLiteStatement insert = database.compileStatement(INSERT);
            for (int i = 0; i < aSize; i++) {
                insert.bindString(1, studentItems.get(i).getStatus());
                insert.bindString(2, date);
                insert.bindString(3, studentItems.get(i).getSection());
                insert.bindString(4, subject);
                insert.bindString(5, studentItems.get(i).getName());
                insert.executeInsert();
            }
            database.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            database.endTransaction();

        }
        return 1;
    }

    public int insertQuiz(@NonNull ArrayList<QuizAddItem> quizAddItems) {
        SQLiteDatabase database = this.getWritableDatabase();
        final String INSERT = "insert into "
                + TABLE2_NAME+ " ("+"entryNumber,scoreObtained,hps,section,subjectCode,date,name"+")"+"values (?,?,?,?,?,?,?)";
        int aSize = quizAddItems.size();
        database.beginTransaction();
        try {
            SQLiteStatement insert = database.compileStatement(INSERT);
            for (int i = 0; i < aSize; i++) {
                insert.bindString(1, String.valueOf(quizAddItems.get(i).getEntryNumber()));
                insert.bindString(2, String.valueOf(quizAddItems.get(i).getScoreObtained()));
                insert.bindString(3, String.valueOf(quizAddItems.get(i).getHps()));
                insert.bindString(4, quizAddItems.get(i).getSection());
                insert.bindString(5, quizAddItems.get(i).getSubjectCode());
                insert.bindString(6, quizAddItems.get(i).getDate());
                insert.bindString(7, quizAddItems.get(i).getName());
                insert.executeInsert();
            }
            database.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            database.endTransaction();

        }
        return 1;
    }

    public int insertProjAssignment(@NonNull ArrayList<QuizAddItem> quizAddItems) {
        SQLiteDatabase database = this.getWritableDatabase();
        final String INSERT = "insert into "
                + TABLE3_NAME+ " ("+"entryNumber,scoreObtained,hps,section,subjectCode,date,name"+")"+"values (?,?,?,?,?,?,?)";
        int aSize = quizAddItems.size();
        database.beginTransaction();
        try {
            SQLiteStatement insert = database.compileStatement(INSERT);
            for (int i = 0; i < aSize; i++) {
                insert.bindString(1, String.valueOf(quizAddItems.get(i).getEntryNumber()));
                insert.bindString(2, String.valueOf(quizAddItems.get(i).getScoreObtained()));
                insert.bindString(3, String.valueOf(quizAddItems.get(i).getHps()));
                insert.bindString(4, quizAddItems.get(i).getSection());
                insert.bindString(5, quizAddItems.get(i).getSubjectCode());
                insert.bindString(6, quizAddItems.get(i).getDate());
                insert.bindString(7, quizAddItems.get(i).getName());
                insert.executeInsert();
            }
            database.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            database.endTransaction();

        }
        return 1;
    }

    public int insertMajorExam(@NonNull ArrayList<QuizAddItem> quizAddItems) {
        SQLiteDatabase database = this.getWritableDatabase();
        final String INSERT = "insert into "
                + TABLE4_NAME+ " ("+"entryNumber,scoreObtained,hps,section,subjectCode,date,name"+")"+"values (?,?,?,?,?,?,?)";
        int aSize = quizAddItems.size();
        database.beginTransaction();
        try {
            SQLiteStatement insert = database.compileStatement(INSERT);
            for (int i = 0; i < aSize; i++) {
                insert.bindString(1, String.valueOf(quizAddItems.get(i).getEntryNumber()));
                insert.bindString(2, String.valueOf(quizAddItems.get(i).getScoreObtained()));
                insert.bindString(3, String.valueOf(quizAddItems.get(i).getHps()));
                insert.bindString(4, quizAddItems.get(i).getSection());
                insert.bindString(5, quizAddItems.get(i).getSubjectCode());
                insert.bindString(6, quizAddItems.get(i).getDate());
                insert.bindString(7, quizAddItems.get(i).getName());
                insert.executeInsert();
            }
            database.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            database.endTransaction();

        }
        return 1;
    }

    // this method is use to add new course to our sqlite database.
    public boolean addNewStudent(String studentName, String studentSubject, String studentSection, int studentId) {


        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.

        values.put(STUDENTID_COL, studentId);
        values.put(NAME_COL, studentName);
        values.put(SUBJECT_COL, studentSubject);
        values.put(SECTION_COL, studentSection);


        // after adding all values we are passing
        // content values to our table.
        try {
            System.out.println(db.insert(TABLE_NAME, null, values));

        }catch (SQLiteException e){
             Log.d("ERROR", e.toString());
             return false;

        }
        // at last we are closing our
        // database after adding database.
        db.close();
        return true;
    }

    public boolean addNew(String studentName, String studentSubject, String studentSection, int studentId, String table_name) {


        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put("entryNumber", studentId);
        values.put("scoreObtained", studentName);
        values.put("hps", studentSubject);
        values.put("section", studentSection);
        values.put("subjectCode", studentSection);
        values.put("studentId", studentSection);


        // after adding all values we are passing
        // content values to our table.
        try {
            System.out.println(db.insert(table_name, null, values));

        }catch (SQLiteException e){
            Log.d("ERROR", e.toString());
            return false;

        }
        // at last we are closing our
        // database after adding database.
        db.close();
        return true;
    }

    public boolean addClass(String className) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase qs = this.getReadableDatabase();
        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put("subjectCode", className);

        // after adding all values we are passing
        // content values to our table.
            try {
            System.out.println(db.insert(TABLE1_NAME, null, values));

        }catch (SQLiteException e){
            Log.d("ERROR", e.toString());
            return false;
        }
        // at last we are closing our
        // database after adding database.
        db.close();
        return true;
    }
    public boolean addUser(String name,String fullname,String sy) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase qs = this.getReadableDatabase();
        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put("name",name);
        values.put("fullname",fullname);
        values.put("sy",sy);

        // after adding all values we are passing
        // content values to our table.
        try {
            System.out.println(db.insert(TABLE6_NAME, null, values));

        }catch (SQLiteException e){
            Log.d("ERROR", e.toString());
            return false;
        }
        // at last we are closing our
        // database after adding database.
        db.close();
        return true;
    }
   public ArrayList<userData> readUserData() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE6_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<userData> userDataArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                userDataArrayList.add(new userData(
                        cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3)));
                System.out.println(userDataArrayList.get(0).getFullname());
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.

        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return userDataArrayList;
    }
    public ArrayList<StudentDataList> readStudentData() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM  " + TABLE_NAME , null);

        // on below line we are creating a new array list.
        ArrayList<StudentDataList> studentDataLists = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                studentDataLists.add(new StudentDataList(
                        cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4)));
               // System.out.println(studentDataLists.get(0).getFullname());
            } while (cursorCourses.moveToNext());
            // moving our cursor to next
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();

        // Traverse through the first list

        return studentDataLists;
    }
    public String queryClass(){
        try{
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorCourses = db.rawQuery(
                "SELECT COUNT(DISTINCT studentid) FROM "
                        + TABLE_NAME, null);
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
               String result= cursorCourses.getString(0);
                //System.out.println("TOTAL: "+result);
                return result;

            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        cursorCourses.close();
    }catch (SQLiteException e){
        Log.d("ERROR", e.toString());
        return "error";
    }
        return "error";
    }

    public String querySubjects(){
        try{
            // on below line we are creating a
            // database for reading our database.
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursorCourses = db.rawQuery(
                    "SELECT COUNT(DISTINCT section) FROM "
                            + TABLE_NAME, null);
            if (cursorCourses.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    //System.out.println("TOTAL: "+result);
                    return cursorCourses.getString(0);


                } while (cursorCourses.moveToNext());
                // moving our cursor to next.
            }
            cursorCourses.close();
        }catch (SQLiteException e){
            Log.d("ERROR", e.toString());
            return "error";
        }
        return "error";
    }

    public String queryClasses(){
        try{
            // on below line we are creating a
            // database for reading our database.
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursorCourses = db.rawQuery(
                    "SELECT COUNT(DISTINCT subject) FROM students", null);
            if (cursorCourses.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    //System.out.println("TOTAL: "+result);
                    return cursorCourses.getString(0);


                } while (cursorCourses.moveToNext());
                // moving our cursor to next.
            }
            cursorCourses.close();
        }catch (SQLiteException e){
            Log.d("ERROR", e.toString());
            return "error";
        }
        return "error";
    }

    public ArrayList<String> distinctSubjects(String section){
        ArrayList<String> list = new ArrayList<>();
        try{
            // on below line we are creating a
            // database for reading our database.
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursorCourses = db.rawQuery(
                    "SELECT DISTINCT subject FROM students WHERE section="+"'"+section+"'", null);
            if (cursorCourses.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    //System.out.println("TOTAL: "+result);
                    list.add(cursorCourses.getString(0));
                } while (cursorCourses.moveToNext());
                // moving our cursor to next.
            }
            cursorCourses.close();
            return list;
        }catch (SQLiteException e){
            Log.d("ERROR", e.toString());
            return list;
        }
    }

    public int getAttendanceDate(String section,String subject, String date){
        try{
            // on below line we are creating a
            // database for reading our database.
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursorCourses = db.rawQuery(
                    "SELECT date FROM attendance WHERE section="+"'"+section+"'"+" AND subjectCode="+"'"+subject+"'", null);
            if (cursorCourses.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    //System.out.println("TOTAL: "+result);
                    if (!cursorCourses.isNull(0)) {
                        String dateVal = cursorCourses.getString(0);
                        String[] splitter = dateVal.split("T");
                        System.out.println("Date from database: "+splitter[0]);
                        System.out.println("Date from system: "+date);
                        if (splitter[0].equals(date)) {
                            return 1;
                        }
                    }
                } while (cursorCourses.moveToNext());
                // moving our cursor to next.
            }
            cursorCourses.close();

        }catch (SQLiteException e){
            Log.d("ERROR", e.toString());
            return 0;
        }
        return 0;
    }

    public ArrayList<ClassItem> studentDetails(String section,String subject){
        ArrayList<ClassItem> list = new ArrayList<>();
        ArrayList<StudentItem> lits = new ArrayList<>();
        try{
            // on below line we are creating a
            // database for reading our database.
            System.out.println("SECTION VALUE: "+section);
            System.out.println("SUBJECT VALUE: "+subject);
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursorCourses = db.rawQuery(
                    "SELECT name,section FROM students WHERE section="+"'"+section+"'"+" AND subject="+"'"+subject+"'", null);
            //System.out.println(cursorCourses.getString(0));
            if (cursorCourses.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    //System.out.println("TOTAL: "+result);
                   // System.out.println(cursorCourses.getString(0)+ " "+cursorCourses.getString(1));
                    //lits.add(new StudentItem(cursorCourses.getString(0),cursorCourses.getString(1),""));
                    list.add(new ClassItem(cursorCourses.getString(0),
                                cursorCourses.getString(1)));
                } while (cursorCourses.moveToNext());
                // moving our cursor to next.
            }
            cursorCourses.close();

            return list;
        }catch (SQLiteException e){
            Log.d("ERROR", e.toString());
            return list;
        }
    }
    public ArrayList<StudentItem> studentDetailsAttendance(String section,String subject){
        //ArrayList<ClassItem> list = new ArrayList<>();
        ArrayList<StudentItem> lits = new ArrayList<>();
        try{
            // on below line we are creating a
            // database for reading our database.
            System.out.println("SECTION VALUE: "+section);
            System.out.println("SUBJECT VALUE: "+subject);
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursorCourses = db.rawQuery(
                    "SELECT name,section FROM students WHERE section="+"'"+section+"'"+" AND subject="+"'"+subject+"'", null);
            //System.out.println(cursorCourses.getString(0));
            if (cursorCourses.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    //System.out.println("TOTAL: "+result);
                    // System.out.println(cursorCourses.getString(0)+ " "+cursorCourses.getString(1));
                    lits.add(new StudentItem(cursorCourses.getString(0),cursorCourses.getString(1),""));

                } while (cursorCourses.moveToNext());
                // moving our cursor to next.
            }
            cursorCourses.close();

            return lits;
        }catch (SQLiteException e){
            Log.d("ERROR", e.toString());
            return lits;
        }
    }

    public ArrayList<GradeItem> queryForGradeSubjItem(String section){
        //ArrayList<ClassItem> list = new ArrayList<>();
        ArrayList<GradeItem> lits = new ArrayList<>();
        try{
            // on below line we are creating a
            // database for reading our database.
            System.out.println("SECTION VALUE: "+section);
            //System.out.println("SUBJECT VALUE: "+subject);
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursorCourses = db.rawQuery(
                    "SELECT subject,section FROM students WHERE section="+"'"+section+"'", null);
            //System.out.println(cursorCourses.getString(0));
            if (cursorCourses.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    //System.out.println("TOTAL: "+result);
                    // System.out.println(cursorCourses.getString(0)+ " "+cursorCourses.getString(1));
                    lits.add(new GradeItem(cursorCourses.getString(0),cursorCourses.getString(1)));

                } while (cursorCourses.moveToNext());
                // moving our cursor to next.
            }
            cursorCourses.close();

            return lits;
        }catch (SQLiteException e){
            Log.d("ERROR", e.toString());
            return lits;
        }
    }

    public ArrayList<QuizViewingItem> quizDetails(String section,String name){
        //ArrayList<ClassItem> list = new ArrayList<>();
        ArrayList<QuizViewingItem> lits = new ArrayList<>();
        try{
            // on below line we are creating a
            // database for reading our database.
            System.out.println("SECTION VALUE: "+section);
            System.out.println("NAME VALUE: "+name);
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursorCourses = db.rawQuery(
                    "SELECT name,section,scoreObtained,hps,date FROM quizes WHERE section="+"'"+section+"'"+" AND name="+"'"+name+"'", null);
            //System.out.println(cursorCourses.getString(0));
            if (cursorCourses.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    //System.out.println("TOTAL: "+result);
                    // System.out.println(cursorCourses.getString(0)+ " "+cursorCourses.getString(1));
                    lits.add(new QuizViewingItem(
                            cursorCourses.getString(0),
                            cursorCourses.getString(1),
                            cursorCourses.getString(2),
                            cursorCourses.getString(3),
                            cursorCourses.getString(4)
                            ));

                } while (cursorCourses.moveToNext());
                // moving our cursor to next.
            }
            cursorCourses.close();

            return lits;
        }catch (SQLiteException e){
            Log.d("ERROR", e.toString());
            return lits;
        }
    }

    public ArrayList<QuizViewingItem> projAssignmentDetails(String section,String name){
        //ArrayList<ClassItem> list = new ArrayList<>();
        ArrayList<QuizViewingItem> lits = new ArrayList<>();
        try{
            // on below line we are creating a
            // database for reading our database.
            System.out.println("SECTION VALUE: "+section);
            System.out.println("NAME VALUE: "+name);
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursorCourses = db.rawQuery(
                    "SELECT name,section,scoreObtained,hps,date FROM projects WHERE section="+"'"+section+"'"+" AND name="+"'"+name+"'", null);
            //System.out.println(cursorCourses.getString(0));
            if (cursorCourses.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    //System.out.println("TOTAL: "+result);
                    // System.out.println(cursorCourses.getString(0)+ " "+cursorCourses.getString(1));
                    lits.add(new QuizViewingItem(
                            cursorCourses.getString(0),
                            cursorCourses.getString(1),
                            cursorCourses.getString(2),
                            cursorCourses.getString(3),
                            cursorCourses.getString(4)
                    ));

                } while (cursorCourses.moveToNext());
                // moving our cursor to next.
            }
            cursorCourses.close();

            return lits;
        }catch (SQLiteException e){
            Log.d("ERROR", e.toString());
            return lits;
        }
    }

    public ArrayList<QuizViewingItem> majorExamDetails(String section,String name){
        //ArrayList<ClassItem> list = new ArrayList<>();
        ArrayList<QuizViewingItem> lits = new ArrayList<>();
        try{
            // on below line we are creating a
            // database for reading our database.
            System.out.println("SECTION VALUE: "+section);
            System.out.println("NAME VALUE: "+name);
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursorCourses = db.rawQuery(
                    "SELECT name,section,scoreObtained,hps,date FROM major_exams WHERE section="+"'"+section+"'"+" AND name="+"'"+name+"'", null);
            //System.out.println(cursorCourses.getString(0));
            if (cursorCourses.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    //System.out.println("TOTAL: "+result);
                    // System.out.println(cursorCourses.getString(0)+ " "+cursorCourses.getString(1));
                    lits.add(new QuizViewingItem(
                            cursorCourses.getString(0),
                            cursorCourses.getString(1),
                            cursorCourses.getString(2),
                            cursorCourses.getString(3),
                            cursorCourses.getString(4)
                    ));

                } while (cursorCourses.moveToNext());
                // moving our cursor to next.
            }
            cursorCourses.close();

            return lits;
        }catch (SQLiteException e){
            Log.d("ERROR", e.toString());
            return lits;
        }
    }
    public ArrayList<QuizzItem> quizzDetailsStudents(String section,String subject){
        //ArrayList<ClassItem> list = new ArrayList<>();
        ArrayList<QuizzItem> lits = new ArrayList<>();
        try{
            // on below line we are creating a
            // database for reading our database.
            System.out.println("SECTION VALUE: "+section);
            System.out.println("SUBJECT VALUE: "+subject);
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursorCourses = db.rawQuery(
                    "SELECT name,section FROM students WHERE section="+"'"+section+"'"+" AND subject="+"'"+subject+"'", null);
            //System.out.println(cursorCourses.getString(0));
            if (cursorCourses.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    //System.out.println("TOTAL: "+result);
                    // System.out.println(cursorCourses.getString(0)+ " "+cursorCourses.getString(1));
                    lits.add(new QuizzItem(cursorCourses.getString(0),cursorCourses.getString(1),""));

                } while (cursorCourses.moveToNext());
                // moving our cursor to next.
            }
            cursorCourses.close();

            return lits;
        }catch (SQLiteException e){
            Log.d("ERROR", e.toString());
            return lits;
        }
    }

    public ArrayList<ViewItem> studentViewAttendance(String section,String subject){
        //ArrayList<ClassItem> list = new ArrayList<>();
        ArrayList<ViewItem> lits = new ArrayList<>();
        try{
            // on below line we are creating a
            // database for reading our database.
            System.out.println("SECTION VALUE: "+section);
            System.out.println("SUBJECT VALUE: "+subject);
            SQLiteDatabase db = this.getReadableDatabase();

            //Cursor cursorCourses = db.rawQuery("SELECT name,section,status,date FROM students WHERE section="+"'"+section+"'"+" AND subject="+"'"+subject+"'"+" AND date="+"'"+date+"'", null);
            Cursor cursorCourses = db.rawQuery("SELECT name,section,attendance,date FROM attendance WHERE section="+"'"+section+"'"+" AND subjectCode="+"'"+subject+"'", null);
            //System.out.println(cursorCourses.getString(0));
            if (cursorCourses.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    //System.out.println("TOTAL: "+result);
                    // System.out.println(cursorCourses.getString(0)+ " "+cursorCourses.getString(1));
                    lits.add(new ViewItem(
                            cursorCourses.getString(0),
                            cursorCourses.getString(1),
                            cursorCourses.getString(2),
                            cursorCourses.getString(3)));

                } while (cursorCourses.moveToNext());
                // moving our cursor to next.
            }
            cursorCourses.close();

            return lits;
        }catch (SQLiteException e){
            Log.d("ERROR", e.toString());
            return lits;
        }
    }

    public ArrayList<String> distinctSections(){
        ArrayList<String> list = new ArrayList<>();
        try{
            // on below line we are creating a
            // database for reading our database.
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursorCourses = db.rawQuery(
                    "SELECT DISTINCT section FROM students", null);
            if (cursorCourses.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    //System.out.println("TOTAL: "+result);
                    list.add(cursorCourses.getString(0));
                } while (cursorCourses.moveToNext());
                // moving our cursor to next.
            }
            cursorCourses.close();
            return list;
        }catch (SQLiteException e){
            Log.d("ERROR", e.toString());
            return list;
        }
    }

    public ArrayList<GradeItem> gradeSectionSubject(String section){
        ArrayList<GradeItem> list = new ArrayList<>();
        try{
            // on below line we are creating a
            // database for reading our database.
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursorCourses = db.rawQuery(
                    "SELECT DISTINCT subject,section FROM students WHERE section="+"'"+section+"'", null);
            if (cursorCourses.moveToFirst()) {
                do {

                    list.add(new GradeItem(cursorCourses.getString(0),cursorCourses.getString(1)));
                } while (cursorCourses.moveToNext());
                // moving our cursor to next.
            }
            cursorCourses.close();
            return list;
        }catch (SQLiteException e){
            Log.d("ERROR", e.toString());
            return list;
        }
    }


    public ArrayList<GradeStudentItem> gradegradegrade(String section, String subject, View view){
        ArrayList<GradeStudentItem> list = new ArrayList<>();
        ArrayList<String> lits = new ArrayList<>();
        DBaddHandler dBaddHandler = new DBaddHandler(view.getContext());

       // final ArrayList<QuizItem> quiz = dBaddHandler.quizGradeMaking(section,name,subject);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery(
                "SELECT name FROM students WHERE section="+"'"+section+"'"+" AND subject="+"'"+subject+"'", null);

        if (cursorCourses.moveToFirst()) {
            do {
                lits.add(cursorCourses.getString(0));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        cursorCourses.close();

        ArrayList<QuizItem> quiz;

        ArrayList<QuizItem> projects;

        ArrayList<QuizItem> major_exams;

        int x=0;

        for (String name:lits) {
            try {
                double quizGrade = 0;
                quiz = dBaddHandler.quizGradeMaking(section,name,subject);
                System.out.println("VAL"+quiz.get(x).getScore());
                    int score_quiz = Integer.parseInt(quiz.get(x).getScore());
                    int all_quiz = Integer.parseInt(quiz.get(x).getHps());
                    double div = (double) score_quiz / all_quiz;
                    double transmut = div * 100;
                    System.out.println("Quiz: "+transmut);
                    quizGrade = transmutation_table(transmut);

                double projectsGrade = 0;
                projects = dBaddHandler.projectGradeMaking(section,name,subject);
                if (projects.get(x).getName()!=null&& projects.get(x).getName().equals(name)) {
                    int score_projects = Integer.valueOf(projects.get(x).getScore());
                    int all_projects = Integer.valueOf(projects.get(x).getHps());
                    double div1 = (double) score_projects / all_projects;
                    double transmut1 = div1 * 100;
                    System.out.println("Projects: "+transmut1);
                    projectsGrade = transmutation_table(transmut1);
                }
                double majorGrade = 0;
                major_exams = dBaddHandler.majorGradeMaking(section,name,subject);
                System.out.println(major_exams.get(x).getScore()+ " "+major_exams.get(x).getHps());
                if (major_exams.get(x).getName()!=null&&major_exams.get(x).getName().equals(name)) {
                    int score_major = Integer.valueOf(major_exams.get(x).getScore());
                    int all_major = Integer.valueOf(major_exams.get(x).getHps());
                    double div2 = (double) score_major /all_major;
                    double transmut2 = div2 * 100;
                    System.out.println("Major: "+transmut2);
                    majorGrade = transmutation_table(transmut2);
                    System.out.println("Major: "+majorGrade);
                }

                double graded =(quizGrade * .40) + (projectsGrade * .30) + (majorGrade * .30);
                String result = String.format(Locale.ENGLISH,"%.2f", graded);
                System.out.println("Grade: "+result);
                if(graded!=0.0) {
                    System.out.println(result);
                    list.add(new GradeStudentItem(name, section, result));
                }
            }catch (NumberFormatException | IndexOutOfBoundsException e){
                Log.d("ERROR", e.toString());
                x++;
            }
            x++;
        }
        return list;
    }
    public double transmutation_table(double val){
        if(val<=1) return 5;
        else if(val<=3 && val>=2){
            return 4.9;
        }else if(val<=5 && val>=4){
            return 4.8;
        }else if(val<=7 && val>=6){
            return 4.7;
        }else if(val<=10 && val>=8){
            return 4.6;
        }else if(val<=13 && val>=11){
            return 4.5;
        }else if(val<=16 && val>=14){
            return 4.4;
        }else if(val<=19 && val>=17){
            return 4.3;
        }else if(val<=22 && val>=20){
            return 4.2;
        }else if(val<=24 && val>=23){
            return 4.1;
        }else if(val<=26 && val>=25){
            return 4.0;
        }else if(val<=28 && val>=27){
            return 3.9;
        }else if(val==29){
            return 3.8;
        }else if(val<=33 && val>=30){
            return 3.7;
        }else if(val<=36 && val>=34){
            return 3.6;
        }else if(val<=39 && val>=37){
            return 3.5;
        }else if(val<=42 && val>=40){
            return 3.4;
        }else if(val<=45 && val>=43){
            return 3.3;
        }else if(val<=48 && val>=46){
            return 3.2;
        }else if(val==49){
            return 3.1;
        }else if(val<=51 && val>=50){
            return 3.0;
        }else if(val<=53 && val>=52){
            return 2.9;
        }else if(val<=56 && val>=54){
            return 2.8;
        }else if(val<=59 && val>=57){
            return 2.7;
        }else if(val<=62 && val>=60){
            return 2.6;
        }else if(val<=64 && val>=63){
            return 2.5;
        }else if(val<=66 && val>=65){
            return 2.4;
        }else if(val<=69 && val>=67){
            return 2.3;
        }else if(val<=72 && val>=70){
            return 2.2;
        }else if(val<=74 && val>=73){
            return 2.1;
        }else if(val<=76 && val>=75){
            return 2.0;
        }else if(val<=78 && val>=77){
            return 1.9;
        }else if(val<=80 && val>=79){
            return 1.8;
        }else if(val<=83 && val>=81){
            return 1.7;
        }else if(val<=86 && val>=84){
            return 1.6;
        }else if(val<=89 && val>=87){
            return 1.5;
        }else if(val<=92 && val>=90){
            return 1.4;
        }else if(val<=95 && val>=93){
            return 1.3;
        }else if(val<=98 && val>=96){
            return 1.2;
        }else if(val==99){
            return 1.1;
        }else if(val==100){
            return 1.0;
        }
        return 0;
    }

    public ArrayList<QuizItem> quizGradeMaking(String section,String name,String subject){
        //ArrayList<ClassItem> list = new ArrayList<>();
        ArrayList<QuizItem> lits = new ArrayList<>();
        try{
            // on below line we are creating a
            // database for reading our database
          /*  System.out.println("SECTION VALUE: "+section);
            System.out.println("NAME VALUE: "+subject);*/
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursorCourses = db.rawQuery(
                    "SELECT name,section,SUM(hps),SUM(scoreObtained) FROM quizes WHERE section="+"'"+section+"'"+" AND name="+"'"+name+"'"+" AND subjectCode="+"'"+subject+"'", null);
            //System.out.println(cursorCourses.getString(0));
            if (cursorCourses.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    // System.out.println("TOTAL: "+result);
                    System.out.println(cursorCourses.getString(0)+ " "+cursorCourses.getString(1));
                    lits.add(new QuizItem(
                            cursorCourses.getString(0),
                            cursorCourses.getString(1),
                            cursorCourses.getString(2),
                            cursorCourses.getString(3)
                    ));

                } while (cursorCourses.moveToNext());
                // moving our cursor to next.
            }
            cursorCourses.close();

            return lits;
        }catch (SQLiteException e){
            Log.d("ERROR", e.toString());
            return lits;
        }
    }

    public ArrayList<QuizItem> projectGradeMaking(String section,String name,String subject){
        //ArrayList<ClassItem> list = new ArrayList<>();
        ArrayList<QuizItem> lits = new ArrayList<>();
        try{
            // on below line we are creating a
            // database for reading our database.
          /*  System.out.println("SECTION VALUE: "+section);
            System.out.println("NAME VALUE: "+name);*/
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursorCourses = db.rawQuery(
                    "SELECT name,section,SUM(hps),SUM(scoreObtained) FROM projects WHERE section="+"'"+section+"'"+" AND name="+"'"+name+"'"+" AND subjectCode="+"'"+subject+"'", null);
            //System.out.println(cursorCourses.getString(0));
            if (cursorCourses.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    //System.out.println("TOTAL: "+result);
                    System.out.println(cursorCourses.getString(0)+ " "+cursorCourses.getString(1));
                    lits.add(new QuizItem(
                            cursorCourses.getString(0),
                            cursorCourses.getString(1),
                            cursorCourses.getString(2),
                            cursorCourses.getString(3)
                    ));

                } while (cursorCourses.moveToNext());
                // moving our cursor to next.
            }
            cursorCourses.close();

            return lits;
        }catch (SQLiteException e){
            Log.d("ERROR", e.toString());
            return lits;
        }
    }
    public ArrayList<QuizItem> majorGradeMaking(String section,String name,String subject){
        //ArrayList<ClassItem> list = new ArrayList<>();
        ArrayList<QuizItem> lits = new ArrayList<>();
        try{
            // on below line we are creating a
            // database for reading our database.
           /* System.out.println("SECTION VALUE: "+section);
            System.out.println("NAME VALUE: "+name);*/
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursorCourses = db.rawQuery(
                    "SELECT name,section,SUM(hps),SUM(scoreObtained) FROM major_exams WHERE section="+"'"+section+"'"+" AND name="+"'"+name+"'"+" AND subjectCode="+"'"+subject+"'", null);
            //System.out.println(cursorCourses.getString(0));
            if (cursorCourses.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    //System.out.println("TOTAL: "+result);
                    System.out.println(cursorCourses.getString(0)+ " "+cursorCourses.getString(1));
                    lits.add(new QuizItem(
                            cursorCourses.getString(0),
                            cursorCourses.getString(1),
                            cursorCourses.getString(2),
                            cursorCourses.getString(3)
                    ));

                } while (cursorCourses.moveToNext());
                // moving our cursor to next.
            }
            cursorCourses.close();

            return lits;
        }catch (SQLiteException e){
            Log.d("ERROR", e.toString());
            return lits;
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String [] tables= {TABLE_NAME,TABLE1_NAME,TABLE2_NAME,TABLE3_NAME,TABLE4_NAME,TABLE5_NAME,TABLE6_NAME};
        for (String tabs:tables) {
            // this method is called to check if the table exists already.
            db.execSQL("DROP TABLE IF EXISTS " + tabs);
        }
        onCreate(db);
    }
    public void logoutDrop(){
        SQLiteDatabase db = this.getWritableDatabase();
        String [] tables= {TABLE_NAME,TABLE1_NAME,TABLE2_NAME,TABLE3_NAME,TABLE4_NAME,TABLE5_NAME,TABLE6_NAME};
        for (String tabs:tables) {
            // this method is called to check if the table exists already.
            db.execSQL("DELETE FROM " + tabs);
            //Toast.makeText(,"",Toast.LENGTH_LONG);
        }
    }


}
