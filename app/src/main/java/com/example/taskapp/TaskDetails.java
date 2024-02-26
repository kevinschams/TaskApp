package com.example.taskapp;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.taskapp.dataaccess.TaskDataAccess;
import com.example.taskapp.models.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskDetails extends AppCompatActivity {

    public static final String TAG = "TaskDetails";
    public static final String EXTRA_TASK_ID = "taskId";
    TaskDataAccess da;
    Task task;

    EditText txtDescription;
    EditText txtDueDate;
    CheckBox chkDone;
    Button btnSave;
    SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        txtDescription = findViewById(R.id.txtDescription);
        txtDueDate = findViewById(R.id.txtDueDate);
        chkDone = findViewById(R.id.chkDone);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(save()){
                    Intent i = new Intent(TaskDetails.this, TaskListActivity.class);
                    startActivity(i);
                }
            }
        });


        da = new TaskDataAccess(this);
        Intent i = getIntent();
        long id = i.getLongExtra(EXTRA_TASK_ID, 0);
        if (id > 0){
            task = da.getTaskById(id);
            Log.d(TAG, "id of task to get: " + id);
//            Log.d(TAG, task.toString());
            putDataIntoUI();
        }else {
            task = new Task("", null, false);
            Log.d(TAG, "No task to get, create a new one.");
        }
    }

    private void putDataIntoUI(){
        if(task != null){
            txtDescription.setText(task.getDescription());
            String dateStr = null;
            dateStr = sdf.format(task.getDue());
            txtDueDate.setText(dateStr);
            chkDone.setChecked(task.isDone());
        }
    }
    private boolean validate(){
        boolean isValid = true;

        if(txtDescription.getText().toString().isEmpty()){
            isValid = false;
            txtDescription.setError("You must enter a description");
        }

        if(txtDueDate.getText().toString().isEmpty()){
            isValid = false;
            txtDueDate.setError("You must enter a due date");
        }else {
            String regex = "^(\\d{1,2}(\\/|-)\\d{1,2}(\\/|-)\\d{2,4})$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(txtDueDate.getText().toString());
            if(!matcher.matches()){
                isValid = false;
                txtDueDate.setError("The date entered is not valid");
            }
        }
        return isValid;
    }

    private boolean save(){
        if(validate()){
            getDataFromUI();
            if(task.getId()>0){
                try {
                    da.updateTask(task);
                } catch (Exception e) {
                    Log.d(TAG, "Failed to update" + e.getMessage());
                    return false;
                }
            }else{
                try {
                    da.insertTask(task);
                } catch (Exception e) {
                    Log.d(TAG, "Failed to insert" + e.getMessage());
                    return false;
                }
            }
        }
        return true;
    }

    private void getDataFromUI(){
        String desc = txtDescription.getText().toString();
        String dateStr = txtDueDate.getText().toString();
        boolean done = chkDone.isChecked();
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if(task != null){
            task.setDescription(desc);
            task.setDue(date);
            task.setDone(done);
        }else{
            Log.d(TAG,"Task is null");
        }
    }

}