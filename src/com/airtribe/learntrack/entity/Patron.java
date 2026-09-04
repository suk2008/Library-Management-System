package src.com.airtribe.learntrack.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Patron {
    private final String id;
    private String name;
    private String email;
    private final List<Book> borrowingHistory;
    private final List<String> preferredGenres;

    public Patron(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.borrowingHistory = new ArrayList<>();
        this.preferredGenres = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBorrowingHistory() {
        return borrowingHistory;
    }

    public List<String> getPreferredGenres() {
        return preferredGenres;
    }
    public void addBorrowHistory(Book book)  {   borrowingHistory.add(book);}
    //callback for observer
    public void notifyAvailable(Book book){
//        System.out.println
        Logger.getLogger(Patron.class.getName()).info
        ("[Notify] " + name + ": Book '" + book.getTitle() + "' is now available!!!");
    }

    @Override
    public String toString() {
        return "Patron{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", borrowingHistory=" + borrowingHistory +
                ", preferredGenres=" + preferredGenres +
                '}';
    }
}
