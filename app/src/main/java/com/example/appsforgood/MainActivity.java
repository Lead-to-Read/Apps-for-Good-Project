package com.example.appsforgood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

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

    private void readBookData() {
        InputStream stream = getResources().openRawResource(R.raw.books);
        BufferedReader provider = new BufferedReader(new InputStreamReader(stream));
        String entry = "";
        try {
            while ((entry = provider.readLine()) != null) {
                // Split by ',' (CSV file)
                String[] attributes = entry.split(",");
                Book current = new Book(attributes[1], attributes[2], attributes[3], attributes[6], attributes[7], attributes[8], attributes[10]);
            }

        }
        catch(IOEException exception) {

        }
    }
}