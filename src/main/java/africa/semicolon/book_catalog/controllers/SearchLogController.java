package africa.semicolon.book_catalog.controllers;

import africa.semicolon.book_catalog.data.model.SearchLog;
import africa.semicolon.book_catalog.dtos.response.LogDto;
import africa.semicolon.book_catalog.services.SearchLogService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/search-logs")
public class SearchLogController {

    private final SearchLogService searchLogService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<Page<LogDto>> getAllBooks(@ParameterObject Pageable pageable) {
        Page<SearchLog> logs = searchLogService.getAllSearchLogs(pageable);
        return ResponseEntity.ok(modelMapper.map(logs, new TypeToken<Page<LogDto>>(){}.getType()));
    }

    @GetMapping("/user/{requester}")
    public ResponseEntity<List<LogDto>> getAllBooksByRequester(@PathVariable String requester) {
        List<SearchLog> logs = searchLogService.getSearchLogsByName(requester);
        return ResponseEntity.ok(modelMapper.map(logs, new TypeToken<List<LogDto>>(){}.getType()));
    }

    @GetMapping("/failed")
    public ResponseEntity<List<LogDto>> getAllBooksByFailed() {
        List<SearchLog> logs = searchLogService.getSearchLogsByResultFound(false);
        return ResponseEntity.ok(modelMapper.map(logs, new TypeToken<List<LogDto>>(){}.getType()));
    }
}
