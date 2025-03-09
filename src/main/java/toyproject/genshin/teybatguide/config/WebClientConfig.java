package toyproject.genshin.teybatguide.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
            .defaultHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
            .build();
    }

}
