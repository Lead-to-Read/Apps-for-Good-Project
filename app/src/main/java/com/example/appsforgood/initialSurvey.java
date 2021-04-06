package com.example.appsforgood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;

public class initialSurvey extends AppCompatActivity {

    private static final String[] languages = new String[]{
           "English", "French", "Spanish"
    };

    private MultiAutoCompleteTextView editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_survey);

        editText = findViewById(R.id.langAutoComplete);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, languages);
        editText.setAdapter(adapter);
        editText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

}