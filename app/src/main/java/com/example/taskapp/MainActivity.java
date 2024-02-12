package com.example.taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.taskapp.dataaccess.TaskDataAccess;
import com.example.taskapp.models.Task;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Task t = new Task("Clean room", new Date(), false);
        Log.d(TAG, t.toString());
        Log.d(TAG, Boolean.toString(t.isValid()));

        Task t2 = new Task("Fix car", null, false);
        Log.d(TAG, t2.toString());
        Log.d(TAG, Boolean.toString(t2.isValid()));

//        TaskDataAccess tda = new TaskDataAccess(this);
        Log.d(TAG, TaskDataAccess.allTasks.toString());
    }
}