package com.example.taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.taskapp.dataaccess.CSVTaskDataAccess;
import com.example.taskapp.dataaccess.TaskDataAccess;
import com.example.taskapp.models.Task;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Task t = new Task("Clean room", new Date(), false);
//        Log.d(TAG, t.toString());
//        Log.d(TAG, Boolean.toString(t.isValid()));
//
//        Task t2 = new Task("Fix car", null, false);
//        Log.d(TAG, t2.toString());
//        Log.d(TAG, Boolean.toString(t2.isValid()));
//
//        TaskDataAccess tda = new TaskDataAccess(this);
//
//        Log.d(TAG, TaskDataAccess.allTasks.toString());
//
//        // Test getAll
//        ArrayList<Task> tasks = tda.getAllTasks();
//        Log.d(TAG, tasks.toString());
//
//        // Test insert
//        Task newTask = new Task("Hair cut", new Date(), false);
//        try {
//            newTask = tda.insertTask(newTask);
//            Log.d(TAG, "NEW TASK ID: " + newTask.getId());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        // Test getById
//        Task someTask = tda.getTaskById(1);
//        Log.d(TAG, someTask.toString());
//
//        // Test update
//        someTask.setDescription("Do homework");
//        try {
//            tda.updateTask(someTask);
//            Log.d(TAG, tda.getAllTasks().toString());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        // Test delete
//        int numRows = tda.deleteTask(newTask);
//        Log.d(TAG, "Num rows: " + numRows);
//        Log.d(TAG, tda.getAllTasks().toString());

        Task testTask = new Task(1, "Blah", new Date(), true);
        CSVTaskDataAccess da = new CSVTaskDataAccess(this);
//
//        Log.d(TAG, da.convertTaskToCSV(testTask));
//        da.saveTasks();
        da.loadTasks();
    }
}