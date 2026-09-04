package src.com.airtribe.learntrack.system;

import src.com.airtribe.learntrack.branch.LibraryBranch;
import src.com.airtribe.learntrack.constants.BookStatus;
import src.com.airtribe.learntrack.entity.Book;
import src.com.airtribe.learntrack.entity.Patron;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class LibrarySystem {
    private static final Logger logging = Logger.getLogger(LibrarySystem.class.getName());
    private final Map<String,LibraryBranch> branches = new HashMap<>();
    private final Map<String, Patron> patronMap = new HashMap<>();

    public void addBranch(LibraryBranch branch) {  branches.put(branch.getBranchId(), branch);  }
    public void addPatron(Patron patron) {  patronMap.put(patron.getId(), patron);  }

    //book checkout
    public Boolean checkoutBook(String branchId, String isbn, String patronId) {
        LibraryBranch branch = branches.get(branchId);
        Patron patron = patronMap.get(patronId);
        if (branch == null || patron == null)       return false;

        Book book = branch.getInventory().get(isbn);
        if (book != null && book.getStatus() == BookStatus.Available) {
            book.setStatus(BookStatus.Borrowed);
            patron.addBorrowHistory(book);
            logging.info("Book '" + book.getTitle() + "' checked out by " + patron.getName());
            return true;
        }
        logging.warning("Failed: Book is not available!!!");
        return false;
    }
    //return the book
    public Boolean returnBook(String branchId, String isbn) {
        LibraryBranch branch = branches.get(branchId);
        if (branch == null) return false;
        Book book = branch.getInventory().get(isbn);
        if (book != null && book.getStatus() == BookStatus.Borrowed) {
            logging.info("Book '" + book.getTitle() + "' returned.");

            if (!book.getReservationQueue().isEmpty()) {
                book.setStatus(BookStatus.Reserved);
                Patron nextPatron = book.notifyNextReservation();
                nextPatron.notifyAvailable(book);
            } else          book.setStatus(BookStatus.Available);
            return true;
        }
        return false;
    }
    //Book Reserve
    public Boolean reserveBook(String branchId, String isbn, String patronId) {
        LibraryBranch branch = branches.get(branchId);
        Patron patron = patronMap.get(patronId);
        if (branch == null || patron == null)       return false;
        Book book = branch.getInventory().get(isbn);
        if (book != null && book.getStatus() == BookStatus.Borrowed) {
            book.addReservation(patron);
            logging.info("Reservation placed for '" + book.getTitle() + "' by " + patron.getName());
            return true;
        }
        return false;
    }

}
