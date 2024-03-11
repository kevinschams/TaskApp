package com.example.taskapp;

import com.example.taskapp.models.Task;

import java.util.ArrayList;
import java.util.List;

public interface Taskable {


    public Task getTaskById(long taskId);

    ArrayList<Task> getAllTasks();

    public Task updateTask(Task task) throws Exception;

    public Task insertTask(Task t) throws Exception;

    public int deleteTask(String taskId);
}
