package src.com.airtribe.learntrack.RecommendationStrategies;

import src.com.airtribe.learntrack.entity.Book;
import src.com.airtribe.learntrack.entity.Patron;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecommendationStrategyBasedOnHistory implements RecommendationStrategy{
    @Override
    public List<Book> recommend(Patron patron, List<Book> inventories) {
        Set<String> genres = new HashSet<>();
        for(Book book : patron.getBorrowingHistory()){
            genres.add(book.getGenre());
        }
        List<Book> recommendations = new ArrayList<>();
        for (Book book : inventories){
            if(genres.contains(book.getGenre()) && !patron.getBorrowingHistory().contains(book))
                recommendations.add(book);
        }
        return recommendations;
    }
}
