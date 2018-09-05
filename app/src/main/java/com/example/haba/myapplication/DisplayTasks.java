package com.example.haba.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DisplayTasks extends AppCompatActivity {
    Button task_button;
    public static TextView task_textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_tasks);
        task_button= (Button) findViewById(R.id.task_button);
        task_textView = (TextView) findViewById(R.id.task_textView);

    }
    public void fetchAllData(View view){
        JSONFetcher process = new JSONFetcher();
        process.execute();
    }
    public void fetchOwnData(View view){
        JSONFetcher process = new JSONFetcher();
        process.execute();
    }

}


