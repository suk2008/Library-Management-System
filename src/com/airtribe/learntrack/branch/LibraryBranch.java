package src.com.airtribe.learntrack.branch;

import src.com.airtribe.learntrack.entity.Book;

import java.util.HashMap;
import java.util.Map;

public class LibraryBranch {
    private final String branchId;
    private final String branchName;
    //ISBN -> Book
    private final Map<String, Book> inventory = new HashMap<>();

    public LibraryBranch(String branchId, String branchName) {
        this.branchId = branchId;
        this.branchName = branchName;
    }
    public void addBook(Book book){  inventory.put(book.getIsbn(), book);   }
    public void removeBook(String isbn) {   inventory.remove(isbn); }

    public String getBranchId() {
        return branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public Map<String, Book> getInventory() {
        return inventory;
    }

    @Override
    public String toString() {
        return "LibraryBranch{" +
                "branchId='" + branchId + '\'' +
                ", branchName='" + branchName + '\'' +
                ", inventory=" + inventory +
                '}';
    }
}
