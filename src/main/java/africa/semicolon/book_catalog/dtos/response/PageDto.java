package africa.semicolon.book_catalog.dtos.response;

import lombok.Data;

import java.util.List;

@Data
public class PageDto<TData> {

    private int totalPages;
    private long totalElements;
    private int pageNumber;
    private List<TData> content;
}
