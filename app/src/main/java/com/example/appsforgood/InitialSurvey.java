package com.example.appsforgood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class InitialSurvey extends AppCompatActivity {

    // Instance Variables
    //private static ArrayList<Book> correctLangBooks = new ArrayList<Book>();
    //private static ArrayList<Double> bookScores = new ArrayList<Double>();
    private static ArrayList<BookScores> correctLangBooks = new ArrayList<BookScores>();
    private String preferredLength;
    private int lowerPageBound;
    private int upperPageBound;
    private String preferredPubTime;
    private int lowerPubYear;
    private int upperPubYear;


    ToggleButton saveDataToggleButton;
    EditText langText;
    EditText authorText;
    ProgressBar authorUserRanking;
    ProgressBar lengthUserRanking;
    ProgressBar pubDateRanking;
    ProgressBar avgRatingUserRanking;
    ProgressBar popRanking;
    RadioButton shortButton;
    RadioButton mediumButton;
    RadioButton longButton;
    RadioButton dateEarly1900sButton;
    RadioButton dateLate1900sButton;
    RadioButton date2000sButton;

    public static final String SHARED_PREFERENCES= "sharedPreferences";
    public static final String LANG_CODE= "langCode";
    public static final String AUTHOR = "favAuthor";
    public static final String AUTHOR_RATING = "authorRating";
    //public static final String LENGTH = "length";
    public static final String SHORT_LENGTH = "shortLength";
    public static final String MEDIUM_LENGTH = "mediumLength";
    public static final String LONG_LENGTH = "longLength";
    public static final String LENGTH_RATING = "lengthRating";
    public static final String DATE_EARLY_1900s = "dateEarly1900s";
    public static final String DATE_LATE_1900s = "dateLate1900s";
    public static final String DATE_2000s = "date2000s";
    public static final String DATE_RATING = "dateRating";
    public static final String AVG_RATING = "avgRating";
    public static final String POPULARITY_RATING = "popularityRating";
    public static final String SAVE_DATA_TOGGLE = "saveDataToggle";


    private String langCode;
    private String author;
    private int authorRating;
    //private String length;
    private boolean shortLength;
    private boolean mediumLength;
    private boolean longLength;
    private int lengthRating;
    //private String date;
    private boolean dateEarly1900s;
    private boolean dateLate1900s;
    private boolean date2000s;
    private int dateRating;
    private int avgRating;
    private int popularityRating;
    private boolean saveDataToggleBool;

    @Override
    /**
     * Displays activity from activity_initial_survey.xml
     * @param savedInstanceState used to open correct xml
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_survey);


        //Log.v("SHAREDPREF","Initial Survey runs");
        saveDataToggleButton = (ToggleButton) findViewById(R.id.saveDataToggle);
        langText = (EditText) findViewById(R.id.langEditText);
        authorText = findViewById(R.id.authorEditText);
        authorUserRanking = findViewById(R.id.authorRankingSlider);
        lengthUserRanking = findViewById(R.id.bookLengthRankingSlider);
        pubDateRanking = findViewById(R.id.publicationDateRankingSlider);
        avgRatingUserRanking = findViewById(R.id.avgRatingRankingSlider);
        popRanking = findViewById(R.id.popularityRankingSlider);
        shortButton = findViewById(R.id.shortLengthButton);
        mediumButton = findViewById(R.id.mediumLengthButton);
        longButton = findViewById(R.id.longLengthButton);
        dateEarly1900sButton = findViewById(R.id.early1900sOption);
        dateLate1900sButton = findViewById(R.id.late1900sOption);
        date2000sButton = findViewById(R.id.modern2000sOption);


        setUpHyperLink();
        loadData();
        updateViews();
    }

    //If saveData is checked and continue is clicked...
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(LANG_CODE, langText.getText().toString());
        editor.putString(AUTHOR, authorText.getText().toString());
        editor.putInt(AUTHOR_RATING, authorUserRanking.getProgress());
        editor.putBoolean(SHORT_LENGTH, shortButton.isChecked());
        editor.putBoolean(MEDIUM_LENGTH, mediumButton.isChecked());
        editor.putBoolean(LONG_LENGTH, longButton.isChecked());
        editor.putInt(LENGTH_RATING, lengthUserRanking.getProgress());
        editor.putBoolean(DATE_EARLY_1900s, dateEarly1900sButton.isChecked());
        editor.putBoolean(DATE_LATE_1900s, dateLate1900sButton.isChecked());
        editor.putBoolean(DATE_2000s, date2000sButton.isChecked());
        editor.putInt(DATE_RATING, pubDateRanking.getProgress());
        editor.putInt(AVG_RATING, avgRatingUserRanking.getProgress());
        editor.putInt(POPULARITY_RATING, popRanking.getProgress());
        editor.putBoolean(SAVE_DATA_TOGGLE, saveDataToggleButton.isChecked());

        editor.apply();
        //Log.v("Shared preferences", "Data saved");
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);

        langCode = sharedPreferences.getString(LANG_CODE,"");
        //Log.v("Shared preferences","Lang: " + langCode);
        author = sharedPreferences.getString(AUTHOR,"");
        //Log.v("Shared preferences","Author: " + author);
        authorRating = sharedPreferences.getInt(AUTHOR_RATING,0);
        //length = sharedPreferences.getString(LENGTH,"");
        shortLength = sharedPreferences.getBoolean(SHORT_LENGTH, false);
        mediumLength = sharedPreferences.getBoolean(MEDIUM_LENGTH, false);
        longLength = sharedPreferences.getBoolean(LONG_LENGTH, false);
        lengthRating = sharedPreferences.getInt(LENGTH_RATING,0);
        //date = sharedPreferences.getString(DATE,"");
        dateEarly1900s = sharedPreferences.getBoolean(DATE_EARLY_1900s, false);
        dateLate1900s = sharedPreferences.getBoolean(DATE_LATE_1900s, false);
        date2000s = sharedPreferences.getBoolean(DATE_2000s, false);
        dateRating = sharedPreferences.getInt(DATE_RATING,0);
        avgRating = sharedPreferences.getInt(AVG_RATING,0);
        popularityRating = sharedPreferences.getInt(POPULARITY_RATING,0);
        saveDataToggleBool = sharedPreferences.getBoolean(SAVE_DATA_TOGGLE, false);
    }

    public void updateViews() {
        langText.setText(langCode);
        authorText.setText(author);
        authorUserRanking.setProgress(authorRating);
        shortButton.setChecked(shortLength);
        mediumButton.setChecked(mediumLength);
        longButton.setChecked(longLength);
        lengthUserRanking.setProgress(lengthRating);
        pubDateRanking.setProgress(dateRating);
        dateEarly1900sButton.setChecked(dateEarly1900s);
        dateLate1900sButton.setChecked(dateLate1900s);
        date2000sButton.setChecked(date2000s);
        avgRatingUserRanking.setProgress(avgRating);
        popRanking.setProgress(popularityRating);
        saveDataToggleButton.setChecked(saveDataToggleBool);
    }

    /**
     * Sets up hyperlink for langQuestion
     */
    private void setUpHyperLink() {
        TextView linkTextView = findViewById(R.id.langQuestion);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
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
        ratingScore();
        popularityScore();
        if (saveDataToggleButton.isChecked()) {
            saveData(); }
        Intent start = new Intent(this, DisplayBooks.class);
        startActivity(start);
        //saveDataToggleButton = (ToggleButton) findViewById(R.id.saveDataToggle);
    }

    /**
     * Filters out books that are not the language specified by the user in the initial survey
     */
    public void langFilter() {
        //EditText langText = findViewById(R.id.langEditText);
        String lang = langText.getText().toString();
        Log.d("LangRef", "" + lang);
        //Log.v("Lang", "User Language: " + lang);
        final Manager aManager = (Manager) getApplicationContext();
        for (Book b: aManager.getBooks()) {
            //Log.v("Book", "All Book Languages: " + b.getLanguage());
            //Log.v("LangDescriptor", "Language: " + b.getLanguage());
            if (b.getLanguage().equalsIgnoreCase(lang)) {
                BookScores currentBook = new BookScores(b, 0.0);
                //Log.v("Book", "Book Title: " + currentBook.getBook().getTitle());
                correctLangBooks.add(currentBook);
                //Log.d("LangBook", currentBook.getBook().getTitle());
                //Log.v("Book", "Book w/ Correct Language: " + b.getTitle());
            }
        }
        /*for (BookScores b: correctLangBooks) {
            Log.v("Book","Book in correctLangBooks" + b.getBook().getTitle());
        } */
    }

    /**
     * Assigns a rating based on the author of each book and the author/importance entered by the user
     */
    public void authorScore() {
        Log.v("Size", "" + correctLangBooks.size());
        //EditText authorText = findViewById(R.id.authorEditText);
        String userAuthor = authorText.getText().toString();
        //Log.v("Author", "Preferred author: " + userAuthor);
        String userAuthorNew = userAuthor.replaceAll("\\s","");
        //Log.v("Author", "Updated preferred author: " + userAuthorNew);

        for (BookScores b : correctLangBooks) {
            String bookAuthor = b.getBook().getAuthors().replaceAll("\\s", "");
            //Log.v("Author", "Book author: " + bookAuthor);
            double authorSubRating;
            //.contains() is normally case-sensitive.
            if (bookAuthor.toLowerCase().contains(userAuthorNew.toLowerCase())) {
                authorSubRating = 1;
            } else {
                authorSubRating = 0;
            }
            Log.v("Author", "Title: " + b.getBook().getTitle() + "Subrating: " + authorSubRating);

            //Get user author rating from slider and multiply by authorSubRating
            //ProgressBar authorUserRanking = findViewById(R.id.authorRankingSlider);
            int authorUserRankingInt = authorUserRanking.getProgress();
            //Log.v("Author", "Subrating: " + authorUserRankingInt);
            double authorRating = authorSubRating * authorUserRankingInt;
            b.setScore(authorRating);
            //Log.v("Author", "Author Score: " + authorRating);
        }
    }

    /*
     * Sets the page bounds for a short book (when user clicks short button)
     * @param v used to set page bounds for short books
     */
    /*public void shortLengthScore(View v) {
        preferredLength = "Short";
        lowerPageBound = 0;
        upperPageBound = 200;
        Log.v("Check", "Length " + preferredLength + " " + lowerPageBound + " " + upperPageBound);
    }
     */
    /**
     * Sets the page bounds for a medium book (when user clicks medium button)
     * @param v used to set page bounds for medium books
     */
    /*public void mediumLengthScore(View v) {
        preferredLength = "Medium";
        lowerPageBound = 201;
        upperPageBound = 500;
        Log.v("Check", "Length " + preferredLength + " " + lowerPageBound + " " + upperPageBound);
    }
*/
    /**
     * Sets the page bounds for a long book (when user clicks long button)
     * @param v used to set page bounds for long books
     */
    /*public void longLengthScore(View v) {
        preferredLength = "Long";
        lowerPageBound = 501;
        upperPageBound = 100000;// Safe upper bound for largest book
        Log.v("Check", "Length " + preferredLength + " " + lowerPageBound + " " + upperPageBound);
    } */

    /**
     * Assigns a rating to each book based on its length using the preferences specified by the user in the initial survey
     */
    public void lengthScore() {
        if (shortLength) {
            preferredLength = "Short";
            lowerPageBound = 0;
            upperPageBound = 200;
            Log.v("Check", "Length " + preferredLength + " " + lowerPageBound + " " + upperPageBound);
        }

        else if (mediumLength) {
            preferredLength = "Medium";
            lowerPageBound = 201;
            upperPageBound = 500;
            Log.v("Check", "Length " + preferredLength + " " + lowerPageBound + " " + upperPageBound);
        }

        else if (longLength) {
            preferredLength = "Long";
            lowerPageBound = 501;
            upperPageBound = 100000;// Safe upper bound for largest book
            Log.v("Check", "Length " + preferredLength + " " + lowerPageBound + " " + upperPageBound);
        }

        //Log.v("Check", "Length Books " + correctLangBooks.size());
        for (int index = 0; index < correctLangBooks.size(); index++) {
            int currentPageLength = correctLangBooks.get(index).getBook().getNumPages();
            double subLengthRating = 0;
            //Log.v("CheckD", "Length" + currentPageLength);
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
            //Log.v("Check", "Length" + subLengthRating);
            //ProgressBar lengthUserRanking = findViewById(R.id.bookLengthRankingSlider);
            int lengthUserRankingInt = lengthUserRanking.getProgress();
            double lengthRating = subLengthRating * lengthUserRankingInt;
            //Log.v("CheckA", "Length" + subLengthRating);
            double currentTotalRating = correctLangBooks.get(index).getScore();
            correctLangBooks.get(index).setScore(currentTotalRating + lengthRating);
        }
        for (BookScores b : correctLangBooks) {
            Log.v("After Length", "Title: " + b.getBook().getTitle() + " Score: " + b.getScore() + " Pages: " + b.getBook().getNumPages() + lowerPageBound);
        }
        //Log.v("Check", "LengthR");
	}

	/**
     * Assigns a rating to each book based on its length using the preferences specified by the user in the initial survey
     */
    public void publicationDateScore() {
        if (dateEarly1900s) {
            preferredPubTime = "early 1900s";
            lowerPubYear = 1900;
            upperPubYear = 1949;
        }
        if (dateLate1900s) {
            preferredPubTime = "late 1900s";
            lowerPubYear = 1950;
            upperPubYear = 1999;
        }
        if (date2000s) {
            preferredPubTime = "2000s";
            lowerPubYear = 2000;
            upperPubYear = 5000; // future year
        }
        for (int index = 0; index < correctLangBooks.size(); index++) {
            int currentPubYear = correctLangBooks.get(index).getBook().getYear();
            //Log.v("PubCheck", "Publication of current book" + currentPubYear);
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
            //Log.v("PubCheck", "Subrating of current book" + correctLangBooks.get(index).getBook().getTitle() + subTimeRating);
            //ProgressBar pubDateRanking = findViewById(R.id.publicationDateRankingSlider);
            int pubDateUserRanking = pubDateRanking.getProgress();
            double pubRanking = (subTimeRating * pubDateUserRanking);
            //Log.v("PubCheck", "Final pub rating of current book" + correctLangBooks.get(index).getBook().getTitle() + pubRanking);
            double currentTotalRating = correctLangBooks.get(index).getScore();
            correctLangBooks.get(index).setScore(currentTotalRating + pubRanking);
            //Log.v("PubCheck", "Final rating of current book" + correctLangBooks.get(index).getScore());
        }
    }

    /**
     * Assigns a rating to each book based on the average rating of each book/the importance entered by the user
     */
    public void ratingScore() {
        for (int index = 0; index < correctLangBooks.size(); index++) {
            double bookRating = correctLangBooks.get(index).getBook().getAvgRating();
            double subAvgRatingRating = (bookRating / 3.934) + 0.35 * (bookRating - 3.934); // 3.934 is the mean rating of all books in the dataset, while 0.35 is the standard deviation. The standard deviation is multiplied instead of divided since it's less than 1
            //Log.v("AvgRating", "" + subAvgRatingRating);
            //ProgressBar avgRatingUserRanking = findViewById(R.id.avgRatingRankingSlider);
            int avgRatingUserRankingInt = avgRatingUserRanking.getProgress();
            double avgRatingRating = subAvgRatingRating * avgRatingUserRankingInt;
            //Log.v("Rating Score", "" + avgRatingRating);
            double currentTotalRating = correctLangBooks.get(index).getScore();
            correctLangBooks.get(index).setScore(currentTotalRating + avgRatingRating);
        }
        //for (BookScores book : correctLangBooks) {
            //Log.v("Score", "" + book.getScore());
        //}
    }

    public void popularityScore() {
        int index;
        for (index = 0; index < correctLangBooks.size(); index++) {
            double bookPopularity = correctLangBooks.get(index).getBook().getRatingsCount();
            double subPopularityRating;
            /**if (bookPopularity > 12327.75) { //12327.75 is the minimum to be an outlier in the dataset, which was skewed right
                subPopularityRating = 1;
            }**/
            if (bookPopularity > 4993.5) { //4993.5 is Q3 of the dataset, which was skewed right
                subPopularityRating = 1;
            }
            else {
                //subPopularityRating = bookPopularity / 12327.75;
                subPopularityRating = bookPopularity / 4993.5;
            }
            //Log.v("Popularity", "subPopRanking " + subPopularityRating);
            //ProgressBar popRanking = findViewById(R.id.popularityRankingSlider);
            int popRankingInt = popRanking.getProgress();
            double popularityRating = subPopularityRating * popRankingInt;
            //Log.v("Popularity", "Popularity final score " + popularityRating);
            double currentTotalRating = correctLangBooks.get(index).getScore();
            correctLangBooks.get(index).setScore(currentTotalRating + popularityRating);
        }
        //for (BookScores book : correctLangBooks) {
        //Log.v("Score", "" + book.getScore());
        //}
    }

    /*public void getHighest () {
        Log.v("LengthCorrectLang", "" + correctLangBooks.size());
        double maxScore = correctLangBooks.get(0).getScore();
        //int i = 0;
        int correctIndex = 0;
        for (int i = 0; i < correctLangBooks.size(); i++) {
           if (correctLangBooks.get(i).getScore() > maxScore) {
               maxScore = correctLangBooks.get(i).getScore();
               correctIndex = i;
           }
        }
        //Log.v("Highest Scoring Book","Index " + correctIndex); //~37
        //Log.v("Highest Scoring Book", "Equals" + "correctLangBooks size:" + correctLangBooks.size() + "bookScores size: " + correctLangBooks.size()); //46 if eng is chosen
        //Log.v("Highest Scoring Book", "Highest Scoring Book" + correctLangBooks.get(correctIndex).getBook().getTitle() + maxScore);
    } */

    public static ArrayList<BookScores> getCorrectLangBooks() {
        return correctLangBooks;
    }
}


