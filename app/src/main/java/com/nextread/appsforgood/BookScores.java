package com.nextread.appsforgood;

/**
 * This class stores each Book object with its associated score to identify the five highest-rated books.
 */
public class BookScores {

    //Instance Variables
    private Book book;
    private double score;

    /**
     * Parameter constructor that initializes two instance variables to the user's inputted variables
     * @param initB Book object
     * @param initScore Book's weighted score from the algorithm
     */
    public BookScores(Book initB, double initScore) {
        book = initB;
        score = initScore;
    }

    /**
     * Gets Book object
     * @return Book object
     */
    public Book getBook() {
        return book;
    }

    /**
     * Gets score associated with Book object as a double
     * @return score associated with Book object as a double
     */
    public double getScore() {
        return score;
    }

    /**
     * Sets Book object to inputted Book
     * @param initB new Book object
     */
    public void setBook(Book initB) {
        book = initB;
    }

    /**
     * Sets score of Book object to inputted score
     * @param initScore new score of the Book object as a double
     */
    public void setScore(double initScore) {
        score = initScore;
    }
}
