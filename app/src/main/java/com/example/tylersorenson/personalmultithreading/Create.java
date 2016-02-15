package com.example.tylersorenson.personalmultithreading;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by Tyler Sorenson on 2/14/2016.
 */
public class Create extends AsyncTask<Integer, Integer, Void>{
    Context context;
    String filename;
    ProgressBar progressBar;

    Create(Context context, String FILE_NAME, ProgressBar progressBar){
        this.context = context;
        filename = FILE_NAME;
        this.progressBar = progressBar;
    }

    public void writeFile(Integer numbers) {
        try {
            FileOutputStream fileOut = context.openFileOutput(filename, Context.MODE_PRIVATE);
            for (int i = 1;i <= numbers;i++) {
                fileOut.write(Integer.toString(i).getBytes());
                fileOut.write("\n".getBytes());
                publishProgress(i);
                Thread.sleep(250);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Void doInBackground(Integer... params) {
        writeFile(params[0]);
        return null;
    }

    protected void onProgressUpdate(Integer... progress) {
        progressBar.setProgress(progress[0]);
    }
}
