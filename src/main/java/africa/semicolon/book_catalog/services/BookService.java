package africa.semicolon.book_catalog.services;

import africa.semicolon.book_catalog.data.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    List<Book> getBooksByCategory(String category);

    List<Book> getBooksByAuthor(String authorName);

    Page<Book> getAllBooks(Pageable pageable);


    void syncBooks(String query);

}
