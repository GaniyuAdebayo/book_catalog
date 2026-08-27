package africa.semicolon.book_catalog.client.gutenberg;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "gutenberg")
@Data
public class GutenbergConfigProperties {

    private String host;
    private String apiKey;
    private String baseUrl;
}
