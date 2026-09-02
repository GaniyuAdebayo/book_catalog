package africa.semicolon.book_catalog.data.model;

import africa.semicolon.book_catalog.data.SearchType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "search_log")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SearchType searchType;

    private String searchTerm;
    private String requestedBy;
    private boolean resultFound;
    private int resultCount;

    @CreationTimestamp
    private Instant timestamp;
}
