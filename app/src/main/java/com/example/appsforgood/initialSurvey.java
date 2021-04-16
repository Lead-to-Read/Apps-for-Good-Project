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

    //private Book b;
    private ArrayList<Book> correctLangBooks = new ArrayList<Book>();
    private ArrayList<Integer> bookScores = new ArrayList<Integer>();
    private int authorSubRating;
    private int authorRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_survey);
    }

    public void onContinueClick (View v) {
        //Make ArrayList, filtering out books with the incorrect language
        langFilter();
        authorScore();
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
            if (bookAuthor.contains(userAuthorNew)) {
                authorSubRating = 2;
            } else {
                authorSubRating = 1;
            }
            Log.v("Author", "Subrating: " + authorSubRating);

            //Get user author rating from slider and multiply by authorSubRating
            ProgressBar authorUserRanking = findViewById(R.id.authorRankingSlider);
            int authorUserRankingInt = authorUserRanking.getProgress();
            Log.v("Author", "Subrating: " + authorUserRankingInt);
            double authorRating = authorSubRating * authorUserRankingInt;
            Log.v("Author", "Author Score: " + authorRating);
        }
    }
}

