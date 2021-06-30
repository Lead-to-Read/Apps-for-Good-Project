package com.nextread.appsforgood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.text.TextWatcher;

import java.util.ArrayList;

/**
 * This class allows the user to fill out a survey about their book preferences, saves this data locally using Shared Preferences, and computes the score of each book using book data from Firebase.
 */
public class InitialSurvey extends AppCompatActivity {

    // Instance Variables
    private static ArrayList<BookScores> correctLangBooks = new ArrayList<>();
    private String preferredLength;
    private int lowerPageBound;
    private int upperPageBound;
    private int lowerPubYear;
    private int upperPubYear;
    public static boolean debug;

    //Input field variables on the activity_initial_survey.xml.
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

    //String name of the keys for the variables being saved from the activity_initial_survey.xml.
    public static final String SHARED_PREFERENCES= "sharedPreferences";
    public static final String LANG_CODE = "lang";
    public static final String AUTHOR = "favAuthor";
    public static final String AUTHOR_RATING = "authorRating";
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

    //Variables storing the user's input into the activity_initial_survey.xml.
    private String author;
    private String langCode;
    private int authorRating;
    private boolean shortLength;
    private boolean mediumLength;
    private boolean longLength;
    private int lengthRating;
    private boolean dateEarly1900s;
    private boolean dateLate1900s;
    private boolean date2000s;
    private int dateRating;
    private int avgRating;
    private int popularityRating;
    private boolean saveDataToggleBool;


    /**
     * Displays activity from activity_initial_survey.xml
     * @param savedInstanceState used to open correct xml
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        debug = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_survey);

        //Initializes each of the input field variables to the appropriate feature on the activity_initial_survey.xml.
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

        //Initializes the TextView object that checks if the inputted language code is valid to the appropriate feature on the activity_initial_survey.xml.
        TextView langCheckText = findViewById(R.id.langCheck);

        setUpHyperLink();
        loadData();
        updateViews();

        //This listener monitors changes in the language code text field.
        langText.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable arg0) {
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            //This method is responsible for checking if the inputted language code is in the dataset.
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                ArrayList<String> langCodes = new ArrayList<>();
                final Manager aManager = (Manager) getApplicationContext();
                for (Book b: aManager.getBooks()) {
                    langCodes.add(b.getLanguage()); }
                    if (langCodes.contains(langText.getText().toString()) || langText.getText().toString().equals("en-US") || langText.getText().toString().equals("en-GB") || langText.getText().toString().equals("eng"))  {
                        String correctLangText = "The language code you inputted is in the dataset.";
                        langCheckText.setText(correctLangText);
                        langCheckText.setTextColor(Color.parseColor("#09349D"));
                    } else {
                        String incorrectLangText = "The language code you inputted is not in the dataset.";
                        langCheckText.setText(incorrectLangText);
                        //Changes the color of the text to red if the code is not valid.
                        langCheckText.setTextColor(Color.parseColor("#FFB00020"));
                    }
                }
            });
    }

    //If saveData is checked and continue is clicked...

    /**
     * Saves the data the user inputs into the initial survey if the ToggleButton is on
     */
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
    }

    /**
     * Sets the variables storing the user's input based on the data saved locally from when the user filled out the survey previously
     */
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);

        langCode = sharedPreferences.getString(LANG_CODE,"");
        author = sharedPreferences.getString(AUTHOR,"");
        authorRating = sharedPreferences.getInt(AUTHOR_RATING,0);
        shortLength = sharedPreferences.getBoolean(SHORT_LENGTH, false);
        mediumLength = sharedPreferences.getBoolean(MEDIUM_LENGTH, false);
        longLength = sharedPreferences.getBoolean(LONG_LENGTH, false);
        lengthRating = sharedPreferences.getInt(LENGTH_RATING,0);
        dateEarly1900s = sharedPreferences.getBoolean(DATE_EARLY_1900s, false);
        dateLate1900s = sharedPreferences.getBoolean(DATE_LATE_1900s, false);
        date2000s = sharedPreferences.getBoolean(DATE_2000s, false);
        dateRating = sharedPreferences.getInt(DATE_RATING,0);
        avgRating = sharedPreferences.getInt(AVG_RATING,0);
        popularityRating = sharedPreferences.getInt(POPULARITY_RATING,0);
        saveDataToggleBool = sharedPreferences.getBoolean(SAVE_DATA_TOGGLE, false);
    }

    /**
     * Updates and fills in the initial survey based on the data saved locally from when the user filled out the survey previously
     */
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
     * Calls all methods used after the initial survey is completed by the user to begin the algorithm
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
    }

    /**
     * Filters out books that are not the language specified by the user in the initial survey
     */
    public void langFilter() {
        String lang = langText.getText().toString();
        if (debug)
            Log.v("Inputted Language", " " + lang);
        final Manager aManager = (Manager) getApplicationContext();
        //Loops through the ArrayList of Book objects from Firebase and adds those with the correct language code to a new ArrayList, which will be used for the algorithm.
        for (Book b: aManager.getBooks()) {
            if (debug) {
                Log.v("Book", "All Book Languages: " + b.getLanguage());
                Log.v("LangDescriptor", "Language: " + b.getLanguage()); }
            if (b.getLanguage().equalsIgnoreCase(lang)) {
                BookScores currentBook = new BookScores(b, 0.0);
                if (debug)
                    Log.v("Book", "Book Title: " + currentBook.getBook().getTitle());
                correctLangBooks.add(currentBook);
            }
        }
    }

    /**
     * Assigns a rating based on the author of each book and the author importance entered by the user
     */
    public void authorScore() {
        String userAuthor = authorText.getText().toString();
        //Removes spaces from the user's inputted preferred author
        String userAuthorNew = userAuthor.replaceAll("\\s","");
        if (debug) {
            Log.v("Author", "Inputted Author: " + userAuthor);
            Log.v("Author", "Updated Inputted Author: " + userAuthorNew); }

        for (BookScores b : correctLangBooks) {
            //Removes spaces from Book object's author
            String bookAuthor = b.getBook().getAuthors().replaceAll("\\s", "");
            double authorSubRating;
            //.contains() is normally case-sensitive.
            if (bookAuthor.toLowerCase().contains(userAuthorNew.toLowerCase())) {
                authorSubRating = 1;
            } else {
                authorSubRating = 0;
            }

            if (debug)
                Log.v("Author", "Title: " + b.getBook().getTitle() + "Author Subrating: " + authorSubRating);

            //Get user author rating from slider and multiplies it by authorSubRating
            int authorUserRankingInt = authorUserRanking.getProgress();
            double authorRating = authorSubRating * authorUserRankingInt;
            b.setScore(authorRating);
            if (debug)
                Log.v("Author", "Author Final Score: " + authorRating);
        }
    }

    /**
     * Assigns a rating to each book based on its length using the preferences specified by the user in the initial survey and the established lower and upper page bound
     */
    public void lengthScore() {
        //Short books have a length between 0 and 200.
        if (shortLength) {

            lowerPageBound = 0;
            upperPageBound = 200;
            if (debug) {
                preferredLength = "Short";
                Log.v("Length", "Length Check: " + preferredLength + " " + lowerPageBound + " " + upperPageBound);
            }
        }

        //Medium books have a length between 201 and 500.
        else if (mediumLength) {

            lowerPageBound = 201;
            upperPageBound = 500;
            if (debug) {
                preferredLength = "Medium";
                Log.v("Length", "Length Check: " + preferredLength + " " + lowerPageBound + " " + upperPageBound);
            }
            }

        //Long books have a length between 501 and 100,000.
        else if (longLength) {

            lowerPageBound = 501;
            upperPageBound = 100000;// Safe upper bound for largest book
            if (debug) {
                preferredLength = "Long";
                Log.v("Length", "Length Check: " + preferredLength + " " + lowerPageBound + " " + upperPageBound);
            }
            }

        for (int index = 0; index < correctLangBooks.size(); index++) {
            int currentPageLength = correctLangBooks.get(index).getBook().getNumPages();
            double subLengthRating;
            //Book's number of pages is within bounds based on user's preference:
            if (lowerPageBound <= currentPageLength && currentPageLength <= upperPageBound) {
                subLengthRating = 1;
            }
            //Book's number of pages is within 25 pages of bounds based on user's preference:
            else if (lowerPageBound - 25 <= currentPageLength && currentPageLength <= upperPageBound + 25) {
                subLengthRating = 0.75;
            }
            //Book's number of pages is within 50 pages of bounds based on user's preference:
            else if (lowerPageBound - 50 <= currentPageLength && currentPageLength <= upperPageBound + 50) {
                subLengthRating = 0.5;
            }
            //Book's number of pages is within 75 pages of bounds based on user's preference:
            else if (lowerPageBound - 75 <= currentPageLength && currentPageLength <= upperPageBound + 75) {
                subLengthRating = 0.25;
            } else {
                subLengthRating = 0;
            }

            int lengthUserRankingInt = lengthUserRanking.getProgress();
            double lengthRating = subLengthRating * lengthUserRankingInt;
            if (debug)
                Log.v("CheckA", "Length" + subLengthRating);
            double currentTotalRating = correctLangBooks.get(index).getScore();
            correctLangBooks.get(index).setScore(currentTotalRating + lengthRating);
        }
        if (debug) {
            for (BookScores b : correctLangBooks) {
                Log.v("After Length", "Title: " + b.getBook().getTitle() + " Score: " + b.getScore() + " Pages: " + b.getBook().getNumPages() + lowerPageBound);
            }
        }
	}

	/**
     * Assigns a rating to each book based on its publication date using the preferences specified by the user in the initial survey
     */
    public void publicationDateScore() {
        //If user preference is early 1900s, set lower bound to 1900 and upper bound to 1949.
        if (dateEarly1900s) {
            lowerPubYear = 1900;
            upperPubYear = 1949;
        }
        //If user preference is late 1900s, set lower bound to 1950 and upper bound to 1999.
        if (dateLate1900s) {
            lowerPubYear = 1950;
            upperPubYear = 1999;
        }
        //If user preference is 2000s, set lower bound to 2000 and upper bound to 2999.
        if (date2000s) {
            lowerPubYear = 2000;
            upperPubYear = 2999; // future year
        }
        for (int index = 0; index < correctLangBooks.size(); index++) {
            int currentPubYear = correctLangBooks.get(index).getBook().getYear();
            double subTimeRating;

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
            int pubDateUserRanking = pubDateRanking.getProgress();
            double pubRanking = (subTimeRating * pubDateUserRanking);
            if (debug)
                Log.v("PubCheck", "Final pub rating of current book" + correctLangBooks.get(index).getBook().getTitle() + pubRanking);
            double currentTotalRating = correctLangBooks.get(index).getScore();
            correctLangBooks.get(index).setScore(currentTotalRating + pubRanking);
        }
    }

    /**
     * Assigns a rating to each book based on the average rating of each book/the importance entered by the user in the initial survey
     */
    public void ratingScore() {
        for (int index = 0; index < correctLangBooks.size(); index++) {
            double bookRating = correctLangBooks.get(index).getBook().getAvgRating();
            double subAvgRatingRating = (bookRating / 3.934) + 0.35 * (bookRating - 3.934); // 3.934 is the mean rating of all books in the dataset, while 0.35 is the standard deviation. The standard deviation is multiplied instead of divided since it's less than 1.
            int avgRatingUserRankingInt = avgRatingUserRanking.getProgress();
            double avgRatingRating = subAvgRatingRating * avgRatingUserRankingInt;
            if (debug)
                Log.v("Rating Score", "" + avgRatingRating);
            double currentTotalRating = correctLangBooks.get(index).getScore();
            correctLangBooks.get(index).setScore(currentTotalRating + avgRatingRating);
        }
    }

    /**
     * Assigns a rating to each book based on the popularity of each book and the importance entered by the user in the initial survey
     */
    public void popularityScore() {
        int index;
        for (index = 0; index < correctLangBooks.size(); index++) {
            double bookPopularity = correctLangBooks.get(index).getBook().getRatingsCount();
            double subPopularityRating;

            if (bookPopularity > 4993.5) { //4993.5 is Q3 of the dataset, which was skewed right
                subPopularityRating = 1;
            }
            else {
                subPopularityRating = bookPopularity / 4993.5;
            }
            int popRankingInt = popRanking.getProgress();
            double popularityRating = subPopularityRating * popRankingInt;
            if (debug)
                Log.v("Popularity", "Popularity final score " + popularityRating);
            double currentTotalRating = correctLangBooks.get(index).getScore();
            correctLangBooks.get(index).setScore(currentTotalRating + popularityRating);
        }
    }

    /**
     * Gets the ArrayList of Book objects with the correct language code
     * @return ArrayList of Book objects with the correct language code
     */
    public static ArrayList<BookScores> getCorrectLangBooks() {
        return correctLangBooks;
    }

    /**
     * Clears the correctLangBooks ArrayList
     */
    public static void clearCorrectLangBooks () {
        correctLangBooks.clear();
    }
}


