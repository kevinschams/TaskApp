package com.example.taskapp;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.taskapp.dataaccess.TaskDataAccess;
import com.example.taskapp.models.Task;

import java.util.ArrayList;

public class TaskListActivity extends AppCompatActivity {

    public static final String TAG = "TaskListActivity";

    private ListView lsTasks;
    private Button btnAddTask;
    private TaskDataAccess da;
    private ArrayList<Task> allTasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        lsTasks = findViewById(R.id.lsTasks);
        btnAddTask = findViewById(R.id.btnAddTask);
        da = new TaskDataAccess(this);
        allTasks = da.getAllTasks();
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TaskListActivity.this, TaskDetails.class);
                startActivity(i);
            }
        });

        ArrayAdapter<Task> adapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1, allTasks);
        lsTasks.setAdapter(adapter);

        lsTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d(TAG, position + "");
                Task selectedTask = allTasks.get(position);
//                Log.d(TAG, selectedTask.toString());
                Intent i = new Intent(TaskListActivity.this, TaskDetails.class);
                i.putExtra(TaskDetails.EXTRA_TASK_ID, selectedTask.getId());
                startActivity(i);
            }
        });
    }


}