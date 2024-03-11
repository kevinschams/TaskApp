package com.example.taskapp.dataaccess;

import android.content.Context;
import android.util.Log;

import com.example.taskapp.Taskable;
import com.example.taskapp.models.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVTaskDataAccess implements Taskable {
    public static final String TAG = "CSVTaskDataAccess";
    public static final String DATA_FILE = "task.csv";
    private ArrayList<Task> allTasks;
    Context context;
    private SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
    public CSVTaskDataAccess(Context c){
        this.context = c;
        this.allTasks = new ArrayList<Task>();
    }

    public void loadTasks(){
        String dataString = fileio.FileHelper.readFromFile(DATA_FILE, context);
//        Log.d(TAG, dataString);
        ArrayList<Task> list = new ArrayList<>();
        String[] lines = dataString.split("\n");
        for(String line : lines){
            Task t = convertCSVToTask(line);
            list.add(t);
        }
        allTasks = list;
        Log.d(TAG, allTasks.toString());
    }

    private Task convertCSVToTask(String csvLine) {
        String[] csvVals = csvLine.split(",");
        long id = Long.parseLong(csvVals[0]);
        String desc = csvVals[1];
        String dateStr = csvVals[2];
        String doneStr = csvVals[3];

        Date d;
        try {
            d = sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        boolean done = doneStr.equals("true");

        Task t = new Task(id, desc, d, done);
        return t;
    }

    private void saveTasks(){
//        allTasks.add(new Task(1, "Feed dog", new Date(), false));
//        allTasks.add(new Task(2, "Feed dog 2", new Date(), false));

        String dataString = "";
        StringBuilder sb = new StringBuilder();

        for(Task t: allTasks){
            String csv = convertTaskToCSV(t) + "\n";
            sb.append(csv);
        }
        Log.d(TAG, sb.toString());

        if(fileio.FileHelper.writeToFile(DATA_FILE, sb.toString(), context)){
            Log.d(TAG, "Success");
        }else{
            Log.d(TAG, "FAILED TO SAVE DATA TO .csv");
        }
    }

    private String convertTaskToCSV(Task t){

        String dateStr = null;
        dateStr = sdf.format(t.getDue());

        return String.format("%d,%s,%s,%s", t.getId(),t.getDescription(),dateStr,t.isDone() ? "true" : "false");
    }

    @Override
    public Task getTaskById(long taskId) {
        return null;
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        return null;
    }

    @Override
    public Task updateTask(Task task) throws Exception {
        return null;
    }

    @Override
    public Task insertTask(Task t) throws Exception {
        return null;
    }

    @Override
    public int deleteTask(String taskId) {
        return 0;
    }
}
