package com.nextread.appsforgood;

import  androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;

/**
 * This class is responsible for displaying the activity_main.xml and the corresponding buttons.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Starts activity
     * @param savedInstanceState used to call superclass onCreate()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpHyperLink();
    }

    /**
     * Called when user starts the app
     */
    @Override
    protected void onStart() {
        super.onStart();

        //final Manager aManager = (Manager) getApplicationContext();

        //Populates Firebase
        /* FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference entry = database.getReference("books");

        for(int a = 0; a < aManager.getBooks().size(); a++) {
            entry.push().setValue(aManager.getBooks().get(a));
        } */
    }

    /*/**
     * Extracts the year of a book from a full mm/dd/yyyy date
     * @param start the string containing the month, date, and year
     * @return the year of publication of the book
     */
    /*private int fixYear(String start) {
        int endMonth = start.indexOf("/");
        String middle = start.substring(endMonth + 1);
        int endDay = middle.indexOf("/");
        String yearString = middle.substring(endDay + 1, endDay + 5);
        int year = Integer.parseInt(yearString);
        return year;
    }*/

    /*/**
     * Reads data from .csv file, but now updated to Firebase and no longer used
     */
    /*private void readBookDataFile() {
        InputStream stream = getResources().openRawResource(R.raw.books);
        BufferedReader provider = new BufferedReader(new InputStreamReader(stream));

        final Manager manager = (Manager) getApplicationContext();

        String entry = "";
        try {
            while ((entry = provider.readLine()) != null) {
                // Split by ',' (CSV file)
                String[] attributes = entry.split(",");
                Book current = new Book(attributes[1], attributes[2], Double.parseDouble(attributes[3]), attributes[5], attributes[6], Integer.parseInt(attributes[7]), Integer.parseInt(attributes[8]), fixYear(attributes[10])); // all necessary factors
                manager.addBook(current);
            }
        }
        catch(IOException exception) {
            Log.wtf("Reading", "Error while reading data - line " + entry);
        }
    }*/

    /**
     * Starts suggestion process
     * @param v used to begin the suggestion process
     */
    public void performStartSuggestions(View v) {
        Intent start = new Intent(this, InitialSurvey.class);
        startActivity(start);
    }

    /**
     * Sets up hyperlink for feedback button
     */
    private void setUpHyperLink() {
        Button feedbackButton = findViewById(R.id.feedbackButton);
        feedbackButton.setMovementMethod(LinkMovementMethod.getInstance());
    }
}