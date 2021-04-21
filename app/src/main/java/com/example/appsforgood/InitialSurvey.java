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

public class InitialSurvey extends AppCompatActivity {

    // Instance Variables
    private ArrayList<Book> correctLangBooks = new ArrayList<Book>();
    private ArrayList<Double> bookScores = new ArrayList<Double>();
    private String preferredLength;
    private int lowerPageBound;
    private int upperPageBound;
    private String preferredPubTime;
    private int lowerPubYear;
    private int upperPubYear;

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
     * @param v used to begin the algorithm
     */
    public void onContinueClick (View v) {
        //Make ArrayList, filtering out books with the incorrect language
        langFilter();
        authorScore();
        lengthScore();
        publicationDateScore();
        Intent start = new Intent(this, DisplayBooks.class);
        startActivity(start);
    }

    /**
     * Filters out books that are not the language specified by the user in the initial survey
     */
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

    /**
     * Assigns a rating based on the author of each book and the author/importance entered by the user
     */
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

    /**
     * Sets the page bounds for a short book (when user clicks short button)
     * @param v used to set page bounds for short books
     */
    public void shortLengthScore(View v) {
        preferredLength = "Short";
        lowerPageBound = 0;
        upperPageBound = 200;
        Log.v("Check", "Length " + preferredLength);
    }

    /**
     * Sets the page bounds for a medium book (when user clicks medium button)
     * @param v used to set page bounds for medium books
     */
    public void mediumLengthScore(View v) {
        preferredLength = "Medium";
        lowerPageBound = 201;
        upperPageBound = 500;
        Log.v("Check", "Length " + preferredLength);
    }

    /**
     * Sets the page bounds for a long book (when user clicks long button)
     * @param v used to set page bounds for long books
     */
    public void longLengthScore(View v) {
        preferredLength = "Long";
        lowerPageBound = 501;
        upperPageBound = 100000;// Safe upper bound for largest book
        Log.v("Check", "Length " + preferredLength);
    }

    /**
     * Assigns a rating to each book based on its length using the preferences specified by the user in the initial survey
     */
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
            double lengthRating = subLengthRating * lengthUserRankingInt;
            Log.v("CheckA", "Length" + subLengthRating);
            double currentTotalRating = bookScores.get(index);
            bookScores.set(index, currentTotalRating + lengthRating);
        }
        Log.v("Check", "LengthR");
	}

        /**
         * Sets the page bounds for a short book (when user clicks short button)
         * @param v used to set page bounds for short books
         */
        public void late1900sScore(View v) {
            preferredPubTime = "late 1900s";
            lowerPubYear = 1950;
            upperPubYear = 2000;
            Log.v("PubCheck", "Publication Time " + preferredPubTime);
        }

        /**
         * Sets the page bounds for a medium book (when user clicks medium button)
         * @param v used to set page bounds for medium books
         */
        public void modern2000sScore(View v) {
            preferredPubTime = "2000s";
            lowerPubYear = 2001;
            upperPubYear = 3000; //Safe upper publication year bound
            Log.v("PubCheck", "Publication Time " + preferredPubTime);
        }


        /**
         * Assigns a rating to each book based on its length using the preferences specified by the user in the initial survey
         */
        public void publicationDateScore() {
            for (int index = 0; index < correctLangBooks.size(); index++) {
                int currentPubYear = correctLangBooks.get(index).getYear();
                Log.v("PubCheck", "Publication of current book" + currentPubYear);
                double subTimeRating = 0;

                if (lowerPubYear <= currentPubYear && currentPubYear <= upperPubYear) {
                    subTimeRating = 1;
                } else if (lowerPubYear - 5 <= currentPubYear && currentPubYear <= upperPubYear + 5) {
                    subTimeRating = 0.75;
                } else if (lowerPubYear - 10 <= currentPubYear && currentPubYear <= upperPubYear + 10) {
                    subTimeRating = 0.5;
                } else if (lowerPubYear - 15 <= currentPubYear && currentPubYear <= upperPubYear + 15) {
                    subTimeRating = 0.25;
                } else {
                    subTimeRating = 0;
                }
                Log.v("PubCheck", "Subrating of current book" + correctLangBooks.get(index).getTitle() + subTimeRating);
                ProgressBar pubDateRanking = findViewById(R.id.publicationDateRankingSlider);
                int pubDateUserRanking = pubDateRanking.getProgress();
                double pubRanking = (subTimeRating * pubDateUserRanking);
                Log.v("PubCheck", "Final pub rating of current book" + correctLangBooks.get(index).getTitle() + pubRanking);
                double currentTotalRating = bookScores.get(index);
                bookScores.set(index, currentTotalRating + pubRanking);
                Log.v("PubCheck", "Final rating of current book" + bookScores.get(index));
            }
        }

    /**
     * Assigns a rating to each book based on the average rating of each book/the importance entered by the user
     */
    public void ratingScore() {
        for (int index = 0; index < correctLangBooks.size(); index++) {
            double bookRating = correctLangBooks.get(index).getAvgRating();
            double subAvgRatingRating = bookRating / 3.934; // 3.934 is the mean rating of all books in the dataset
            Log.v("AvgRating", "" + subAvgRatingRating);
            ProgressBar avgRatingUserRanking = findViewById(R.id.avgRatingRankingSlider);
            int avgRatingUserRankingInt = avgRatingUserRanking.getProgress();
            double avgRatingRating = subAvgRatingRating * avgRatingUserRankingInt;
            Log.v("Rating Score", "" + avgRatingRating);
            double currentTotalRating = bookScores.get(index);
            bookScores.set(index, currentTotalRating + avgRatingRating);
        }
        for (double score : bookScores) {
            Log.v("Score", "" + score);
        }
    }

    public void popularityScore() {

    }
}


