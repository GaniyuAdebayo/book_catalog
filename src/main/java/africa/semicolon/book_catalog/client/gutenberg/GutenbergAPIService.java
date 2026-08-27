package africa.semicolon.book_catalog.client.gutenberg;

import africa.semicolon.book_catalog.client.gutenberg.dtos.GutenbergBook;
import africa.semicolon.book_catalog.client.gutenberg.dtos.GutenbergResponsePage;

public interface GutenbergAPIService {

    GutenbergResponsePage<GutenbergBook> getBooks(String searchQuery);
    GutenbergResponsePage<GutenbergBook> getBooksByAuthor(String authorName);
    GutenbergResponsePage<GutenbergBook> getBooksByCategory(String categoryName);

}
