package src.com.airtribe.learntrack.branch;

import src.com.airtribe.learntrack.constants.BookStatus;
import src.com.airtribe.learntrack.entity.Book;

import java.util.logging.Logger;

public class BranchTransferService {
    private static final Logger logger = Logger.getLogger(BranchTransferService.class.getName());
    public boolean transferBook(String isbn, LibraryBranch source, LibraryBranch destination) {
        Book book = source.getInventory().get(isbn);
            if(book !=null&&book.getStatus()== BookStatus.Available)   {
                source.removeBook(isbn);
                book.setStatus(BookStatus.InTransit);
                destination.addBook(book);
                book.setStatus(BookStatus.Available);
                logger.info("ISBN Transferred : " + isbn + " from " + source.getBranchId() + " to " + destination.getBranchId());
                return true;
            }
            logger.warning("Transfer failed: Book not found!!!");
            return false;
    }
}
