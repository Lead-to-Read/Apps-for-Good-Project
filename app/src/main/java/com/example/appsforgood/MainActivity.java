package com.example.appsforgood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Book> books = new ArrayList<Book>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        readBookData();
    }

    private int fixYear(String start) {
        int endMonth = start.indexOf("/");
        String middle = start.substring(endMonth + 1);
        int endDay = middle.indexOf("/");
        String yearString = middle.substring(endDay + 1, endDay + 5);
        int year = Integer.parseInt(yearString);
        return year;
    }

    private void readBookData() {
        InputStream stream = getResources().openRawResource(R.raw.books);
        BufferedReader provider = new BufferedReader(new InputStreamReader(stream));
        String entry = "";
        try {
            while ((entry = provider.readLine()) != null) {
                // Split by ',' (CSV file)
                String[] attributes = entry.split(",");
                Log.v("Reading", attributes[1] + " " + attributes[2]);
                Book current = new Book(attributes[1], attributes[2], Double.parseDouble(attributes[3]), attributes[6], Integer.parseInt(attributes[7]), Integer.parseInt(attributes[8]), fixYear(attributes[10]));
                books.add(current);
            }

        }
        catch(IOException exception) {
            Log.wtf("Reading", "Error while reading data - line " + entry);

        }
    }
}