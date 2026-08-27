package africa.semicolon.book_catalog.client.gutenberg.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GutenbergBook {

    /*

{
  "next": "https://project-gutenberg-books-api.p.rapidapi.com/books?q=shakespeare&page_size=10&page=2",
  "previous": null,
  "results": [
    {
      "id": 1513,
      "title": "Romeo and Juliet",
      "alternative_title": null,
      "authors": [
        {
          "id": 65,
          "name": "Shakespeare, William"
        }
      ],
      "subjects": ["Tragedies", "Drama"],
      "bookshelves": ["Best Books Ever Listings"],
      "media_type": "Text",
      "download_count": 67890,
      "issued": "1997-05-01T00:00:00.000Z",
      "reading_ease_score": "81.20",
      "cover_image": "https://www.gutenberg.org/cache/epub/1513/pg1513.cover.medium.jpg"
    }
  ]
}
    *  */

    private Long id;
    private String title;

    @JsonProperty("alternative_title")
    private String alternativeTitle;
    @JsonProperty("media_type")
    private String mediaType;

    @JsonProperty("download_count")
    private long downloadCount;

    private String issued;

    @JsonProperty("reading_ease_score")
    private String readingEaseScore;

    @JsonProperty("cover_image")
    private String coverImage;

    private List<String> subjects;
    private List<String> bookshelves;
    private List<Author> authors;

    @Data
    public static class Author {
        private Long id;
        private String name;
    }
}
