package src.com.airtribe.learntrack.entity;

import src.com.airtribe.learntrack.constants.BookStatus;

import java.util.LinkedList;
import java.util.Queue;

public class Book {
    private final String isbn;
    private String title;
    private String author;
    private int publicationYear;
    private String genre;
    private BookStatus status;
    private final Queue<Patron> reservationQueue;

    public Book(String isbn, String title, String author, int publicationYear, String genre) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.status = BookStatus.Available;
        this.reservationQueue = new LinkedList<>();
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Queue<Patron> getReservationQueue() {
        return reservationQueue;
    }
    public void addReservation(Patron patron){
        if(!reservationQueue.contains(patron))      reservationQueue.add(patron);
    }
    public Patron notifyNextReservation(){  return reservationQueue.poll();    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", genre='" + genre + '\'' +
                ", status=" + status +
                ", reservationQueue=" + reservationQueue +
                '}';
    }
}
