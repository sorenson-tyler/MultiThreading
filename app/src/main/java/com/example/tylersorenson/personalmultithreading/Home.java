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
import java.util.concurrent.ExecutionException;

public class Home extends AppCompatActivity {
    private final String FILE_NAME = "numbers.txt";
    ProgressBar progressBar;
    Button create, load, clear;
    List <String> data;
    ArrayAdapter<String> contentAdapter;
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
                createClick = new Create(getApplicationContext(), FILE_NAME, progressBar);
                createClick.execute(10);
            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadClick = new Load(getApplicationContext(), FILE_NAME, progressBar, content);
                try {
                    data = loadClick.execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                setAdapter(data);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentAdapter.clear();
            }
        });
    }

    public void setUp() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(10);
        create = (Button) findViewById(R.id.create);
        load = (Button) findViewById(R.id.load);
        clear = (Button) findViewById(R.id.clear);
        content = (ListView) findViewById(R.id.listView);
    }

    public void setAdapter(List <String> data) {
        contentAdapter = new ArrayAdapter<String>(this, R.layout.list_display, data);
        content.setAdapter(contentAdapter);
        contentAdapter.notifyDataSetChanged();
    }
}
