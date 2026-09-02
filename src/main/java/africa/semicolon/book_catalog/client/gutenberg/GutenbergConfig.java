package africa.semicolon.book_catalog.client.gutenberg;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@RequiredArgsConstructor
public class GutenbergConfig {

    private final GutenbergConfigProperties properties;

    @Bean
    public RestClient gutenbergRestClient() {
        return RestClient.builder()
                .baseUrl(properties.getBaseUrl())
                .defaultHeader("X-RapidAPI-Key", properties.getApiKey())
                .defaultHeader("X-RapidAPI-Host",  properties.getHost())
                .build();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
