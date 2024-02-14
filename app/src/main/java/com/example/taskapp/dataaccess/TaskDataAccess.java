package com.example.taskapp.dataaccess;

import android.content.Context;

import com.example.taskapp.models.Task;

import java.util.ArrayList;
import java.util.Date;

public class TaskDataAccess {

     public static ArrayList<Task> allTasks = new ArrayList<Task>(){{
          add(new Task(1, "Mow the lawn", new Date(124, 0, 1), false));
          add(new Task(2, "Take out the trash", new Date(124, 0, 1), false));
          add(new Task(3, "Pay Rent", new Date(124, 0, 1), true));
     }};

     private Context context;

     public TaskDataAccess(Context context){
          this.context = context;
     }

     public ArrayList<Task> getAllTasks(){
          return allTasks;
     }

     public Task getTaskById(long id){
          for(Task t : allTasks){
               if(t.getId() == id){
                    return t;
               }
          }
          return null;
     }

     private long getMaxId(){
          long maxId = 0;
          for(Task t : allTasks){
               if(t.getId() > maxId){
                    maxId = t.getId();
               }
          }
          return maxId;
     }

     public Task insertTask(Task t) throws Exception{
          if(t.isValid()){
               t.setId(getMaxId() + 1);
               allTasks.add(t);
               return t;
          }else{
               throw new Exception("INVALID TASK ON INSERT");
          }
     }

     public Task updateTask(Task t) throws Exception{
          if(t.isValid()){
               Task taskToUpdate = getTaskById(t.getId());
               taskToUpdate.setDescription(t.getDescription());
               taskToUpdate.setDue(t.getDue());
               taskToUpdate.setDone(t.isDone());
               return taskToUpdate;
          }else{
               throw new Exception("INVALID TASK IN UPDATE");
          }
     }

     public int deleteTask(Task taskToDelete){
          for(Task t : allTasks ){
               if(t.getId() == taskToDelete.getId()){
                    allTasks.remove(t);
                    return 1;
               }
          }
          return 0;
     }
}