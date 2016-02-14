package com.example.tylersorenson.personalmultithreading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private final String FILE_NAME = "numbers.txt";
    ProgressBar progressBar;
    Button create, load, clear;
    List<String> fileData = null;
    ListView content;
    Create createClick;
    Load loadClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUp();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createClick = new Create(getApplicationContext(), FILE_NAME);
                createClick.start();
            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadClick = new Load(getApplicationContext(), FILE_NAME);
                loadClick.start();
                fileData = loadClick.getList();
                setListView(fileData);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setUp() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        create = (Button) findViewById(R.id.create);
        load = (Button) findViewById(R.id.load);
        clear = (Button) findViewById(R.id.clear);
        content = (ListView) findViewById(R.id.listView);
    }

    public void setListView(List<String> data) {
        if (fileData == null) {
            Log.e("ERROR", "String List is empty");
        }
        else {
            ArrayAdapter<String> fileData = new ArrayAdapter<String>(this, R.layout.activity_home, data);
        }
    }
}
