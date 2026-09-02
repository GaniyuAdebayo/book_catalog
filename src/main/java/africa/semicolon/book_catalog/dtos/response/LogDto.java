package africa.semicolon.book_catalog.dtos.response;

import africa.semicolon.book_catalog.data.SearchType;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class LogDto {

    private Long id;
    private SearchType searchType;
    private String searchTerm;
    private String requestedBy;
    private boolean resultFound;
    private int resultCount;
    private Instant timestamp;
}
