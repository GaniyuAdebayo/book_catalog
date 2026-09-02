package africa.semicolon.book_catalog.services;

import africa.semicolon.book_catalog.data.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface BookService {

    List<Book> getBooksByCategory(String name, String category);

    List<Book> getBooksByAuthor(String authorName, String name);

    Page<Book> getAllBooks(Pageable pageable);


    void syncBooks(String query);

    Set<String> getAllDistinctCategories();

}
