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

    Create(Context context){
        this.context = context;
    }

    public void writeFile() {
        String FILE_NAME = "numbers.txt";
        File file = new File(context.getFilesDir(), FILE_NAME);
        OutputStream out;
        try {
            out = new BufferedOutputStream(new FileOutputStream(file));
            for (int i = 0;i <= 10;i++) {
                out.write(i);
                Thread.sleep(250);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        writeFile();
    }
}
