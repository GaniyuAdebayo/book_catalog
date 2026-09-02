package africa.semicolon.book_catalog.data.repositories;

import africa.semicolon.book_catalog.data.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b.gutenbergId FROM Book b WHERE b.gutenbergId IN :gutenbergIds")
    List<Long> findAllGutenbergIds(List<Long> gutenbergIds);

    List<Book> findAllByCategoriesContaining(String category);
    List<Book> findAllByAuthorsContaining(String author);

    @Query("SELECT b.categories FROM Book b")
    List<List<String>> getAllCategories();


}