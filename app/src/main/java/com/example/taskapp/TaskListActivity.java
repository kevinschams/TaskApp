package com.example.taskapp;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.taskapp.dataaccess.CSVTaskDataAccess;
import com.example.taskapp.dataaccess.TaskDataAccess;
import com.example.taskapp.models.Task;

import java.util.ArrayList;

public class TaskListActivity extends AppCompatActivity {

    public static final String TAG = "TaskListActivity";

    private ListView lsTasks;
    private Button btnAddTask;
    private Taskable da;
    private ArrayList<Task> allTasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        da = new CSVTaskDataAccess(this);
        allTasks = da.getAllTasks();

        if(allTasks.size()==0){
            Intent i = new Intent(TaskListActivity.this, TaskDetails.class);
            startActivity(i);
        }else {

            btnAddTask = findViewById(R.id.btnAddTask);
            btnAddTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(TaskListActivity.this, TaskDetails.class);
                    startActivity(i);
                }
            });
        }

            lsTasks = findViewById(R.id.lsTasks);
            example();

    }


    public void example() {
        ArrayAdapter<Task> adapter = new ArrayAdapter<Task>(this, R.layout.custom_task_list_item, R.id.lbl1, allTasks) {

            @NonNull
            public View getView(int position, View convertView, @NonNull ViewGroup parentListView) {
                View listItemView = super.getView(position, convertView, parentListView);
                TextView lbl1 = listItemView.findViewById(R.id.lbl1);
                CheckBox checkBox = listItemView.findViewById(R.id.checkBox);

                Task curTask = allTasks.get(position);
                lbl1.setText(curTask.getDescription());
                checkBox.setChecked(curTask.isDone());


                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        curTask.setDone(checkBox.isChecked());

                        try {
                            da.updateTask(curTask);
                            Log.d(TAG, "Updated task!!" + curTask);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

                lbl1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        long id = curTask.getId();
                        Log.d(TAG, "onClick: " + id);
//                        Task id = allTasks.get(position);
                        Intent i = new Intent(TaskListActivity.this, TaskDetails.class);
                        i.putExtra(TaskDetails.EXTRA_TASK_ID, id);
                        startActivity(i);
                    }
                });
                return listItemView;
            }


        };
        lsTasks.setAdapter(adapter);
    }

}
