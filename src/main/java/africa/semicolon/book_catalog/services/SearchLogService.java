package africa.semicolon.book_catalog.services;

import africa.semicolon.book_catalog.data.model.Book;
import africa.semicolon.book_catalog.data.model.SearchLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SearchLogService {

    void logSearchByCategory(List<Book> books, String searchTerm, String requestedBy);
    void logSearchByAuthor(List<Book> books, String searchTerm, String requestedBy);
    Page<SearchLog> getAllSearchLogs(Pageable pageable);
    List<SearchLog> getSearchLogsByName(String name);
    List<SearchLog> getSearchLogsByResultFound(boolean resultFound);
}
