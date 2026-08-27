package africa.semicolon.book_catalog.controllers;
import africa.semicolon.book_catalog.data.model.Book;
import africa.semicolon.book_catalog.dtos.response.BookDto;
import africa.semicolon.book_catalog.dtos.response.PageDto;
import africa.semicolon.book_catalog.services.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping ("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/category/{category}")
    public ResponseEntity<List<BookDto>> getBooksByCategory(@PathVariable String category) {
        List<Book> books = bookService.getBooksByCategory(category);
        Type type = new TypeToken<List<BookDto>>(){}.getType();
        return ResponseEntity.ok(modelMapper.map(books, type));
    }

    @GetMapping("/author/{authorName}")
    public ResponseEntity<List<BookDto>> getBooksByAuthor(@PathVariable String authorName) {
        List<Book> books = bookService.getBooksByAuthor(authorName);
        Type type = new TypeToken<List<BookDto>>(){}.getType();
        return ResponseEntity.ok(modelMapper.map(books, type));
    }

    @GetMapping
    public ResponseEntity<PageDto<BookDto>> getBooks(@ParameterObject Pageable pageable) {
        Page<Book> bookPage= bookService.getAllBooks(pageable);
        Type type = new  TypeToken<PageDto<BookDto>>(){}.getType();
        return ResponseEntity.ok(modelMapper.map(bookPage, type));
    }

    @PostMapping("/sync")
    public ResponseEntity<String> syncBooks(@RequestParam("q") String query) {
        bookService.syncBooks(query);
        return ResponseEntity.ok("Sync successfully");
    }

}
