package src.com.airtribe.learntrack.RecommendationStrategies;

import src.com.airtribe.learntrack.entity.Book;
import src.com.airtribe.learntrack.entity.Patron;

import java.util.List;

public interface RecommendationStrategy {
    List<Book> recommend(Patron patron, List<Book> inventories);
}
