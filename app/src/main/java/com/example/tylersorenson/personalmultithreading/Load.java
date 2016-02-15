package com.example.tylersorenson.personalmultithreading;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by Tyler Sorenson on 2/14/2016.
 */
public class Load extends AsyncTask<Void, Integer, List<String>>{
    List<String> numbers;
    String filename;
    Context context;
    ProgressBar progressBar;
    ListView content;
    ArrayAdapter contentAdapter;

    Load(Context context, String filename, ProgressBar progressBar, ListView content){
        this.filename = filename;
        this.context = context;
        this.progressBar = progressBar;
        this.content = content;
    }

    public void readFile() {
        numbers = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                numbers.add(line);
                publishProgress(i);
                Thread.sleep(250);
                i++;
            }
            i = 10;
            publishProgress(i);
            publishProgress(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> doInBackground(Void... params) {
        readFile();
        return numbers;
    }

    protected void onProgressUpdate(Integer... progress) {
        progressBar.setProgress(progress[0]);
    }

    public void setAdapter(List <String> data) {
        contentAdapter = new ArrayAdapter<String>(context, R.layout.list_display, data);
        content.setAdapter(contentAdapter);
        contentAdapter.notifyDataSetChanged();
    }
}
