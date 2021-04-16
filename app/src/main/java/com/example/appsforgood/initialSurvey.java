package com.example.appsforgood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class initialSurvey extends AppCompatActivity {

    // Instance Variables
    private ArrayList<Book> correctLangBooks = new ArrayList<Book>();
    private ArrayList<Double> bookScores = new ArrayList<Double>();
    private String preferredLength;
    private int lowerPageBound;
    private int upperPageBound;

    @Override
    /**
     * Displays activity from activity_initial_survey.xml
     * @param savedInstanceState used to open correct xml
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_survey);
    }

    /**
     * Calls all methods used after the initial survey is completed by the user
     * @param v used to begin the rating process / algorithm
     */
    public void onContinueClick (View v) {
        //Make ArrayList, filtering out books with the incorrect language
        langFilter();
        authorScore();
        lengthScore();
        Intent start = new Intent(this, DisplayBooks.class);
        startActivity(start);
    }

    public void langFilter() {
        EditText langText = findViewById(R.id.langEditText);
        String lang = langText.getText().toString();
        Log.v("Lang", "User Language: " + lang);

        for (Book b: Manager.getBooks()) {
            Log.v("Book", "All Book Titles: " + b.getTitle());
            if (b.getLanguage().equalsIgnoreCase(lang)) {
                correctLangBooks.add(b);
                Log.v("Book", "Book w/ Correct Language: " + b.getTitle());
            }
        }
        for (Book b: correctLangBooks) {
            Log.v("Book","Book in correctLangBooks" + b.getTitle());
        }
    }

    public void authorScore() {
        EditText authorText = findViewById(R.id.authorEditText);
        String userAuthor = authorText.getText().toString();
        Log.v("Author", "Preferred author: " + userAuthor);
        String userAuthorNew = userAuthor.replaceAll("\\s","");
        Log.v("Author", "Updated preferred author: " + userAuthorNew);

        for (Book b : correctLangBooks) {
            String bookAuthor = b.getAuthors().replaceAll("\\s", "");
            Log.v("Author", "Book author: " + bookAuthor);
            double authorSubRating;
            if (userAuthorNew.contains(bookAuthor)) {
                authorSubRating = 1;
            } else {
                authorSubRating = 0;
            }
            Log.v("Author", "Subrating: " + authorSubRating);

            //Get user author rating from slider and multiply by authorSubRating
            ProgressBar authorUserRanking = findViewById(R.id.authorRankingSlider);
            int authorUserRankingInt = authorUserRanking.getProgress();
            Log.v("Author", "Subrating: " + authorUserRankingInt);
            double authorRating = authorSubRating * authorUserRankingInt;
            bookScores.add(authorRating);
            Log.v("Author", "Author Score: " + authorRating);
        }
    }

    public void shortLengthScore(View v) {
        preferredLength = "Short";
        lowerPageBound = 0;
        upperPageBound = 200;
        Log.v("Check", "Length " + preferredLength);
    }

    public void mediumLengthScore(View v) {
        preferredLength = "Medium";
        lowerPageBound = 201;
        upperPageBound = 500;
    }

    public void longLengthScore(View v) {
        preferredLength = "Long";
        lowerPageBound = 501;
        upperPageBound = 100000; // Safe upper bound for largest book
    }

    public void lengthScore() {
        Log.v("Check", "Length Books " + correctLangBooks.size());
        for (int index = 0; index < correctLangBooks.size(); index++) {
            int currentPageLength = correctLangBooks.get(index).getNumPages();
            double subLengthRating = 0;
            Log.v("CheckD", "Length" + currentPageLength);
            if (lowerPageBound <= currentPageLength && currentPageLength <= upperPageBound) {
                subLengthRating = 1;
            } else if (lowerPageBound - 25 <= currentPageLength && currentPageLength <= upperPageBound + 25) {
                subLengthRating = 0.75;
            } else if (lowerPageBound - 50 <= currentPageLength && currentPageLength <= upperPageBound + 50) {
                subLengthRating = 0.5;
            } else if (lowerPageBound - 75 <= currentPageLength && currentPageLength <= upperPageBound + 75) {
                subLengthRating = 0.25;
            } else {
                subLengthRating = 0;
            }
            Log.v("Check", "Length" + subLengthRating);
            ProgressBar lengthUserRanking = findViewById(R.id.bookLengthRankingSlider);
            int lengthUserRankingInt = lengthUserRanking.getProgress();
            double lengthRating = (subLengthRating * lengthUserRankingInt);
            Log.v("CheckA", "Length" + subLengthRating);
            double currentTotalRating = bookScores.get(index);
            bookScores.set(index, currentTotalRating + lengthRating);
        }
        Log.v("Check", "LengthR");
	}

}