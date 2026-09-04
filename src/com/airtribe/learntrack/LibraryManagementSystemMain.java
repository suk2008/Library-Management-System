package src.com.airtribe.learntrack;

import src.com.airtribe.learntrack.RecommendationStrategies.RecommendationStrategy;
import src.com.airtribe.learntrack.RecommendationStrategies.RecommendationStrategyBasedOnHistory;
import src.com.airtribe.learntrack.branch.BranchTransferService;
import src.com.airtribe.learntrack.branch.LibraryBranch;
import src.com.airtribe.learntrack.entity.Book;
import src.com.airtribe.learntrack.entity.Patron;
import src.com.airtribe.learntrack.system.LibrarySystem;

import java.util.ArrayList;

public class LibraryManagementSystemMain {
    public static void main(String[] args) {
        LibrarySystem librarySystem = new LibrarySystem();
        LibraryBranch branchA = new LibraryBranch("B001", "Central Library");
        LibraryBranch branchB = new LibraryBranch("B002", "Community Library");
        librarySystem.addBranch(branchA);
        librarySystem.addBranch(branchB);


        branchA.addBook(new Book("ISBN001", "Effective Java", "Joshua Bloch", 2018, "Programming"));
        branchA.addBook(new Book("ISBN003", "The Hobbit", "J.R.R. Tolkien", 1937, "Fantasy"));
        branchB.addBook(new Book("ISBN002", "Clean Code", "Robert C. Martin", 2008, "Programming"));
        branchA.addBook(new Book("ISBN004", "The Forty Rules of Love", "Elif Shafak", 2009, "Spiritual Fiction"));
        branchA.addBook(new Book("ISBN005", "Men Are from Mars, Women Are from Venus", "John Gray", 1992, "Relationship Psychology"));
        branchB.addBook(new Book("ISBN006", "Emotional Intelligence", "Daniel Goleman", 1995, "Psychology"));

        Patron patron1 = new Patron("P001", "Sowmya", "sowmya@example.com");
        Patron patron2 = new Patron("P002", "Bob", "bob@example.com");
        librarySystem.addPatron(patron1);
        librarySystem.addPatron(patron2);

        System.out.println("Checkout attempt:");
        if(librarySystem.checkoutBook("B001", "ISBN001", "P001")) System.out.println( "Checkout Done!!");
        else System.out.println("checkout unsuccessful!!");
        System.out.print("Reservation attempt: ");
        if(librarySystem.reserveBook("B001", "ISBN001", "P002")) System.out.println("Reservation Done!!");
        else System.out.println("Reservation unsuccessful!!");
        System.out.print("Return attempt: ");
        if(librarySystem.returnBook("B001", "ISBN001")) System.out.println("Return Successful!!");
        else System.out.println("Return Failed!!");
        BranchTransferService transferService = new BranchTransferService();
        System.out.print("Transfer attempt: ");
        if(transferService.transferBook("ISBN002", branchB, branchA)) System.out.println("Transfer Completed!!!");
        else System.out.println("Transfer Unsuccessful!!!");
//        transferService.transferBook("ISBN004", branchA, branchB);

        // Recommendation Strategy Demo
        RecommendationStrategy strategy = new RecommendationStrategyBasedOnHistory();
        ArrayList<Book> allBooks = new ArrayList<>();
        allBooks.addAll(branchA.getInventory().values());
        allBooks.addAll(branchB.getInventory().values());
        System.out.println("Recommendations for Sowmya:");

        for (Book b : strategy.recommend(patron1, allBooks)) {
            System.out.println(" - " + b.getTitle());
        }

    }
}
