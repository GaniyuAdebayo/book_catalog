package africa.semicolon.book_catalog.services.impl;

import africa.semicolon.book_catalog.client.gutenberg.dtos.GutenbergBook;
import africa.semicolon.book_catalog.client.gutenberg.dtos.GutenbergResponsePage;
import africa.semicolon.book_catalog.data.model.Book;
import africa.semicolon.book_catalog.data.repositories.BookRepository;
import africa.semicolon.book_catalog.services.BookService;
import africa.semicolon.book_catalog.client.gutenberg.GutenbergAPIService;
import africa.semicolon.book_catalog.services.SearchLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GutenbergAPIService gutenbergAPIService;
    private final SearchLogService searchLogService;

    @Override
    public List<Book> getBooksByCategory(String name, String category) {
        List<Book> allByCategory = bookRepository.findAllByCategoriesContaining(category);
        if  (!allByCategory.isEmpty()) {
            searchLogService.logSearchByCategory(allByCategory, category, name);
            return allByCategory;
        }
        GutenbergResponsePage<GutenbergBook> gutenbergBooks = gutenbergAPIService.getBooksByCategory(category);
        List<Book> books = new ArrayList<>();
        for (GutenbergBook book : gutenbergBooks.getResults()){
            Book bookCopy = createBookCopy(book);
            books.add(bookCopy);
        }
        searchLogService.logSearchByCategory(books, category, name);
        return bookRepository.saveAll(books);
    }

    @Override
    public List<Book> getBooksByAuthor(String authorName, String name) {
        List<Book> allByAuthor = bookRepository.findAllByAuthorsContaining(authorName);
        if  (!allByAuthor.isEmpty()) {
            searchLogService.logSearchByAuthor(allByAuthor, authorName, name);
            return allByAuthor;
        }
        GutenbergResponsePage<GutenbergBook> gutenbergBooks = gutenbergAPIService.getBooksByAuthor(authorName);
        List<Book> books = new ArrayList<>();
        for (GutenbergBook book : gutenbergBooks.getResults()){
            Book bookCopy = createBookCopy(book);
            books.add(bookCopy);
        }
        searchLogService.logSearchByAuthor(allByAuthor, authorName, name);
        return bookRepository.saveAll(books);
    }

    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public void syncBooks(String query) {
        log.info("Syncing books");
        GutenbergResponsePage<GutenbergBook> gutenbergBooks = gutenbergAPIService.getBooks(query);
        log.info("GutenbergBooks {}", gutenbergBooks.getResults());
        List<Book> books = new ArrayList<>();
        List<Long> gutenbergIds = new ArrayList<>();
        for (GutenbergBook book : gutenbergBooks.getResults() ){
            Book bookCopy = createBookCopy(book);
            books.add(bookCopy);
            gutenbergIds.add(book.getId());
        }

        List<Long> existingGutenbergIds = bookRepository.findAllGutenbergIds(gutenbergIds);

        log.info("Existing GutenbergIds {}", existingGutenbergIds);

        List<Book> newBooks = new ArrayList<>();
        for (Book book : books) {
            if (!existingGutenbergIds.contains(book.getGutenbergId())) {
                newBooks.add(book);
            }
        }
        if (!newBooks.isEmpty()) {
            bookRepository.saveAll(newBooks);
        }
    }

    @Override
    public Set<String> getAllDistinctCategories() {
        List<List<String>> categories = bookRepository.getAllCategories();
        Set<String> allCategories = new HashSet<>();
        for (List<String> category : categories) {
            allCategories.addAll(category);
        }
        return allCategories;
    }

    private static @NonNull Book createBookCopy(GutenbergBook book) {
        Book bookCopy = new Book();
        bookCopy.setTitle(book.getTitle());
        bookCopy.setCoverImageUrl(book.getCoverImage());
        bookCopy.setDownloadCount(book.getDownloadCount());
        bookCopy.setGutenbergId(book.getId());
        bookCopy.setCategories(book.getSubjects());
        bookCopy.setAuthors(book.getAuthors().stream()
                .map(author -> author.getName())
                .toList());
        return bookCopy;
    }

}
