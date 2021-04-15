package com.example.appsforgood;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SuggestionActivity extends AppCompatActivity {

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
