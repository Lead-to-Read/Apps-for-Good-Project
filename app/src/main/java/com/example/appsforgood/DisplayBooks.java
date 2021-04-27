package com.example.appsforgood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class DisplayBooks extends AppCompatActivity {

    /**
     * Displays the activity_display_books.xml activity
     * @param savedInstanceState Bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_books);
        getHighest();
    }

    public void getHighest () {
        double maxScore = InitialSurvey.getBookScores().get(0);
        //int i = 0;
        int correctIndex = 0;
        for (int i = 0; i < InitialSurvey.getBookScores().size(); i++) {
            if (InitialSurvey.getBookScores().get(i) > maxScore) {
                maxScore = InitialSurvey.getBookScores().get(i);
                correctIndex = i;
            }
        }
        Log.v("Highest Scoring Book","Index " + correctIndex); //~37
        Log.v("Highest Scoring Book", "Equals" + "correctLangBooks size:" + InitialSurvey.getCorrectLangBooks().size() + "bookScores size: " + InitialSurvey.getBookScores().size()); //46 if eng is chosen
        Log.v("Highest Scoring Book", "Highest Scoring Book" + InitialSurvey.getCorrectLangBooks().get(correctIndex).getTitle() + maxScore);
    }
}