package africa.semicolon.book_catalog.dtos.response;
import lombok.Data;

import java.util.List;

@Data
public class BookDto {

    private Long id;
    private String gutenbergId;
    private String title;
    private List<String> authors;
    private List<String> categories;
    private long downloadCount;
    private String coverImageUrl;
}
