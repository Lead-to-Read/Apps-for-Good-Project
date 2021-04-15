package com.example.appsforgood;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SuggestionActivity extends AppCompatActivity {

    private Book b;

    @Override
    /**
     * Open survey at proper time
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_survey);

        final Manager manager = (Manager) getApplicationContext();
    }



}
