package africa.semicolon.book_catalog.client.gutenberg.dtos;

import lombok.Data;

import java.util.List;

@Data
public class GutenbergResponsePage<TData> {

    private String next;
    private String previous;
    private List<TData> results;
}
