package ua.dtsebulia.backend.config;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConfigTest {

    @Test
    public void testRestTemplateCreation() {

        Config config = new Config();

        RestTemplate restTemplate = config.restTemplate();

        assertNotNull(restTemplate);
    }
}
