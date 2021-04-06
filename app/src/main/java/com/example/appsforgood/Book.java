package com.example.appsforgood;

public class Book {

    private String title;
    private String authors;
    private int avgRating;
    private String languageCode;
    private int numPages;
    private int year;
    private int ratingsCount;

    public Book(String initTitle, String initAuthors, int initAvgRating, String initLanguageCode, int initNumPages, int initYear, int initRatingsCount) {
        title = initTitle;
        authors = initAuthors;
        avgRating = initAvgRating;
        languageCode = initLanguageCode;
        numPages = initNumPages;
        year = initYear;
        ratingsCount = initRatingsCount;
    }

    public Book() {
        title = "Book";
        authors = "Mark Jo";
        avgRating = 0;
        languageCode = "eng";
        numPages = 0;
        year = 2000;
        ratingsCount = 0;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public int getAvgRating() {
        return avgRating;
    }

    public String getLanguage() {
        return languageCode;
    }

    public int getNumPages() {
        return numPages;
    }

    public int getYear() {
        return year;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }
}
