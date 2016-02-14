package com.example.tylersorenson.personalmultithreading;

import android.content.Context;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Tyler Sorenson on 2/14/2016.
 */
public class Create extends Thread{
    Context context;
    String filename;

    Create(Context context, String FILE_NAME){
        this.context = context;
        filename = FILE_NAME;
    }

    public void writeFile() {
        File file = new File(context.getFilesDir(), filename);
        OutputStream out;
        try {
            out = new BufferedOutputStream(new FileOutputStream(file));
            for (int i = 0;i <= 10;i++) {
                out.write(i);
                Thread.sleep(250);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        writeFile();
    }
}
