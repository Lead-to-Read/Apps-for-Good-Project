package com.example.appsforgood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * This class displays the five highest-rated books in a table to the user.
 */
public class DisplayBooks extends AppCompatActivity {

    /**
     * Displays the activity_display_books.xml or activity_no_lang.xml activity
     * @param savedInstanceState Bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //If the size of the correctLangBooks ArrayList is zero, activity_no_lang.xml is displayed.
        if (InitialSurvey.getCorrectLangBooks().size() == 0) {
            setContentView(R.layout.activity_no_lang);
        }

        //If the size of the correctLangBooks ArrayList is not zero, activity_display_books.xml is displayed.
        else {
            setContentView(R.layout.activity_display_books);
            sortCorrectLangBooks();

            TextView title1 = (TextView) findViewById(R.id.suggestion1Title);
            title1.setText((InitialSurvey.getCorrectLangBooks()).get(0).getBook().getTitle());

            TextView author1 = (TextView) findViewById(R.id.suggestion1Author);
            author1.setText((InitialSurvey.getCorrectLangBooks()).get(0).getBook().getAuthors());

            TextView isbn1 = (TextView) findViewById(R.id.suggestion1ISBN);
            isbn1.setText((InitialSurvey.getCorrectLangBooks()).get(0).getBook().getISBN13());

            if (InitialSurvey.debug)
                Log.v("Testing Ranking", "First Score: " + InitialSurvey.getCorrectLangBooks().get(0).getScore());

            TextView title2 = (TextView) findViewById(R.id.suggestion2Title);
            title2.setText((InitialSurvey.getCorrectLangBooks()).get(1).getBook().getTitle());

            TextView author2 = (TextView) findViewById(R.id.suggestion2Author);
            author2.setText((InitialSurvey.getCorrectLangBooks()).get(1).getBook().getAuthors());

            TextView isbn2 = (TextView) findViewById(R.id.suggestion2ISBN);
            isbn2.setText((InitialSurvey.getCorrectLangBooks()).get(1).getBook().getISBN13());

            if (InitialSurvey.debug)
                Log.v("Testing Ranking", "Second Score: " + InitialSurvey.getCorrectLangBooks().get(1).getScore());

            TextView title3 = (TextView) findViewById(R.id.suggestion3Title);
            title3.setText((InitialSurvey.getCorrectLangBooks()).get(2).getBook().getTitle());

            TextView author3 = (TextView) findViewById(R.id.suggestion3Author);
            author3.setText((InitialSurvey.getCorrectLangBooks()).get(2).getBook().getAuthors());

            TextView isbn3 = (TextView) findViewById(R.id.suggestion3ISBN);
            isbn3.setText((InitialSurvey.getCorrectLangBooks()).get(2).getBook().getISBN13());

            if (InitialSurvey.debug)
                Log.v("Testing Ranking", "Third Score: " + InitialSurvey.getCorrectLangBooks().get(2).getScore());

            TextView title4 = (TextView) findViewById(R.id.suggestion4Title);
            title4.setText((InitialSurvey.getCorrectLangBooks()).get(3).getBook().getTitle());

            TextView author4 = (TextView) findViewById(R.id.suggestion4Author);
            author4.setText((InitialSurvey.getCorrectLangBooks()).get(3).getBook().getAuthors());

            TextView isbn4 = (TextView) findViewById(R.id.suggestion4ISBN);
            isbn4.setText((InitialSurvey.getCorrectLangBooks()).get(3).getBook().getISBN13());

            if (InitialSurvey.debug)
                Log.v("Testing Ranking", "Fourth Score: " + InitialSurvey.getCorrectLangBooks().get(3).getScore());

            TextView title5 = (TextView) findViewById(R.id.suggestion5Title);
            title5.setText((InitialSurvey.getCorrectLangBooks()).get(4).getBook().getTitle());

            TextView author5 = (TextView) findViewById(R.id.suggestion5Author);
            author5.setText((InitialSurvey.getCorrectLangBooks()).get(4).getBook().getAuthors());

            TextView isbn5 = (TextView) findViewById(R.id.suggestion5ISBN);
            isbn5.setText((InitialSurvey.getCorrectLangBooks()).get(4).getBook().getISBN13());

            if (InitialSurvey.debug)
                Log.v("Testing Ranking", "Fifth Score: " + InitialSurvey.getCorrectLangBooks().get(4).getScore());
        }
    }

    /**
     * This method utilizes a bubble sort to rank the correctLangBooks ArrayList from highest to lowest book score.
     */
    public void sortCorrectLangBooks () {
        System.out.println();
        for (int k = 1; k <= InitialSurvey.getCorrectLangBooks().size() - 1; k++) {
            for (int i = 0; i < InitialSurvey.getCorrectLangBooks().size() - k; i++) {
                if (InitialSurvey.getCorrectLangBooks().get(i).getScore() < InitialSurvey.getCorrectLangBooks().get(i + 1).getScore()) {
                    BookScores temp = InitialSurvey.getCorrectLangBooks().get(i);
                    InitialSurvey.getCorrectLangBooks().set(i, InitialSurvey.getCorrectLangBooks().get(i + 1));
                    InitialSurvey.getCorrectLangBooks().set(i + 1, temp);
                }
            }
        }
    }

}