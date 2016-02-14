package com.example.tylersorenson.personalmultithreading;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Tyler Sorenson on 2/14/2016.
 */
public class Create extends AsyncTask<Integer, Integer, Void>{
    Context context;
    String filename;
    ProgressBar progressBar;
    int numbers = 10;

    Create(Context context, String FILE_NAME, ProgressBar progressBar){
        this.context = context;
        filename = FILE_NAME;
        this.progressBar = progressBar;
    }

    public void writeFile(Integer numbers) {
        File file = new File(context.getFilesDir(), filename);
        OutputStream out;
        try {
            out = new BufferedOutputStream(new FileOutputStream(file));
            for (int i = 0;i <= numbers;i++) {
                out.write(i);
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
