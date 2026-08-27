package africa.semicolon.book_catalog.client.gutenberg;

import africa.semicolon.book_catalog.client.gutenberg.dtos.GutenbergBook;
import africa.semicolon.book_catalog.client.gutenberg.dtos.GutenbergResponsePage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class GutenbergAPIServiceImpl implements GutenbergAPIService {

    private final RestClient gutenbergRestClient;

    @Override
    public GutenbergResponsePage<GutenbergBook> getBooks(String searchQuery) {

        return gutenbergRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/books")
                        .queryParam("q", searchQuery)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<GutenbergResponsePage<GutenbergBook>>() {});

    }

    @Override
    public GutenbergResponsePage<GutenbergBook> getBooksByAuthor(String authorName) {
        return gutenbergRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/books")
                        .queryParam("author", authorName)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<GutenbergResponsePage<GutenbergBook>>() {});
    }

    @Override
    public GutenbergResponsePage<GutenbergBook> getBooksByCategory(String categoryName) {
        return gutenbergRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/books")
                        .queryParam("subject", categoryName)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<GutenbergResponsePage<GutenbergBook>>() {});
    }

}
