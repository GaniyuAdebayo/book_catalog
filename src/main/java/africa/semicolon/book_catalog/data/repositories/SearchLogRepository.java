package africa.semicolon.book_catalog.data.repositories;

import africa.semicolon.book_catalog.data.model.SearchLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchLogRepository extends JpaRepository<SearchLog, Long> {

     List<SearchLog> findAllByRequestedBy(String name);
     List<SearchLog> findAllByResultFound(boolean resultFound);
}
