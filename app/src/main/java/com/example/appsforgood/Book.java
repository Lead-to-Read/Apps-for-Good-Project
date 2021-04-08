package com.example.appsforgood;

public class Book {

    private String title;
    private String authors;
    private double avgRating;
    private String languageCode;
    private double numPages;
    private double ratingsCount;
    private double year;

    public Book(String initTitle, String initAuthors, double initAvgRating, String initLanguageCode, double initNumPages, double initRatingsCount, double initYear,) {
        title = initTitle;
        authors = initAuthors;
        avgRating = initAvgRating;
        languageCode = initLanguageCode;
        numPages = initNumPages;
        ratingsCount = initRatingsCount;
        year = initYear;
    }

    public Book() {
        title = "Book";
        authors = "Mark Jo";
        avgRating = 0;
        languageCode = "eng";
        numPages = 0;
        ratingsCount = 0;
        year = 2000;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public String getLanguage() {
        return languageCode;
    }

    public double getNumPages() {
        return numPages;
    }

    public double getRatingsCount() {
        return ratingsCount;
    }

    public double getYear() {
        return year;
    }

}
