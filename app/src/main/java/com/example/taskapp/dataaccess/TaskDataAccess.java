package com.example.taskapp.dataaccess;

import android.content.Context;

import com.example.taskapp.models.Task;

import java.util.ArrayList;
import java.util.Date;

public class TaskDataAccess {

     public static ArrayList<Task> allTasks = new ArrayList<Task>(){{
          add(new Task(1, "Mow the lawn", new Date(124, 0, 1), false));
          add(new Task(2, "Take out trash", new Date(124, 0, 1), false));
          add(new Task(3, "Pay rent", new Date(124, 0, 1), true));
     }};
     private Context context;
     public TaskDataAccess(Context context){
          this.context = context;
     }
     public ArrayList<Task> getAllTasks(){
          return null;
     }
     public Task getTaskById(long id){
          return null;
     }
     public Task insertTask(Task t){
          return null;
     }
     public Task updateTask(Task t){
          return null;
     }
     public int deleteTask(Task t){
          return 0;
     }
}
