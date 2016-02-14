package com.example.tylersorenson.personalmultithreading;

import android.content.Context;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tyler Sorenson on 2/14/2016.
 */
public class Load extends Thread{
    List<String> numbers;
    String filename;
    Context context;

    Load(Context context, String filename){
        this.filename = filename;
        this.context = context;
        run();
    }

    public void readFile() {
        numbers = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                numbers.add(line);
                Thread.sleep(250);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        readFile();
    }
}
