package com.example.appsforgood;

public class Book {

    // Instance Variables
    private String title;
    private String authors;
    private double avgRating;
    private String languageCode;
    private int numPages;
    private int ratingsCount;
    private int year;

    // Constructors
    /**
     * Parameter constructor that initializes 7 instance variables to the user's inputted variables
     * @param initTitle title of the book as a String
     * @param initAuthors author(s) of the book as Strings separated by commas
     * @param initAvgRating average Goodreads rating of the book as a double out of 5
     * @param initLanguageCode language code of the book (eng, en-US, en-GB, spa, or fre), where eng, en-US, en-GB are English, spa is Spanish, and fre is French
     * @param initNumPages number of pages in the book as an int
     * @param initRatingsCount number of ratings for the book on Goodreads, indicator of popularity
     * @param initYear book's year of publication
     */
    public Book(String initTitle, String initAuthors, double initAvgRating, String initLanguageCode, int initNumPages, int initRatingsCount, int initYear) {
        title = initTitle;
        authors = initAuthors;
        avgRating = initAvgRating;
        languageCode = initLanguageCode;
        numPages = initNumPages;
        ratingsCount = initRatingsCount;
        year = initYear;
    }

    /**
     * Default/no parameter constructor that initializes all Strings (title, authors, languageCode) to empty Strings and integers and doubles (avgRating, numPages, ratingCount, year) to 0
     */
    public Book() {
        title = "";
        authors = "";
        avgRating = 0.0;
        languageCode = "";
        numPages = 0;
        ratingsCount = 0;
        year = 0000;
    }

    //Methods
    /**
     * Gets title of the book as a String
     * @return title of the book as a String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets author(s) of the book as Strings separated by commas
     * @return author(s) of the book as Strings separated by commas
     */
    public String getAuthors() {
        return authors;
    }

    /**
     * Gets average Goodreads rating of the book
     * @return average Goodreads rating of the book as a double with a maximum of 5.0
     */
    public double getAvgRating() {
        return avgRating;
    }

    /**
     * Gets language code of the book
     * @return String language code of the book
     */
    public String getLanguage() {
        if ((languageCode.equals("en-US")) || (languageCode.equals("en-GB"))) {
            languageCode = "eng";
        }
        return languageCode;
    }

    /**
     * Gets number of pages in the book
     * @return number of pages in the book as an int
     */
    public int getNumPages() {
        return numPages;
    }

    /**
     * Gets number of ratings for the book on Goodreads
     * @return number of ratings for the book on Goodreads, indicator of popularity
     */
    public int getRatingsCount() {
        return ratingsCount;
    }

    /**
     * Gets year of publication
     * @return book's year of publication
     */
    public int getYear() {
        return year;
    }
}
