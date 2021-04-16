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
import android.widget.TextView;

import java.util.ArrayList;

public class initialSurvey extends AppCompatActivity {

    //private Book b;
    private ArrayList<Book> correctLangBooks = new ArrayList<Book>();
    private ArrayList<Integer> bookScores = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_survey);
        //langFilterNew();
        //performDisplaySuggestions();
    }
    public void performDisplaySuggestions(View v) {
        EditText langText = findViewById(R.id.langEditText);
        String lang = langText.getText().toString();
        Log.v("Lang", "User Language: " + lang);

        for (Book b: Manager.getBooks()) {
            Log.v("Book", "All Book Titles: " + b.getTitle());
            if (b.getLanguage().equalsIgnoreCase(lang)) {
                Log.v("Book", "Book w/ Correct Language: " + b.getTitle());
            }
        }
        Intent start = new Intent(this, DisplayBooks.class);
        startActivity(start);
        //Log.v("Bye", "Bello");
    }

    /**public void langFilter() {
        Log.v("Bye", "Bello");
        final Manager manager = (Manager) getApplicationContext();
        EditText langText = findViewById(R.id.langEditText);
        String lang = langText.getText().toString();
        Log.v("Lang", "lang: " + lang);
        for (Book book : manager.getBooks()) {
            if (book.getLanguage().equalsIgnoreCase(lang))
                correctLangBooks.add(book);
        }
        for (Book entry : correctLangBooks) {
            Log.v("Final", entry.getLanguage());
        }
    }**/

    /**public void langFilterNew () {
    ((EditText)findViewById(R.id.langEditText)).setOnEditorActionListener(new EditText.OnEditorActionListener() {
        //@Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                if (event == null || !event.isShiftPressed()) {
                    // the user is done typing.
                    Log.v("Done", "User is done typing.");
                    return true; // consume.
                }
            }
            return false; // pass on to other listeners.
        } } );} }**/
}

