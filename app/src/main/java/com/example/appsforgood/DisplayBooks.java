package com.example.appsforgood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class  DisplayBooks extends AppCompatActivity {

    //ArrayList<BookScores> correctLangBooks2;

    /**
     * Displays the activity_display_books.xml activity
     * @param savedInstanceState Bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_books);
        sortCorrectLangBooks();
        //getHighest(InitialSurvey.getCorrectLangBooks());

        TextView title1 = (TextView) findViewById(R.id.suggestion1Title);
        title1.setText((InitialSurvey.getCorrectLangBooks()).get(0).getBook().getTitle());

        TextView author1 = (TextView) findViewById(R.id.suggestion1Author);
        author1.setText((InitialSurvey.getCorrectLangBooks()).get(0).getBook().getAuthors());

        TextView isbn1 = (TextView) findViewById(R.id.suggestion1ISBN);
        isbn1.setText((InitialSurvey.getCorrectLangBooks()).get(0).getBook().getISBN13());

        Log.v("Testing Ranking", "First Score: " + InitialSurvey.getCorrectLangBooks().get(0).getScore());

        TextView title2 = (TextView) findViewById(R.id.suggestion2Title);
        title2.setText((InitialSurvey.getCorrectLangBooks()).get(1).getBook().getTitle());

        TextView author2 = (TextView) findViewById(R.id.suggestion2Author);
        author2.setText((InitialSurvey.getCorrectLangBooks()).get(1).getBook().getAuthors());

        TextView isbn2 = (TextView) findViewById(R.id.suggestion2ISBN);
        isbn2.setText((InitialSurvey.getCorrectLangBooks()).get(1).getBook().getISBN13());

        Log.v("Testing Ranking", "Second Score: " + InitialSurvey.getCorrectLangBooks().get(1).getScore());

        TextView title3 = (TextView) findViewById(R.id.suggestion3Title);
        title3.setText((InitialSurvey.getCorrectLangBooks()).get(2).getBook().getTitle());

        TextView author3 = (TextView) findViewById(R.id.suggestion3Author);
        author3.setText((InitialSurvey.getCorrectLangBooks()).get(2).getBook().getAuthors());

        TextView isbn3 = (TextView) findViewById(R.id.suggestion3ISBN);
        isbn3.setText((InitialSurvey.getCorrectLangBooks()).get(2).getBook().getISBN13());

        Log.v("Testing Ranking", "Third Score: " + InitialSurvey.getCorrectLangBooks().get(2).getScore());

        TextView title4 = (TextView) findViewById(R.id.suggestion4Title);
        title4.setText((InitialSurvey.getCorrectLangBooks()).get(3).getBook().getTitle());

        TextView author4 = (TextView) findViewById(R.id.suggestion4Author);
        author4.setText((InitialSurvey.getCorrectLangBooks()).get(3).getBook().getAuthors());

        TextView isbn4 = (TextView) findViewById(R.id.suggestion4ISBN);
        isbn4.setText((InitialSurvey.getCorrectLangBooks()).get(3).getBook().getISBN13());

        Log.v("Testing Ranking", "Fourth Score: " + InitialSurvey.getCorrectLangBooks().get(3).getScore());

        TextView title5 = (TextView) findViewById(R.id.suggestion5Title);
        title5.setText((InitialSurvey.getCorrectLangBooks()).get(4).getBook().getTitle());

        TextView author5 = (TextView) findViewById(R.id.suggestion5Author);
        author5.setText((InitialSurvey.getCorrectLangBooks()).get(4).getBook().getAuthors());

        TextView isbn5 = (TextView) findViewById(R.id.suggestion5ISBN);
        isbn5.setText((InitialSurvey.getCorrectLangBooks()).get(4).getBook().getISBN13());

        Log.v("Testing Ranking", "Fifth Score: " + InitialSurvey.getCorrectLangBooks().get(4).getScore());
    }

    public Book getHighest (ArrayList<BookScores> initCorrectLangBooks) {

        double maxScore = initCorrectLangBooks.get(0).getScore();
        //int i = 0;
        int correctIndex = 0;
        for (int i = 0; i < initCorrectLangBooks.size(); i++) {
            if (initCorrectLangBooks.get(i).getScore() > maxScore) {
                maxScore = initCorrectLangBooks.get(i).getScore();
                correctIndex = i;
            }
        }

        Log.v("Highest Scoring Book","Index " + correctIndex); //~37
        Log.v("Highest Scoring Book", "Equals" + "correctLangBooks size:" + initCorrectLangBooks.size() + "bookScores size: " + initCorrectLangBooks.size()); //46 if eng is chosen
        Log.v("Highest Scoring Book", "Highest Scoring Book" + initCorrectLangBooks.get(correctIndex).getBook().getTitle() + maxScore);
        //ArrayList<BookScores> correctLangBooks = initCorrectLangBooks;
        //correctLangBooks2 = initCorrectLangBooks;
        //correctLangBooks2.remove(correctIndex);
        return initCorrectLangBooks.get(correctIndex).getBook();
    }

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