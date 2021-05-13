package com.example.appsforgood;

public class BookScores {

    private Book book;
    private double score;

    public BookScores(Book initB, double initScore) {
        book = initB;
        score = initScore;
    }
    public void setBook(Book initB) {
        book = initB;
    }
    public void setScore(double initScore) {
        score = initScore;
    }
    public Book getBook() { return book; }
    public double getScore() { return score; }

}
