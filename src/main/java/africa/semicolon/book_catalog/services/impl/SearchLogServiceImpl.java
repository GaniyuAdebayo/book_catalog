package africa.semicolon.book_catalog.services.impl;

import africa.semicolon.book_catalog.data.SearchType;
import africa.semicolon.book_catalog.data.model.Book;
import africa.semicolon.book_catalog.data.model.SearchLog;
import africa.semicolon.book_catalog.data.repositories.SearchLogRepository;
import africa.semicolon.book_catalog.services.SearchLogService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchLogServiceImpl implements SearchLogService {

    private final SearchLogRepository searchLogRepository;

    @Override
    public void logSearchByCategory(List<Book> books, String searchTerm, String requestedBy) {
        SearchLog searchLog = SearchLog.builder()
                .searchType(SearchType.CATEGORY)
                .searchTerm(searchTerm)
                .requestedBy(requestedBy)
                .resultFound(!books.isEmpty())
                .resultCount(books.size())
                .build();
        searchLogRepository.save(searchLog);
    }

    @Override
    public void logSearchByAuthor(List<Book> books, String searchTerm, String requestedBy) {
        SearchLog searchLog = SearchLog.builder()
                .searchType(SearchType.AUTHOR)
                .searchTerm(searchTerm)
                .requestedBy(requestedBy)
                .resultFound(!books.isEmpty())
                .resultCount(books.size())
                .build();
        searchLogRepository.save(searchLog);
    }

    @Override
    public Page<SearchLog> getAllSearchLogs(Pageable pageable) {
        return  searchLogRepository.findAll(pageable);
    }

    @Override
    public List<SearchLog> getSearchLogsByName(String name) {
        return searchLogRepository.findAllByRequestedBy(name);
    }

    @Override
    public List<SearchLog> getSearchLogsByResultFound(boolean resultFound) {
        return searchLogRepository.findAllByResultFound(resultFound);
    }


}
