package africa.semicolon.book_catalog;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
@OpenAPIDefinition(info = @Info(description = "Book Catalog"))
public class BookCatalogApplication {

    static void main(String[] args) {
        SpringApplication.run(BookCatalogApplication.class, args);
    }

}
